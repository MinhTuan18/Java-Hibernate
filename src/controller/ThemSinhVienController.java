/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.*;
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
    
    private Sinhvien sv = null;

    public ThemSinhVienController(JTextField maSVTF, JTextField hotenTF, JRadioButton namRadioButton, JRadioButton nuRadioButton, JTextField cmndTF, JTextField lopTF, JButton saveBtn) {
        this.maSVTF = maSVTF;
        this.hotenTF = hotenTF;
        this.namRadioButton = namRadioButton;
        this.nuRadioButton = nuRadioButton;
        this.cmndTF = cmndTF;
        this.lopTF = lopTF;
        this.saveBtn = saveBtn;
    }

    public ThemSinhVienController() {
    }
    
    
}
