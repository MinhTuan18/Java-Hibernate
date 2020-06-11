/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import net.sf.ehcache.hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pojo.SinhVien;
import static util.NewHibernateUtil.getSessionFactory;

/**
 *
 * @author HP
 */
public class SinhVienDAO {
    public static List<SinhVien> layDanhSachSinhVien() {
        List<SinhVien> ds = null;
        Session session = null;
        try  {
            session = getSessionFactory().openSession();
        } catch (Exception e) {
        }
        //Session session = getSessionFactory().openSession();
        try {
        String hql = "select sv from SinhVien sv";
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
}
