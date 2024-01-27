package xyz.windows.file.explorer.ui.table;

import xyz.windows.file.explorer.model.Data;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.AbstractTableModel;

public class MainTableModel extends AbstractTableModel {

    private final String[] COLUMN_NAMES = {
        "",
        "Name",
        "Size",
        "RelativeSize%",
        "Created",
        "#Files(F)",
        "#Directories (D)"
    };
    private final Class[] COLUMN_CLASSES = {
        ImageIcon.class,
        String.class,
        String.class,
        String.class,
        String.class,
        String.class,
        String.class
    };
    private final int[] COLUMN_WIDTH = {
        40,
        -1,
        150,
        150,
        250,
        100,
        100
    };
    private final JTable mainTable;
    private final List<Data> dataSet;

    public MainTableModel(JTable mainTable, List<Data> dataSet) {
        this.mainTable = mainTable;
        this.dataSet = dataSet;
    }

    @Override
    public int getRowCount() {
        return dataSet == null ? 0 : dataSet.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public String getColumnName(int col) {
        return COLUMN_NAMES[col];
    }

    @Override
    public Class getColumnClass(int col) {
        return COLUMN_CLASSES[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        switch (col) {
            case 0:
                return FileSystemView.getFileSystemView().getSystemIcon(dataSet.get(row).getFileObject());
            case 1:
                return dataSet.get(row).getNameReadable();
            case 2:
                return dataSet.get(row).getSizeInReadble();
            case 3:
                return dataSet.get(row).getSizePercentRelativeToParentReadable();
            case 4:
                return dataSet.get(row).getCreationTimeStampReadable();
            case 5:
                return dataSet.get(row).getNumberOfFilesReadable();
            case 6:
                return dataSet.get(row).getNumberOfFoldersReadable();
        }

        return null;
    }

    @Override
    public void fireTableDataChanged() {
        super.fireTableDataChanged(); 
        adjustColumnWidths();
    }
    
    public void adjustColumnWidths() {
        for (int i = 0; i < COLUMN_WIDTH.length && i < COLUMN_NAMES.length; i++) {
            if (COLUMN_WIDTH[i] > 0) {
                mainTable.getColumnModel().getColumn(i).setMaxWidth(COLUMN_WIDTH[i]);
                mainTable.getColumnModel().getColumn(i).setPreferredWidth(COLUMN_WIDTH[i]);
                mainTable.getColumnModel().getColumn(i).setResizable(true);
            }
        }
    }

}
