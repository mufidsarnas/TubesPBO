/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.tubes;
import com.mycompany.tubes.koneksi;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gwild
 */

public class penjualan extends javax.swing.JFrame {

    /**
     * Creates new form penjualan
     */
    public penjualan() {
        initComponents();
        conn = koneksi.bukaKoneksi();
        dtb();
        detail = new ArrayList<>();
        loadItem();
    }
    
//    void bersih(){
//        tfSearch.setText("Search");
//        tfPay.setText("0");
//}
    
    public void dtb(){
        products = new ArrayList<>();
//            String item = "";
        
        try {
            java.sql.Connection con = (Connection) koneksi.bukaKoneksi();
            if(con.isClosed()){
                koneksi.conn = null;
                con = (Connection) koneksi.bukaKoneksi();
            }
            java.sql.Statement st = con.createStatement();
            String query = "SELECT idProduct, namaProduct, hargaSatuan, stock FROM `product` where stock > 0";
            java.sql.ResultSet rs = st.executeQuery(query);
//              Statement st = conn.createStatement();
//              ResultSet rs = st.executeQuery(query);
            
            while(rs.next()){
                String namaProduct = rs.getString("namaProduct");
                int idbarang = rs.getInt("idProduct");
                int hargaSatuan = rs.getInt("hargaSatuan");
                int stock = rs.getInt("stock");

                Product product = new Product(namaProduct, idbarang ,hargaSatuan, stock);
                products.add(product);

            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(penjualan.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    
    public void loadItem() {
        Dimension buttonSize = new Dimension(100, 100);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10)); // FlowLayout with left alignment and spacing
        int xbtncount = products.size();
        for (Product item : products) {

            String buttonText = String.format("<html>%s<br/>Price: %d<br/>Stock: %d</html>", 
                                              item.getNamaProduct(), 
                                              item.getHargaSatuan(),
                                              item.getStock());
            
            JButton button = new JButton(buttonText);
            buttonPanel.setPreferredSize(new Dimension(200, xbtncount * 40));
            button.setPreferredSize(buttonSize);    
            buttonPanel.add(button);
            
            button.addActionListener(new ActionListener() {
                
                public void actionPerformed(ActionEvent e) {
                    int xstock = item.getStock() - 1;
                    if(xstock < 0){
                        JOptionPane.showMessageDialog(new penjualan(),"Stock Tidak Mencukupi");
                        return;
                    }
                    item.setStock(xstock);
                    String buttonText = String.format("<html>%s<br/>Price: %d<br/>Stock: %d</html>", 
                                              item.getNamaProduct(), 
                                              item.getHargaSatuan(),
                                              item.getStock());
                    button.setText(buttonText);
                    masukDetail(item);
                    loadDetail();
                }
            });
        }
        
        jScrollPane1.setViewportView(buttonPanel); // Set the button panel to the scroll pane
        jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); // For never showing horizontal scrollbar
        jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); // For showing horizontal scrollbar as needed
    }
    
    public void loadDetail() {
                model.setColumnIdentifiers(new Object[]{"Barang", "Jumlah", "Total Harga"});
                model.setRowCount(0);
                
                int xsum = 0;

                
                for (listDetail item : detail) {
                    int totalHarga = item.getHarga() * item.getQty();
                    model.addRow(new Object[]{
                        item.getNamaProduct(), item.getQty(), totalHarga
                    });
                    xsum += totalHarga;
                }
                jTable2.setModel(model);
                tfTotal.setText(Integer.toString(xsum));
            }
    
    
    public void masukDetail(Product item) {
        boolean xhit = false;
        for (listDetail x : detail) {
            if(x.getIdbarang() == item.getIdbarang()){
                xhit = true;
                
                x.setQty(x.getQty() + 1);
                break;
            } 
        }
        if(!xhit){
            listDetail xdetail = new listDetail(item.getNamaProduct(), item.getIdbarang(), item.getHargaSatuan(), 1);
            detail.add(xdetail);
        }
    }

