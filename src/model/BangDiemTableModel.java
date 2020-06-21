/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import pojo.Bangdiem;

/**
 *
 * @author HP
 */
public class BangDiemTableModel {
    public DefaultTableModel setTableBangDiem(List<Bangdiem> bd, String[] listCol) {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dtm.setColumnIdentifiers(listCol);
        int nCol = listCol.length;
        Object[] obj = null;
        int rows = bd.size();
        if (rows > 0 ) {
            for (int i = 0; i < rows; i++) {
                Bangdiem temp = bd.get(i);
                obj = new Object[nCol];
                obj[0] = (i+1);
                obj[1] = temp.getId().getMasv();
                obj[2] = temp.getHoten();
                obj[3] = temp.getGiuaki();
                obj[4] = temp.getCuoiki();
                obj[5] = temp.getKhac();
                obj[6] = temp.getDiemtong();
                obj[7] = temp.getDiemtong() >= 5.0 ? "Đậu" : "Rớt";
                dtm.addRow(obj);
            }
        }
        return dtm;
    }
}
