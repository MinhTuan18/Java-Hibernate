/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import model.MonHocTableModel;
import pojo.Monhoc;
import view.ImportMonHocCSVJFrame;

/**
 *
 * @author HP
 */
public class MonHocController {
    private JComboBox lopCBB;
    private JButton addBtn;
    private JPanel viewPanel;
    
    private MonHocTableModel tableModel = null;
    
    private String[] columns = {"STT", "Mã môn", "Tên môn", "Phòng học", "Lớp"};
    
    private TableRowSorter<TableModel> rowSorter = null;

    public MonHocController(JComboBox lopCBB, JButton addBtn, JPanel viewPanel) {
        this.lopCBB = lopCBB;
        this.addBtn = addBtn;
        this.viewPanel = viewPanel;
        this.tableModel = new MonHocTableModel();
    }

    
    
    public void setDataToTable(String malop) {
        List<Monhoc> dsMH;
        if (malop == "all") {
            dsMH = MonhocDAO.layDanhSachMonHoc();
        } else {
            dsMH = MonhocDAO.layDanhSachMonHoc(malop);
        }
        DefaultTableModel model = tableModel.setTableMonhoc(dsMH, columns);
        JTable table = new JTable(model);
        
        rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);
        
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
    
    public void setDataToComboBox() {
        lopCBB.removeAllItems();
        List <String> dsLop = LopDAO.layDanhSachLop();
        for (int i = 0; i< dsLop.size(); i++ ) {
            lopCBB.addItem(dsLop.get(i));
        }
        lopCBB.setSelectedIndex(-1);
    }
    
    public void setEvent() {
        addBtn.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                new ImportMonHocCSVJFrame().setVisible(true);
            }
            
        });
        lopCBB.setAutoscrolls(true);
        lopCBB.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(lopCBB.getSelectedIndex() != -1) {
                    String malop = lopCBB.getSelectedItem().toString();
                    setDataToTable(malop);
                }
            }
        });
        
    }
}
