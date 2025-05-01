/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.tubes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class tambah extends javax.swing.JFrame {

    public tambah() throws SQLException {
        initComponents();
        conn = koneksi.bukaKoneksi();
        loadKolom();
        jtProduct.setModel(model);
        loadProduct();
    }
    
    void bersih(){
    tfCari.setText("Search");
    }
    
    @SuppressWarnings("unchecked")
    private DefaultTableModel model = new DefaultTableModel();
    private Connection conn;
    private ArrayList<produk> daftarproduk;
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtProduct = new javax.swing.JTable();
        bPilih = new javax.swing.JButton();
        tfCari = new javax.swing.JTextField();
        bCari = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        tfidProduct = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfnama = new javax.swing.JTextField();
        tfharga = new javax.swing.JTextField();
        tfJumlah = new javax.swing.JTextField();
        bOke = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        tfnamaProduct = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tfhargaSatuan = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tfstock = new javax.swing.JTextField();
        bSubmit = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        bKembali = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Menu Tambah Stock");

        jtProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "idProduct", "namaProduct", "hargaSatuan", "stock"
            }
        ));
        jScrollPane1.setViewportView(jtProduct);

        bPilih.setBackground(new java.awt.Color(255, 255, 255));
        bPilih.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        bPilih.setForeground(new java.awt.Color(0, 102, 102));
        bPilih.setText("PILIH");
        bPilih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPilihActionPerformed(evt);
            }
        });

        tfCari.setBackground(new java.awt.Color(255, 255, 255));
        tfCari.setForeground(new java.awt.Color(0, 102, 102));
        tfCari.setText("Search");
        tfCari.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfCariFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfCariFocusLost(evt);
            }
        });
        tfCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfCariActionPerformed(evt);
            }
        });

        bCari.setBackground(new java.awt.Color(255, 255, 255));
        bCari.setForeground(new java.awt.Color(0, 102, 102));
        bCari.setText("Cari");
        bCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCariActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tfidProduct.setBackground(new java.awt.Color(255, 255, 255));
        tfidProduct.setForeground(new java.awt.Color(0, 102, 102));
        tfidProduct.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        tfidProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfidProductActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(0, 102, 102));
        jLabel3.setText("Id Product :");

        jLabel5.setForeground(new java.awt.Color(0, 102, 102));
        jLabel5.setText("Nama Product :");

        jLabel6.setForeground(new java.awt.Color(0, 102, 102));
        jLabel6.setText("Harga Satuan :");

        jLabel4.setForeground(new java.awt.Color(0, 102, 102));
        jLabel4.setText("Jumlah     :");

        tfnama.setBackground(new java.awt.Color(255, 255, 255));
        tfnama.setForeground(new java.awt.Color(0, 102, 102));
        tfnama.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));

        tfharga.setBackground(new java.awt.Color(255, 255, 255));
        tfharga.setForeground(new java.awt.Color(0, 102, 102));
        tfharga.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));

        tfJumlah.setBackground(new java.awt.Color(255, 255, 255));
        tfJumlah.setForeground(new java.awt.Color(0, 102, 102));
        tfJumlah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        tfJumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfJumlahActionPerformed(evt);
            }
        });

        bOke.setBackground(new java.awt.Color(0, 102, 102));
        bOke.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        bOke.setForeground(new java.awt.Color(255, 255, 255));
        bOke.setText("SUBMIT");
        bOke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bOkeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(tfharga, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                        .addComponent(tfnama)
                        .addComponent(tfidProduct)
                        .addComponent(tfJumlah))
                    .addComponent(bOke, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfidProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfharga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bOke, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(new java.awt.Color(0, 102, 102));

        tfnamaProduct.setBackground(new java.awt.Color(255, 255, 255));
        tfnamaProduct.setForeground(new java.awt.Color(0, 102, 102));
        tfnamaProduct.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));

        jLabel8.setForeground(new java.awt.Color(0, 102, 102));
        jLabel8.setText("Nama Product :");

        tfhargaSatuan.setBackground(new java.awt.Color(255, 255, 255));
        tfhargaSatuan.setForeground(new java.awt.Color(0, 102, 102));
        tfhargaSatuan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));

        jLabel9.setForeground(new java.awt.Color(0, 102, 102));
        jLabel9.setText("Harga Satuan :");

        jLabel10.setForeground(new java.awt.Color(0, 102, 102));
        jLabel10.setText("Stock :");

        tfstock.setBackground(new java.awt.Color(255, 255, 255));
        tfstock.setForeground(new java.awt.Color(0, 102, 102));
        tfstock.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        tfstock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfstockActionPerformed(evt);
            }
        });

        bSubmit.setBackground(new java.awt.Color(0, 102, 102));
        bSubmit.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        bSubmit.setForeground(new java.awt.Color(255, 255, 255));
        bSubmit.setText("SUBMIT");
        bSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSubmitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(bSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 95, Short.MAX_VALUE))
                    .addComponent(tfnamaProduct)
                    .addComponent(tfhargaSatuan)
                    .addComponent(tfstock, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tfnamaProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tfhargaSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(tfstock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(bSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Menu Tambah Barang");

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("MENU RESTOCK");

        jLabel12.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText(" X");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        bKembali.setBackground(new java.awt.Color(255, 255, 255));
        bKembali.setForeground(new java.awt.Color(0, 102, 102));
        bKembali.setText("Kembali");
        bKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bKembaliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(109, 109, 109)
                                .addComponent(jLabel2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(103, 103, 103)
                                .addComponent(jLabel7))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bKembali)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(tfCari, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bCari, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                            .addComponent(bPilih, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(276, 276, 276)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 37, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tfCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bCari))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(41, 41, 41)
                        .addComponent(bPilih, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tfJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfJumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfJumlahActionPerformed

    private void bOkeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bOkeActionPerformed
        // TODO add your handling code here:
        try {
            String sql = ("SELECT product.idProduct FROM product where product.namaProduct like '%" + tfnama.getText() + "%'; ");
            java.sql.Connection con = (Connection) koneksi.bukaKoneksi();
            java.sql.Statement stm = con.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            if (res.next()) {
                tambahbarang();
                JOptionPane.showMessageDialog(this, "Berhasil");
                tfidProduct.setText("");
                tfnama.setText("");
                tfharga.setText("");
                tfJumlah.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Gagal");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_bOkeActionPerformed

    private void bPilihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPilihActionPerformed
        // TODO add your handling code here:
        int barisTerpilih = jtProduct.getSelectedRow();
        tfidProduct.setText(model.getValueAt(barisTerpilih, 0).toString());
        tfnama.setText(model.getValueAt(barisTerpilih, 1).toString());
        tfharga.setText(model.getValueAt(barisTerpilih, 2).toString());
    }//GEN-LAST:event_bPilihActionPerformed

    private void tfstockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfstockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfstockActionPerformed

    private void bSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSubmitActionPerformed
        // TODO add your handling code here:
        String namaProduct = tfnamaProduct.getText();
        String harga = tfhargaSatuan.getText();
        int hargaSatuan = Integer.parseInt(harga);
        String stoc = tfstock.getText();
        int stock = Integer.parseInt(stoc);
        tambahProduct(namaProduct,hargaSatuan,stock);
        tfnamaProduct.setText("");
        tfhargaSatuan.setText("");
        tfstock.setText("");
    }//GEN-LAST:event_bSubmitActionPerformed

    private void bCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCariActionPerformed
        // TODO add your handling code here:
         try{
            model.setRowCount(0);
            String sql= ("Select idProduct,namaProduct,hargaSatuan,stock from product where namaProduct Like '%" + tfCari.getText() + "%';");
            java.sql.Connection con = (Connection) koneksi.bukaKoneksi();
            java.sql.Statement stm = con.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()){
                model.addRow(new Object[]{
                    res.getString(1), res.getString(2),res.getString(3),res.getString(4)
                });

        }
            jtProduct.setModel(model);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this,e.getMessage());
        }

    }//GEN-LAST:event_bCariActionPerformed

    private void tfidProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfidProductActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfidProductActionPerformed

    private void tfCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCariActionPerformed

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        dispose();
    }//GEN-LAST:event_jLabel12MouseClicked

    private void tfCariFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfCariFocusGained
        String cri = tfCari.getText();
            if(cri.equals("Search")){
            tfCari.setText("");
        }
    }//GEN-LAST:event_tfCariFocusGained

    private void tfCariFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfCariFocusLost
        String cri = tfCari.getText();
            if(cri.equals("") || cri.equals ("Search")){
            tfCari.setText("");
}
    }//GEN-LAST:event_tfCariFocusLost

    private void bKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bKembaliActionPerformed
        // TODO add your handling code here:
        dispose();
        menu.main(null);
    }//GEN-LAST:event_bKembaliActionPerformed
    
    
    private void loadKolom() {
        model.addColumn("Id Product");
        model.addColumn("Nama Product");
        model.addColumn("Harga Satuan");
        model.addColumn("Stock");
    }
    
    private void loadProduct() throws SQLException {
        if (conn != null) {
            daftarproduk = new ArrayList<>();
            String kueri = "SELECT idProduct,namaProduct,hargaSatuan,stock FROM product;";
            try {
                model.setRowCount(0);
                PreparedStatement ps = conn.prepareStatement(kueri);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    String namaProduct = rs.getString("namaProduct");
                    int hargaSatuan = rs.getInt("hargasatuan");
                    int stock = rs.getInt("stock");
                    produk produk = new produk(namaProduct, hargaSatuan,stock);
                    daftarproduk.add(produk);
                    
                    model.addRow(new Object[]{
                        rs.getString("idProduct"), rs.getString("namaProduct"),rs.getString("hargaSatuan"),rs.getString("stock")  
                    });
                }
                rs.close();
                ps.close();
                
                jtProduct.setModel(model);
            } catch (SQLException ex) {
                Logger.getLogger(tambah.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    
    public void NewJFrame() throws SQLException {
        initComponents();
        jtProduct.setModel(model);
        conn = koneksi.bukaKoneksi();
        loadProduct();
    }
    
    private void tambahbarang() {
        try {
            String sql = "SELECT tambahbarang.jumlahIsi,tambahbarang.tanggalTambah,product.idProduct FROM tambahbarang inner join product on tambahbarang.idProduct = product.idProduct";
            java.sql.Connection con = (Connection) koneksi.bukaKoneksi();
            java.sql.Statement stm = con.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            int jumlahIsi = Integer.parseInt(tfJumlah.getText());
            int id = Integer.parseInt(tfidProduct.getText());
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dateAktif = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String tanggalTambah = now.format(dateAktif);

            String kueri = "insert into tambahbarang( jumlahIsi, tanggalTambah, idProduct) values (?,?,?)";
            PreparedStatement pst = conn.prepareStatement(kueri);
            pst.setInt(1, jumlahIsi);
            pst.setString(2, tanggalTambah);
            pst.setInt(3, id);

            pst.executeUpdate();
            System.out.println("tabel tambahbarang sudah diisi");
            updatestok();

        } catch (SQLException ex) {
            Logger.getLogger(tambah.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void updatestok() {
        try {
            
            String sql = "UPDATE product SET stock = ? WHERE idProduct = ?";
            String kueri = "SELECT product.stock from product where product.idProduct like '%" + tfidProduct.getText() + "%';";
            Connection conn = koneksi.bukaKoneksi();

            
            java.sql.Connection con = (Connection) koneksi.bukaKoneksi();
            java.sql.Statement stm = con.createStatement();
            java.sql.ResultSet res = stm.executeQuery(kueri);

            if (res.next()) {  
                String xjumlah = tfJumlah.getText();
                int stock = res.getInt("stock");
                int jumlahIsi = Integer.parseInt(xjumlah);
                stock = stock + jumlahIsi;

                
                PreparedStatement updatePs = conn.prepareStatement(sql);
                updatePs.setInt(1, stock);
                updatePs.setInt(2, Integer.parseInt(tfidProduct.getText()));
                updatePs.executeUpdate();
                loadProduct();

                System.out.println("Stock updated successfully.");
            } else {
                System.out.println("No product found with the given ID.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private void tambahProduct(String namaProduct, int hargaSatuan, int stock) {
        if (conn != null) {          
            try {
                String kueri = "insert into product(namaProduct, hargaSatuan, stock) values (?,?,?)";
                PreparedStatement ps = conn.prepareStatement(kueri);
                ps.setString(1, namaProduct);
                ps.setInt(2, hargaSatuan);
                ps.setInt(3, stock);
                
                int hasil = ps.executeUpdate();
                if (hasil > 0) {
                    produk produk = new produk(namaProduct, hargaSatuan, stock);
                    JOptionPane.showMessageDialog(this, "input berhasil");
                    
                    loadProduct();
                }
            } catch (SQLException ex) {
                Logger.getLogger(tambah.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

     private void cariProduk(String keyword) {
        if (conn != null) {
            daftarproduk = new ArrayList<>();
            String kueri = "Select idProduct,namaProduct,hargaSatuan,stock from product where namaProduct Like ?";
            try {
                PreparedStatement ps = conn.prepareStatement(kueri);
                ps.setString(1, "%" + keyword + "%");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String namaProduct = rs.getString("namaProduct");
                    int hargaSatuan = rs.getInt("hargaSatuan");
                    int stock = rs.getInt("stock");
                    produk produk = new produk(namaProduct, hargaSatuan, stock);
                    daftarproduk.add(produk);
                }
                rs.close();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(tambah.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
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
            java.util.logging.Logger.getLogger(tambah.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tambah.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tambah.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tambah.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new tambah().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(tambah.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCari;
    private javax.swing.JButton bKembali;
    private javax.swing.JButton bOke;
    private javax.swing.JButton bPilih;
    private javax.swing.JButton bSubmit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtProduct;
    private javax.swing.JTextField tfCari;
    private javax.swing.JTextField tfJumlah;
    private javax.swing.JTextField tfharga;
    private javax.swing.JTextField tfhargaSatuan;
    private javax.swing.JTextField tfidProduct;
    private javax.swing.JTextField tfnama;
    private javax.swing.JTextField tfnamaProduct;
    private javax.swing.JTextField tfstock;
    // End of variables declaration//GEN-END:variables
}

class produk {

    private int hargaSatuan,stock;
    private String namaProduct;

    public produk(String namaProduct,int hargaSatuan, int stock ) {
        this.namaProduct = namaProduct;
        this.hargaSatuan = hargaSatuan;
        this.stock = stock;
    }

    public int getHargaSatuan() {
        return hargaSatuan;
    }

    public int getStock() {
        return stock;
    }

    public String getNamaProduct() {
        return namaProduct;
    }
    public void instring(String id){
        id = " "+namaProduct+" "+hargaSatuan+" "+stock;
    }
}