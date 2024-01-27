package xyz.windows.file.explorer.ui.table;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import xyz.windows.file.explorer.model.Data;
import xyz.windows.file.explorer.ui.Refreshable;
import xyz.windows.file.explorer.utils.Task;

public class MainTableAsyncLoader implements Task.TaskCompleted {

    private final MainTableModel tableModel;
    private final List<Data> dataSet;
    private final Refreshable refreshable;

    private final AtomicInteger countCompleted = new AtomicInteger(0);
    private final AtomicLong parentFolderSizeInBytes = new AtomicLong(0L);

    private final ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(20);

    public MainTableAsyncLoader(Refreshable refreshable, MainTableModel tableModel, List<Data> dataSet) {
        this.tableModel = tableModel;
        this.dataSet = dataSet;
        this.refreshable = refreshable;
    }

    public void start() {
        for (Data data : dataSet) {
            Task task = new Task(data, this);
            executor.execute(task);
        }
        executor.shutdown();
    }

    @Override
    public void onComplete(Data data) {
        countCompleted.addAndGet(1);
        parentFolderSizeInBytes.addAndGet(data.getSizeInBytes());

        refreshable.refresh();

        if (countCompleted.get() == dataSet.size()) {
            //ON FULLY COMPLETED
            for (Data dataTemp : dataSet) {
                dataTemp.setParentSizeInBytes(parentFolderSizeInBytes.get());
                double sizePercentRelativeToParent = (dataTemp.getSizeInBytes() * 100F) / dataTemp.getParentSizeInBytes();
                dataTemp.setSizePercentRelativeToParent(sizePercentRelativeToParent);
            }
            refreshable.refresh(parentFolderSizeInBytes.get());
        }

        tableModel.fireTableDataChanged();
        tableModel.adjustColumnWidths();

    }
}
