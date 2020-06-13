/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Sinhvien;
import util.HibernateUtil;

/**
 *
 * @author HP
 */
public class SinhvienDAO {
    public static List<Sinhvien> layDanhSachSinhVien() {
    List<Sinhvien> ds = null;
    Session session = HibernateUtil.getSessionFactory().openSession();
    try {
        String hql = "select sv from Sinhvien sv";
        Query query = session.createQuery(hql);
        ds = query.list();
    } catch (HibernateException ex) {
        //Log the exception
        System.err.println(ex);
    } finally {
        session.close();
    }
    return ds;
    }
    
    public static Sinhvien layThongTinSinhVien(String maSinhVien) {
        Sinhvien sv = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            sv = (Sinhvien) session.get(Sinhvien.class, maSinhVien);
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return sv;
    }
    
    public static boolean themSinhVien(Sinhvien sv) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (SinhvienDAO.layThongTinSinhVien(sv.getMasv()) !=null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(sv);
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
    
    public static boolean capNhatThongTinSinhVien(Sinhvien sv) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (SinhvienDAO.layThongTinSinhVien(sv.getMasv()) == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(sv);
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
    
    public static boolean xoaSinhVien(String maSinhVien) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Sinhvien sv = SinhvienDAO.layThongTinSinhVien(maSinhVien);
        if(sv==null){
        return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(sv);
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