/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;
import assets.TableActionCellEditor;
import assets.TableActionCellRender;
import assets.TableActionEvent;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.Connector;
import model.DataModel;

/**
 *
 * @author LENOVO
 */
public class DataView extends javax.swing.JFrame {
private DefaultTableModel model;
DataModel dataModel = new DataModel();
    /**
     * Creates new form DataView
     */
    public DataView() {
        initComponents();
        dataMhs();
        dataAdmin();
        
    }
        
    private void dataMhs(){
        String sql = "SELECT * FROM mhs";
        Object header[] = {"Nama","NIM","Analisa Algoritma","AOK","OPK","PBO","PKN","Prak PBO","Prak SCPK","RPL","SCPK","Techno","IPS","Action"};
        int head = header.length;
        model = new DefaultTableModel(null,header);
        
        //digunakan untuk memberi heading / judul pada kolom di tabel
        tabel_data.setModel(model);
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                InsertDataView insertView = new InsertDataView();
                TableModel tm = tabel_data.getModel();

                String nama = tm.getValueAt(row, 0).toString();
                String nim = tm.getValueAt(row, 1).toString();
                String aa = tm.getValueAt(row, 2).toString();
                String aok = tm.getValueAt(row, 3).toString();
                String opk = tm.getValueAt(row, 4).toString();
                String pbo = tm.getValueAt(row, 5).toString();
                String pkn = tm.getValueAt(row, 6).toString();
                String ppbo = tm.getValueAt(row, 7).toString();
                String pscpk = tm.getValueAt(row, 8).toString();
                String rpl = tm.getValueAt(row, 9).toString();
                String scpk = tm.getValueAt(row, 10).toString();
                String tech = tm.getValueAt(row, 11).toString();
                
                String read[]={aa, aok, opk, pbo, pkn, ppbo, pscpk, rpl, scpk, tech};
                int nilai_index[] = {0,0,0,0,0,0,0,0,0,0};
                for (int i = 0; i < 10; i++) {
                    switch (read[i]) {
                        case "A":
                            nilai_index[i] = 1;
                            break;
                        case "B+":
                            nilai_index[i] = 2;
                            break;
                        case "B":
                            nilai_index[i] = 3;
                            break;
                        case "C+":
                            nilai_index[i] = 4;
                            break;
                        case "C":
                            nilai_index[i] = 5;
                            break;
                        case "D":
                            nilai_index[i] = 6;
                            break;
                        case "E":
                            nilai_index[i] = 7;
                            break;
                        default:
                            nilai_index[i] = 0;
                    }
                }
                
                insertView.InputNIM.setText(nim);
                insertView.InputNama.setText(nama);
                insertView.AA.setSelectedIndex(nilai_index[0]);
                insertView.AOK.setSelectedIndex(nilai_index[1]);
                insertView.OPK.setSelectedIndex(nilai_index[2]);
                insertView.PBO.setSelectedIndex(nilai_index[3]);
                insertView.PKN.setSelectedIndex(nilai_index[4]);
                insertView.PPBO.setSelectedIndex(nilai_index[5]);
                insertView.PSCPK.setSelectedIndex(nilai_index[6]);
                insertView.RPL.setSelectedIndex(nilai_index[7]);
                insertView.SCPK.setSelectedIndex(nilai_index[8]);
                insertView.TECHNO.setSelectedIndex(nilai_index[9]);
                insertView.TambahData.setText("Update");

                insertView.InputNIM.setEditable(false);
                insertView.Reset.setVisible(false);
                insertView.setVisible(true);
                dispose();
            }

