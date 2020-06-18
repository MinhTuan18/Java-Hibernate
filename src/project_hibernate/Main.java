/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_hibernate;

import dao.*;
import java.util.List;
import pojo.*;

/**
 *
 * @author HP
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Sinhvien sv = new Sinhvien();
        sv.setMasv("1123345");
        sv.setCmnd("123125413");
        sv.setGioitinh("Nam");
        sv.setHoten("A B C");
        Lop lop = new Lop("17CTT2");
        sv.setLop(lop);
        boolean kq = SinhvienDAO.themSinhVien(sv);
    } 
}
