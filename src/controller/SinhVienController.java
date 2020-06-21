/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.LopDAO;
import dao.SinhvienDAO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import java.util.List;
import javafx.scene.control.TableRow;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import model.SinhVienTableModel;
import pojo.Lop;
import pojo.Sinhvien;
import view.ImportSinhVienCSVJFrame;
import view.ThemSVJFrame;

/**
 *
 * @author HP
 */
public class SinhVienController {
    private JPanel viewPanel;
    private JTextField searchTF;
    private JComboBox lopCBB;
    private JButton addBtn;
    private JButton importCSVBtn;
    
    private SinhVienTableModel tableModel = null;
    
    private  String[] columns = {"STT", "MSSV", "Họ tên", "Giới tính",
        "CMND", "Lớp"};

    private TableRowSorter<TableModel> rowSorter = null;

    public SinhVienController(JPanel viewPanel, JTextField searchTF, JComboBox lopCBB, JButton addBtn, JButton importCSVBtn) {
        this.viewPanel = viewPanel;
        this.searchTF = searchTF;
        this.lopCBB = lopCBB;
        this.addBtn = addBtn;
        this.importCSVBtn = importCSVBtn;
        this.tableModel = new SinhVienTableModel();
    }
    
    

    public void setDaTaToComboBox() {
        lopCBB.removeAllItems();
        List <String> dsLop = LopDAO.layDanhSachLop();
        for (int i = 0; i< dsLop.size(); i++ ) {
            lopCBB.addItem(dsLop.get(i));
        }
        lopCBB.setSelectedIndex(-1);
        
        
    }

    public void setDataToTable() {
        List<Sinhvien> dsSV = SinhvienDAO.layDanhSachSinhVien();
        DefaultTableModel model = tableModel.setTableSinhVien(dsSV, columns);
        JTable table = new JTable(model);
        
        rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);
        
        searchTF.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = searchTF.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = searchTF.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                
            }
        });
        
        
        // design
        table.getColumnModel().getColumn(0).setMaxWidth(80);
        table.getColumnModel().getColumn(0).setMinWidth(80);
        table.getColumnModel().getColumn(0).setPreferredWidth(30);
        
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    DefaultTableModel mode = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();
                    selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);
                    
                    Sinhvien sv = new Sinhvien();
                    sv.setMasv((String) model.getValueAt(selectedRowIndex, 1));
                    sv.setHoten((String) model.getValueAt(selectedRowIndex, 2));
                    sv.setGioitinh((String) model.getValueAt(selectedRowIndex, 3));
                    sv.setCmnd((String) model.getValueAt(selectedRowIndex, 4));
                    Lop lop = new Lop((String) model.getValueAt(selectedRowIndex, 5));
                    sv.setLop(lop);
                    
                    ThemSVJFrame frame = new ThemSVJFrame(sv, false);
                    
                    
                    frame.setVisible(true);
                    
                }
                
            }
            
      
            
        });
        
        
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
    
    public void setEvent() {
        addBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ThemSVJFrame(new Sinhvien(), true).setVisible(true);
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
}       );
        importCSVBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ImportSinhVienCSVJFrame().setVisible(true);
                
            }
        });
        
    }
}
