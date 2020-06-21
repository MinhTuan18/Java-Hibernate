/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Bangdiem;
import pojo.BangdiemId;
import util.HibernateUtil;

/**
 *
 * @author HP
 */
public class BangdiemDAO {
    public static Bangdiem layThongTinDiem(BangdiemId bdId) {
        Bangdiem bd = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            bd = (Bangdiem) session.get(Bangdiem.class, bdId);
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return bd;
    }
    
    public static List<Bangdiem> layBangDiem(String malop, String mamon) {
    List<Bangdiem> ds = null;
    Session session = HibernateUtil.getSessionFactory().openSession();
    try {
        String hql = "select bd from Bangdiem bd where bd.id.malop = :malop and bd.id.mamon = :mamon";
        Query query = session.createQuery(hql);
        query.setString("malop", malop);
        query.setString("mamon", mamon);
        ds = query.list();
    } catch (HibernateException ex) {
        //Log the exception
        System.err.println(ex);
    } finally {
        session.close();
    }
    return ds;
    }
    
    public static boolean capNhatDiem(Bangdiem diem) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (BangdiemDAO.layThongTinDiem(diem.getId()) == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(diem);
            transaction.commit();
        } catch (HibernateException ex) {
            //Log the exception
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return true;
    }
    
    public static boolean themBangDiemTuFileCSV(String filename, String malop, String mamon){
        String delimiter = ",";
        List<Bangdiem> ds = new ArrayList<Bangdiem>();
        
        try {
            File file = new File(filename);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            br.readLine();
            while ((line = br.readLine()) != null) {
                tempArr = line.split(delimiter);
                String mssv = tempArr[1];
                String hoten = tempArr[2];
                double diemgk = Double.parseDouble(tempArr[3]);
                double diemck = Double.parseDouble(tempArr[4]);
                double diemkhac = Double.parseDouble(tempArr[5]);
                double diemtong = Double.parseDouble(tempArr[6]);
                
                Bangdiem diem = new Bangdiem(new BangdiemId(mssv, malop, mamon), hoten, diemgk, diemck, diemkhac, diemtong);
                
                ds.add(diem); 
            }
        } catch (IOException ex) {
        }
        if (ds.isEmpty()) {
            return false;
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            ds.stream().forEach((a) -> {
                session.save(a);
            });
            
            transaction.commit();
        } catch (HibernateException ex) {
            //Log the exception
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return true;
    }
}
