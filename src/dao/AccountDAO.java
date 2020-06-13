/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Account;
import util.HibernateUtil;

/**
 *
 * @author HP
 */
public class AccountDAO {
    public static Account layThongTinAccount(String username) {
        Account account = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            account = (Account) session.get(Account.class, username);
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return account;
    }
    
    public static boolean themAccount(Account account) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (AccountDAO.layThongTinAccount(account.getUsername()) != null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(account);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return true;
    }
    
    public static boolean doiMatKhau(Account account) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (AccountDAO.layThongTinAccount(account.getUsername()) == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(account);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return true;
    }
    
    public static boolean checkLogin(String username, String password) {
        Account account = AccountDAO.layThongTinAccount(username);
        if (account == null) {
            return false;
        }
        if (!password.equals(account.getPassword())) {
            return false;
        }
        return true;
    }
}
