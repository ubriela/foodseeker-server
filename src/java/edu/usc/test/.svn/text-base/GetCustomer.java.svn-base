/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usc.test;

import edu.usc.test.model.Customer;
import edu.usc.utils.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author ubriela
 */
public class GetCustomer {

    static Session session = null;

    public GetCustomer() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public List getCustomers(int startID, int endID) {
        List<Customer> idList = null;
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Customer as c where c.customerId between " + startID + " and " + endID);
            System.out.println("from Customer as c where c.customerId between " + startID + " and " + endID);
            idList = (List<Customer>) q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idList;
    }

    public Customer getCustomerByID(int customerId) {
        Customer customer = null;
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Customer as c where c.customerId=" + customerId);
            customer = (Customer) q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return customer;
    }
    
    
}
