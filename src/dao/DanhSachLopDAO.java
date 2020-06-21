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
import pojo.Danhsachlop;
import util.HibernateUtil;

/**
 *
 * @author HP
 */
public class DanhSachLopDAO {
    public static List<Danhsachlop> layDanhSachLop() {
        List<Danhsachlop> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select * from Danhsachlop";
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
    
    public static List<Danhsachlop> layDanhSachLop(String malop, String mamon) {
        List<Danhsachlop> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select mh from Monhoc mh where mh.lop.malop = :malop and mh.mamon=:mamon";
            Query query = session.createQuery(hql);
            query.setString("mamon", mamon);
            query.setString("malop", malop);
            ds = query.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return ds;
    }
    
    public boolean themDanhSachLop(List<Danhsachlop> dsLop) {
        if (dsLop.isEmpty()) {
            return false;
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            dsLop.stream().forEach((sv) -> {
                session.save(sv);
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