    private void search(String keyword) {
        products = new ArrayList<>();
//            String item = "";
                try {
            Statement st = conn.createStatement();
            String query = "SELECT idProduct, namaProduct, hargaSatuan, stock FROM `product` where namaProduct like '%"+keyword+"%' ";
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next()){
                String namaProduct = rs.getString("namaProduct");
                int idbarang = rs.getInt("idProduct");
                int hargaSatuan = rs.getInt("hargaSatuan");
                int stock = rs.getInt("stock");
                
                Product product = new Product(namaProduct, idbarang ,hargaSatuan, stock);
                products.add(product);
                
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(penjualan.class.getName()).log(Level.SEVERE, null, ex);
        }
            loadItem();
    }    
    
    private void insertData(){
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);

        String insertTransaksiSQL = "INSERT INTO transaksi (orderDate, priceTotal, idAccount) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        ResultSet rs = null;
        int lastID = 0;

    try {
        ps = conn.prepareStatement(insertTransaksiSQL, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, formattedDate);
        ps.setInt(2, Integer.parseInt(tfTotal.getText()));
        ps.setInt(3, session1.idAccount);
        ps.executeUpdate();

        
        rs = ps.getGeneratedKeys();
        if (rs.next()) {
            lastID = rs.getInt(1);
        }

    ps.close();

    String insertPaymentDetailsSQL = "INSERT INTO payment (paymentDate, amount, idAccount) VALUES (?, ?, ?)";
        ps = conn.prepareStatement(insertPaymentDetailsSQL);
        ps.setString(1, formattedDate);
        ps.setInt(2, Integer.parseInt(tfPay.getText()));
        ps.setInt(3, session1.idAccount);
        ps.executeUpdate();
        ps.close();

    
    for (listDetail q : detail) {
        String insertOrderDetailsSQL = "INSERT INTO orderdetails (quantityOrder, priceTotal, idProduct, idTransaksi) VALUES (?, ?, ?, ?)";
        ps = conn.prepareStatement(insertOrderDetailsSQL);
        ps.setInt(1, q.getQty());
        ps.setInt(2, q.getHarga());
        ps.setInt(3, q.getIdbarang());
        ps.setInt(4, lastID);
        ps.executeUpdate();
        ps.close();

        String updateProductSQL = "UPDATE product SET stock = stock - ? WHERE idProduct = ?";
        ps = conn.prepareStatement(updateProductSQL);
        ps.setInt(1, q.getQty());
        ps.setInt(2, q.getIdbarang());
        ps.executeUpdate();
        ps.close();

        
    }
    rs.close();
    ps.close();
    JOptionPane.showMessageDialog(this,"Success");
    dispose();
    struk.main(null);
//    dispose();
//    main(null);
    } catch (SQLException e) {
        e.printStackTrace();
    } 
}
    
    private boolean validasi(){
        if(detail.size() == 0){
            JOptionPane.showMessageDialog(this,"Masukkan Barang");
            return false;
        }
        double xpay = 0;
        double xtotal = 0;
        if(!tfPay.getText().isEmpty()){
            xpay = Double.parseDouble((tfPay.getText()));
        }
        if(!tfTotal.getText().isEmpty()){
            xtotal = Double.parseDouble((tfTotal.getText()));
        }
        double xcount = xpay - xtotal;
        if (xpay < xtotal) {
            JOptionPane.showMessageDialog(this,"Uang Tidak Cukup");
            return false;
        }
        
        String xScount = String.valueOf(xcount);
        tfChange.setText(xScount);
        return true;
    }
 
    /*
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")  
//    private JButton button = new JButton();
    private DefaultTableModel model = new DefaultTableModel();
    private Connection conn;
    private ArrayList<Product> products;
    private ArrayList<listDetail> detail;
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tfTotal = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tfPay = new javax.swing.JTextField();
        tfChange = new javax.swing.JTextField();
        bSubmit = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        tfSearch = new javax.swing.JTextField();
        Reset = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        bKembali = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setForeground(new java.awt.Color(255, 255, 255));

        jTable2.setBackground(new java.awt.Color(255, 255, 255));
        jTable2.setForeground(new java.awt.Color(0, 0, 0));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Barang", "Jumlah", "Total Harga"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1514, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 714, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel2);

        jLabel5.setForeground(new java.awt.Color(0, 102, 102));
        jLabel5.setText("Total : ");

        jLabel7.setForeground(new java.awt.Color(0, 102, 102));
        jLabel7.setText("Pay : ");

        tfTotal.setBackground(new java.awt.Color(255, 255, 255));
        tfTotal.setForeground(new java.awt.Color(0, 102, 102));
        tfTotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        tfTotal.setEnabled(false);
        tfTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTotalActionPerformed(evt);
            }
        });

        jLabel8.setForeground(new java.awt.Color(0, 102, 102));
        jLabel8.setText("Change : ");

        tfPay.setBackground(new java.awt.Color(255, 255, 255));
        tfPay.setForeground(new java.awt.Color(0, 102, 102));
        tfPay.setText("0");
        tfPay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        tfPay.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        tfPay.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfPayFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfPayFocusLost(evt);
            }
        });
        tfPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPayActionPerformed(evt);
            }
        });
        tfPay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfPayKeyPressed(evt);
            }
        });

        tfChange.setBackground(new java.awt.Color(255, 255, 255));
        tfChange.setForeground(new java.awt.Color(0, 102, 102));
        tfChange.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        tfChange.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        tfChange.setEnabled(false);
        tfChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfChangeActionPerformed(evt);
            }
        });

        bSubmit.setBackground(new java.awt.Color(0, 102, 102));
        bSubmit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bSubmit.setForeground(new java.awt.Color(255, 255, 255));
        bSubmit.setText("SUBMIT");
        bSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSubmitActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 102, 102));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("+1");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        tfSearch.setBackground(new java.awt.Color(255, 255, 255));
        tfSearch.setForeground(new java.awt.Color(0, 102, 102));
        tfSearch.setText("Search");
        tfSearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102), 2));
        tfSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfSearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfSearchFocusLost(evt);
            }
        });
        tfSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSearchActionPerformed(evt);
            }
        });
        tfSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfSearchKeyPressed(evt);
            }
        });

        Reset.setBackground(new java.awt.Color(0, 102, 102));
        Reset.setForeground(new java.awt.Color(255, 255, 255));
        Reset.setText("Reset");
        Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 102, 102));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("-1");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 102, 102));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Cari");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 102, 102));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("MENU KASIR");

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText(" X");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(448, 448, 448)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel1)))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(0, 102, 102));

        bKembali.setBackground(new java.awt.Color(255, 255, 255));
        bKembali.setForeground(new java.awt.Color(0, 102, 102));
        bKembali.setText("Kembali");
        bKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bKembaliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bKembali)
                .addContainerGap(1130, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bKembali, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel5))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tfPay, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfChange, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Reset, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(bSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(Reset)
                            .addComponent(jButton2)
                            .addComponent(tfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(tfPay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfChange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(51, 51, 51)
                        .addComponent(bSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 732, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tfSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSearchActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tfSearchActionPerformed

    private void ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetActionPerformed
        // TODO add your handling code here:
        dtb();
        detail = new ArrayList<>();
        loadItem();
        model.setRowCount(0);
        jTable2.setModel(model);
        detail.clear();
        tfTotal.setText("0");
    }//GEN-LAST:event_ResetActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int xindex = jTable2.getSelectedRow();
        if(xindex < 0) {
            JOptionPane.showMessageDialog(this,"Select Data");
            return;
        }
        listDetail qdata = detail.get(xindex);
        int xqty = qdata.getQty();
        
        for (Product x : products) {
            if(x.getIdbarang() == qdata.getIdbarang()){
                int xstock = x.getStock() - 1;
                if(xstock < 0){
                    JOptionPane.showMessageDialog(this,"Stock Habis");
                    return;
                }
                x.setStock(xstock);
                loadItem();
                break;
            }
        }
        
        
        detail.get(xindex).setQty(xqty + 1);
        loadDetail();
        jTable2.setRowSelectionInterval(xindex, xindex);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
        int xindex = jTable2.getSelectedRow();
        if(xindex < 0) {
            JOptionPane.showMessageDialog(this,"Select Data");
            return;
        }
        
        listDetail qdata = detail.get(xindex); 
        int xqty = qdata.getQty();
        
        for (Product x : products) {
            if(x.getIdbarang() == qdata.getIdbarang()){
                x.setStock(x.getStock() + 1);
                loadItem();
                break;
            }
        }
        
        
        if(xqty == 1){
            detail.remove(xindex);
            loadDetail();
            return;
        }
        detail.get(xindex).setQty(xqty - 1);
        loadDetail();
        jTable2.setRowSelectionInterval(xindex, xindex);
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tfTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTotalActionPerformed

    private void tfPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPayActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tfPayActionPerformed

    private void tfChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfChangeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfChangeActionPerformed

    private void tfPayKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPayKeyPressed
        // TODO add your handling code here:
        tfPay.addKeyListener(new KeyAdapter() {
           public void keyTyped(KeyEvent e) {
             char c = e.getKeyChar();
             if (!((c >= '0') && (c <= '9') ||
                (c == KeyEvent.VK_BACK_SPACE) ||
                (c == KeyEvent.VK_DELETE))) {
               getToolkit().beep();
               e.consume();
             }
           }
         });
    }//GEN-LAST:event_tfPayKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        validasi();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tfSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSearchKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSearchKeyPressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        search(tfSearch.getText());
    }//GEN-LAST:event_jButton4ActionPerformed

    private void bSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSubmitActionPerformed
        // TODO add your handling code here:
        if(validasi()){
            insertData();
        }

    }//GEN-LAST:event_bSubmitActionPerformed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        dispose();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void tfSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfSearchFocusGained
        String src = tfSearch.getText();
            if(src.equals("Search")){
            tfSearch.setText("");
        }
    }//GEN-LAST:event_tfSearchFocusGained

    private void tfSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfSearchFocusLost
       String src = tfSearch.getText();
            if(src.equals("") || src.equals ("Search")){
            tfSearch.setText("");
        }
    }//GEN-LAST:event_tfSearchFocusLost

    private void tfPayFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfPayFocusGained
        String pay = tfPay.getText();
            if(pay.equals("0")){
            tfPay.setText("");
}
    }//GEN-LAST:event_tfPayFocusGained

    private void tfPayFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfPayFocusLost
        String pay = tfPay.getText();
            if(pay.equals("") || pay.equals ("0")){
            tfPay.setText("0");
}
    }//GEN-LAST:event_tfPayFocusLost

    private void bKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bKembaliActionPerformed
        // TODO add your handling code here:
        dispose();
        menu.main(null);
    }//GEN-LAST:event_bKembaliActionPerformed

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
            java.util.logging.Logger.getLogger(penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new penjualan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Reset;
    private javax.swing.JButton bKembali;
    private javax.swing.JButton bSubmit;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField tfChange;
    private javax.swing.JTextField tfPay;
    private javax.swing.JTextField tfSearch;
    private javax.swing.JTextField tfTotal;
    // End of variables declaration//GEN-END:variables
}

class Product {
    private String namaProduct;
    private int idbarang, hargaSatuan, stock;
    
    public Product(String namaProduct,int idbarang ,int hargaSatuan, int stock){
        this.namaProduct = namaProduct;
        this.idbarang = idbarang;
        this.hargaSatuan = hargaSatuan;
        this.stock = stock;
    }
    
    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getIdbarang() {
        return idbarang;
    }

    public String getNamaProduct() {
        return namaProduct;
    }

    public int getHargaSatuan() {
        return hargaSatuan;
    }

    public int getStock() {
        return stock;
    }
    
}

class listDetail {
    private String namaProduct;
    private int idbarang, harga, qty;
    
    public listDetail(String namaProduct,int idbarang ,int harga, int qty){
        this.namaProduct = namaProduct;
        this.idbarang = idbarang;
        this.harga = harga;
        this.qty = qty;
    }

    public void setNamaProduct(String namaProduct) {
        this.namaProduct = namaProduct;
    }

    public void setIdbarang(int idbarang) {
        this.idbarang = idbarang;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
    
    public int getIdbarang() {
        return idbarang;
    }

    public String getNamaProduct() {
        return namaProduct;
    }

    public int getHarga() {
        return harga;
    }

    public int getQty() {
        return qty;
    }
    
}

