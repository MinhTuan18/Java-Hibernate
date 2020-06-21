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
import pojo.Lop;
import util.HibernateUtil;

/**
 *
 * @author HP
 */
public class LopDAO {
    public static List<String> layDanhSachLop() {
        List<String> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select malop from Lop";
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
    
    public static Lop layThongTinLop(String malop) {
        Lop lop = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            lop = (Lop) session.get(Lop.class, malop);
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return lop;
    }
    
    public static boolean themLop(Lop lop) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (LopDAO.layThongTinLop(lop.getMalop()) != null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(lop);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return true;
    }
}
