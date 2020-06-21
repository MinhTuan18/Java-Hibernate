/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BangdiemDAO;
import dao.LopDAO;
import dao.MonhocDAO;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import model.BangDiemTableModel;
import pojo.Bangdiem;
import pojo.BangdiemId;
import pojo.Monhoc;
import view.ImportBangDiemCSVJFrame;

/**
 *
 * @author HP
 */
public class BangDiemController {
    private JButton importCSVBtn;
    private JPanel jPanel1;
    private JComboBox lopCBB1;
    private JComboBox lopCBB2;
   
    private JPanel viewPanel;
    
    private BangDiemTableModel tableModel = null;
    
    private String[] columns = {"STT", "MSSV", "Họ tên", "Điểm GK", "Điểm CK", "Điểm khác", "Điểm tổng", "Kết quả"};
    
    private TableRowSorter<TableModel> rowSorter = null;

    public BangDiemController(JButton importCSVBtn, JPanel jPanel1, JComboBox lopCBB1, JComboBox lopCBB2, JPanel viewPanel) {
        this.importCSVBtn = importCSVBtn;
        this.jPanel1 = jPanel1;
        this.lopCBB1 = lopCBB1;
        this.lopCBB2 = lopCBB2;
        this.viewPanel = viewPanel;
        this.tableModel = new BangDiemTableModel();
    }
    
    public void setDaTaToComboBox1() {
        lopCBB1.removeAllItems();
        List <String> dsLop = LopDAO.layDanhSachLop();
        for (int i = 0; i< dsLop.size(); i++ ) {
            lopCBB1.addItem(dsLop.get(i));
        }
        lopCBB1.setSelectedIndex(-1);
        
        lopCBB2.removeAllItems(); 
    }
    
    public void setDataToTable(String malop, String mamon) {
        List<Bangdiem> bd = BangdiemDAO.layBangDiem(malop, mamon);
        
        
        DefaultTableModel model = tableModel.setTableBangDiem(bd, columns);
        JTable table = new JTable(model);
        
        rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);
        
        
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    DefaultTableModel mode = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();
                    selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);
                    
                    Bangdiem bd = new Bangdiem();
                    BangdiemId bdId = new BangdiemId((String) model.getValueAt(selectedRowIndex, 1), lopCBB1.getSelectedItem().toString(), lopCBB2.getSelectedItem().toString());
                    bd.setId(bdId);
                    bd.setHoten((String) model.getValueAt(selectedRowIndex, 2));
                    bd.setGiuaki((double) model.getValueAt(selectedRowIndex, 3));
                    bd.setCuoiki((double) model.getValueAt(selectedRowIndex, 4));
                    bd.setKhac((double)model.getValueAt(selectedRowIndex, 5));
                    bd.setDiemtong((double) model.getValueAt(selectedRowIndex, 6));
                    //ThemSVJFrame frame = new ThemSVJFrame(sv, false);
                    
                    
                    //frame.setVisible(true);
                    
                }
                
            }
            
      
            
        });
        // design
        table.getColumnModel().getColumn(0).setMaxWidth(80);
        table.getColumnModel().getColumn(0).setMinWidth(80);
        table.getColumnModel().getColumn(0).setPreferredWidth(30);
        
        table.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 50));
        table.setRowHeight(50);
        table.validate();
        table.repaint();
        
        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().add(table);
        scroll.setPreferredSize(new Dimension(1350, 400));
        viewPanel.removeAll();
        viewPanel.setLayout(new CardLayout());
        viewPanel.add(scroll);
        viewPanel.validate();
        viewPanel.repaint();
    }
    
    public void setDataToComboBox2(String malop) {
        lopCBB2.removeAllItems();
        List <Monhoc> ds = MonhocDAO.layDanhSachMonHoc(malop);
        for (int i = 0; i< ds.size(); i++ ) {
            lopCBB2.addItem(ds.get(i).getMamon() + "-" + ds.get(i).getTenmon());
        }
        //lopCBB2.setSelectedIndex(-1);
    }
    
    public void setEvent() {
        importCSVBtn.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                new ImportBangDiemCSVJFrame().setVisible(true);
            }
            
        });
        lopCBB1.setAutoscrolls(true);
        lopCBB1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(lopCBB1.getSelectedIndex() != -1) {
                    String malop = lopCBB1.getSelectedItem().toString();
                    setDataToComboBox2(malop);
                }
            }
        });
        
        lopCBB2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(lopCBB2.getSelectedIndex() != -1) {
                    String mamon = lopCBB2.getSelectedItem().toString().split("-")[0];
                    System.out.printf(mamon);
                    setDataToTable(lopCBB1.getSelectedItem().toString(), mamon);
                }
            }
        });
        
    }
}