            @Override
            public void onDelete(int row) {
                if(tabel_data.isEditing()) {
                    tabel_data.getCellEditor().stopCellEditing();
                }
                TableModel tm = tabel_data.getModel();
                Connector connect = new Connector();
                String nim = tm.getValueAt(row, 1).toString();
                if(tabel_data.isEditing()) {
                    tabel_data.getCellEditor().stopCellEditing();
                }
                try {
                    String query = "DELETE FROM `mhs` WHERE `nim`='" + nim + "'";
                    Statement st = connect.koneksi.createStatement();
                    st.executeUpdate(query);
                    st.close();

                    JOptionPane.showMessageDialog(null, "Delete Success");
                } catch (Exception e) {
                    System.out.println("Error : " + e.getMessage());
                    JOptionPane.showMessageDialog(null, "Delete Failed");
                }
                DefaultTableModel model1 = (DefaultTableModel)tabel_data.getModel();
                model1.removeRow(row);
            }
        };
        tabel_data.getColumnModel().getColumn(13).setCellRenderer(new TableActionCellRender());
        tabel_data.getColumnModel().getColumn(13).setCellEditor(new TableActionCellEditor(event));
        
        dataModel.getData(sql, head, model);
    }
    
    private void dataAdmin(){
        String sql = "SELECT nama, username, password FROM users";
        Object header[] = {"Nama","Username","Password", "Action"};
        int head = header.length;
        model = new DefaultTableModel(null,header);
        
        //digunakan untuk memberi heading / judul pada kolom di tabel
        tabel_admin.setModel(model);
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                EditAdminView adminView = new EditAdminView();
                TableModel tm = tabel_admin.getModel();

                String nama = tm.getValueAt(row, 0).toString();
                String username = tm.getValueAt(row, 1).toString();
                String password = tm.getValueAt(row, 2).toString();

                adminView.nama.setText(nama);
                adminView.username.setText(username);
                adminView.password.setText(password);
                
                adminView.username.setEnabled(false);

                adminView.setVisible(true);
                dispose();
            }

            @Override
            public void onDelete(int row) {
                TableModel tm = tabel_admin.getModel();
                Connector connect = new Connector();
                String username = tm.getValueAt(row, 1).toString();
                if(tabel_admin.isEditing()) {
                    tabel_admin.getCellEditor().stopCellEditing();
                }
                try {
                    String query = "DELETE FROM `users` WHERE `username`='" + username + "'";
                    Statement st = connect.koneksi.createStatement();
                    st.executeUpdate(query);
                    st.close();

                    JOptionPane.showMessageDialog(null, "Delete Success");
                } catch (Exception e) {
                    System.out.println("Error : " + e.getMessage());
                    JOptionPane.showMessageDialog(null, "Delete Failed");
                }
                DefaultTableModel model1 = (DefaultTableModel)tabel_admin.getModel();
                model1.removeRow(row);
            }
        };  
        tabel_admin.getColumnModel().getColumn(3).setCellRenderer(new TableActionCellRender());
        tabel_admin.getColumnModel().getColumn(3).setCellEditor(new TableActionCellEditor(event));
        
        dataModel.getData(sql, head, model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnMenu = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_data = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabel_admin = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(16, 24, 32));

        jPanel2.setBackground(new java.awt.Color(16, 24, 32));

        jPanel3.setBackground(new java.awt.Color(16, 24, 32));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(254, 231, 21), 3, true));
        jPanel3.setToolTipText("");

        jLabel1.setBackground(new java.awt.Color(16, 24, 32));
        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Daftar Nilai Mahasiswa");

        btnMenu.setBackground(new java.awt.Color(255, 255, 255));
        btnMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/home.png"))); // NOI18N
        btnMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMenu.setPreferredSize(new java.awt.Dimension(50, 50));
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(255, 255, 255))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 38, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(16, 24, 32));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(254, 231, 21), 3, true));

        jTabbedPane1.setBackground(new java.awt.Color(16, 24, 32));
        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1020, 458));

        jScrollPane1.setBorder(null);

        tabel_data.setBackground(new java.awt.Color(255, 255, 255));
        tabel_data.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        tabel_data.setForeground(new java.awt.Color(0, 0, 0));
        tabel_data.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "NIM", "Nama", "Analisa Algoritma", "AOK", "OPK", "PBO", "PKN", "Prak PBO", "Prak SCPK", "RPL", "SCPK", "Techno", "IPS", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabel_data.setFocusable(false);
        tabel_data.setGridColor(new java.awt.Color(16, 24, 32));
        tabel_data.setRowHeight(40);
        tabel_data.setSelectionBackground(new java.awt.Color(0, 0, 0));
        tabel_data.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tabel_data.setShowGrid(true);
        jScrollPane1.setViewportView(tabel_data);

        jTabbedPane1.addTab("Mahasiswa", jScrollPane1);

        jScrollPane3.setBorder(null);
        jScrollPane3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tabel_admin.setBackground(new java.awt.Color(255, 255, 255));
        tabel_admin.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        tabel_admin.setForeground(new java.awt.Color(0, 0, 0));
        tabel_admin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nama", "Username", "Password", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabel_admin.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tabel_admin.setGridColor(new java.awt.Color(16, 24, 32));
        tabel_admin.setRowHeight(50);
        tabel_admin.setSelectionBackground(new java.awt.Color(0, 0, 0));
        tabel_admin.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tabel_admin.setShowGrid(true);
        jScrollPane3.setViewportView(tabel_admin);
        if (tabel_admin.getColumnModel().getColumnCount() > 0) {
            tabel_admin.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1020, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1020, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 423, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Admin", jPanel1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        // TODO add your handling code here:
        new MenuView().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnMenuActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DataView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DataView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JTable tabel_admin;
    private javax.swing.JTable tabel_data;
    // End of variables declaration//GEN-END:variables
}
