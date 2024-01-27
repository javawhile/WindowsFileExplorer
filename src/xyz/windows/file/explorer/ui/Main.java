package xyz.windows.file.explorer.ui;

import xyz.windows.file.explorer.ui.table.MainTableModel;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import xyz.windows.file.explorer.model.Data;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import xyz.windows.file.explorer.ui.table.MainTableAsyncLoader;
import xyz.windows.file.explorer.utils.Utils;

public class Main extends javax.swing.JFrame implements Refreshable {

    private String path;
    private File file;
    private MainTableModel mainTableModel;
    private MainTableAsyncLoader mainTableAsyncLoader;

    public Main(String path) {
        if(path == null || path.trim().isEmpty()) {
            path = System.getProperty("user.dir");
        }
        initComponents();
        mainTable.addMouseListener(mainTableMouseAdapter);
        start(path);
    }

    private void start(String path) {
        
        //HOLD CURRENT DATA
        String tempPath = this.path;
        File tempFile = this.file;

        //SAVE DATA
        this.path = path;
        this.file = new File(path);

        //DO NOTHING IF FILE CLICKED
        if (this.file.listFiles() == null || this.file.isFile()) {
            //RESTORE DATA
            this.path = tempPath;
            this.file = tempFile;
            return;
        }

        setTitle(path);

        //RAW TABLE MODEL
        List<Data> dataSet = new ArrayList<>();
        for (File file : this.file.listFiles()) {
            Data data = new Data();
            data.setFileObject(file);
            data.setName(file.getName());
            dataSet.add(data);
        }

        //SET TABLE MODEL
        mainTableModel = new MainTableModel(mainTable, dataSet);
        mainTable.setModel(mainTableModel);

        //START ASYNC LOADING
        mainTableAsyncLoader = new MainTableAsyncLoader(this, mainTableModel, dataSet);
        mainTableAsyncLoader.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        mainTable = new javax.swing.JTable();
        parentFolderSizeLabel = new javax.swing.JLabel();
        mainStatusLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        backBtn = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("File Explorer");

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        mainTable.setAutoCreateRowSorter(true);
        mainTable.setBackground(new java.awt.Color(255, 252, 247));
        mainTable.setFont(new java.awt.Font("Candara", 0, 15)); // NOI18N
        mainTable.setFillsViewportHeight(true);
        mainTable.setGridColor(new java.awt.Color(0, 204, 102));
        mainTable.setIntercellSpacing(new java.awt.Dimension(10, 10));
        mainTable.setRowHeight(30);
        mainTable.setSelectionBackground(new java.awt.Color(204, 255, 204));
        mainTable.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(mainTable);

        parentFolderSizeLabel.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        parentFolderSizeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        parentFolderSizeLabel.setText("Status Bar");

        mainStatusLabel.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        mainStatusLabel.setText("Status Bar");

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(mainStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 450, Short.MAX_VALUE)
                        .addComponent(parentFolderSizeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(1, 1, 1))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(parentFolderSizeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mainStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        backBtn.setText("Back");
        backBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backBtnMouseClicked(evt);
            }
        });
        jMenuBar1.add(backBtn);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backBtnMouseClicked
        if (file != null && file.getParentFile() != null) {
            start(file.getParentFile().getAbsolutePath());
        }
    }//GEN-LAST:event_backBtnMouseClicked

    public static void main(String args[]) {
        String pathFromArgs = args != null && args.length >= 1 ? args[0] : null;        

        EventQueue.invokeLater(() -> {
            new Main(pathFromArgs).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu backBtn;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel mainStatusLabel;
    private javax.swing.JTable mainTable;
    private javax.swing.JLabel parentFolderSizeLabel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void refresh(Object... objectsData) {
        if (objectsData != null && objectsData.length >= 1) {
            mainStatusLabel.setForeground(new Color(0, 156, 0));
            mainStatusLabel.setText("Loaded Successfully");

            long parentSizeInBytes = Long.valueOf(objectsData[0].toString());
            parentFolderSizeLabel.setText("Directory Size:  " + Utils.getReadableBytes(parentSizeInBytes));
        } else {
            mainStatusLabel.setForeground(new Color(0, 0, 255));
            int threadCount = Thread.activeCount();
            mainStatusLabel.setText(threadCount + " Active Threads. In Progress");
        }

    }

    private final MouseAdapter mainTableMouseAdapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent me) {
            if (me.getClickCount() == 2) {
                int rowNum = mainTable.getSelectedRow();
                int colNum = 1;
                String path = Main.this.path + "/" + mainTable.getModel().getValueAt(rowNum, colNum);

                start(path);
            }
        }

    };

}
