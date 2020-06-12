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
public class Project_Hibernate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Account a= new Account();
        a.setUsername("1712870");
        a.setPassword("12345");
        a.setType("0");
        boolean kq = AccountDAO.themAccount(a);
        if (kq == true) {
            System.out.println("Thêm thành công");
        } else {
            System.out.println("Thêm thất bại");
        }
    }
    
}
