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
import pojo.Lop;
import util.HibernateUtil;

/**
 *
 * @author HP
 */
public class LopDAO {
    public static List<Lop> layDanhSachLop() {
    List<Lop> ds = null;
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
    
    
}
