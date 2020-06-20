/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import pojo.Sinhvien;

/**
 *
 * @author HP
 */
public class ClassTableModel {
    public DefaultTableModel setTableSinhVien(List<Sinhvien> dsSV, String[] listCol) {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dtm.setColumnIdentifiers(listCol);
        int nCol = listCol.length;
        Object[] obj = null;
        int rows = dsSV.size();
        if (rows > 0 ) {
            for (int i = 0; i < rows; i++) {
                Sinhvien sv = dsSV.get(i);
                obj = new Object[nCol];
                obj[0] = (i+1);
                obj[1] = sv.getMasv();
                obj[2] = sv.getHoten();
                obj[3] = sv.getGioitinh();
                obj[4] = sv.getCmnd();
                obj[5] = sv.getLop().getMalop();
                dtm.addRow(obj);
            }
        }
        return dtm;
    }
}
