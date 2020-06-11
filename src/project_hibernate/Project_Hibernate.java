/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_hibernate;

import dao.SinhVienDAO;
import java.util.List;
import pojo.SinhVien;

/**
 *
 * @author HP
 */
public class Project_Hibernate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        List<SinhVien> ds=SinhVienDAO.layDanhSachSinhVien();
        for(int i=0; i<ds.size(); i++){
        SinhVien sv=ds.get(i);
        System.out.println("MSSV: "+sv.getMaSinhVien());
        System.out.println("Họ và tên: "+sv.getHoTen());
        }
    }
    
}
