/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.SinhvienDAO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import pojo.Lop;
import pojo.Sinhvien;

/**
 *
 * @author HP
 */
public class ThemSinhVienController {
    private JTextField maSVTF;
    private JTextField hotenTF;
    private JRadioButton namRadioButton;
    private JRadioButton nuRadioButton;
    private JTextField cmndTF;
    private JTextField lopTF;
    private JButton saveBtn;
    private boolean isNew;
    
    private Sinhvien sv = null;

    public ThemSinhVienController(JTextField maSVTF, JTextField hotenTF, JRadioButton namRadioButton, JRadioButton nuRadioButton, JTextField cmndTF, JTextField lopTF, JButton saveBtn, boolean isNew) {
        this.maSVTF = maSVTF;
        this.hotenTF = hotenTF;
        this.namRadioButton = namRadioButton;
        this.nuRadioButton = nuRadioButton;
        this.cmndTF = cmndTF;
        this.lopTF = lopTF;
        this.saveBtn = saveBtn;
        this.isNew = isNew;
    }

    

    public ThemSinhVienController() {
    }
    
    public void setView(Sinhvien sv) {
        this.sv = sv;
        
        maSVTF.setText(sv.getMasv());
        hotenTF.setText(sv.getHoten());
        if ("Nam".equals(sv.getGioitinh())) {
            namRadioButton.setSelected(true);
        } else {
            nuRadioButton.setSelected(true);
        }
        cmndTF.setText(sv.getCmnd());
        lopTF.setText(sv.getLop().getMalop());
        
        setEvent();
    }

    private void setEvent() {
        saveBtn.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                sv.setMasv(maSVTF.getText().trim());
                sv.setHoten(hotenTF.getText().trim());
                sv.setCmnd(cmndTF.getText().trim());
                if (namRadioButton.isSelected()) {
                    sv.setGioitinh("Nam");
                } else {
                    sv.setGioitinh("Nữ");
                }
                sv.setLop(new Lop(lopTF.getText()));
                if (showDialog() == true) {
                    boolean kq;
                    if (isNew == true) {
                        kq = SinhvienDAO.themSinhVien(sv);
                    } else {
                        kq = SinhvienDAO.capNhatThongTinSinhVien(sv);

                    }
                    if (kq == true) {
                        System.out.println("Thành công");
                    } else {
                        System.out.println("Thất bại");
                    }
                }
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
            
        });
    }
    
    private boolean showDialog() {
        int dialogResult = JOptionPane.showConfirmDialog(null,
                "Bạn muốn lưu thông tin sinh viên?", "Thông báo", JOptionPane.YES_NO_OPTION);
        return dialogResult == JOptionPane.YES_OPTION;
    }
}
