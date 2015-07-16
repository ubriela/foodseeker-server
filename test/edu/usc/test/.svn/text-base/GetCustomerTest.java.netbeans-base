/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usc.test;

import edu.usc.test.model.Customer;
import java.util.Iterator;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ubriela
 */
public class GetCustomerTest {
    
    public GetCustomerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getCustomers method, of class GetCustomer.
     */
    @Test
    public void testGetCustomers() {
        System.out.println("getCustomers");
        int startID = 0;
        int endID = 100;
        GetCustomer instance = new GetCustomer();
        List expResult = null;
        List result = instance.getCustomers(startID, endID);
        Iterator it = result.iterator();
        while (it.hasNext()) {
            Customer c = (Customer) it.next();
            System.out.println(c.getCustomerId());
        }
        
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFilmByID method, of class GetCustomer.
     */
    @Test
    public void testGetFilmByID() {
        System.out.println("getCustomerByID");
        int customerId = 1;
        GetCustomer instance = new GetCustomer();
        Customer expResult = null;
        Customer result = instance.getCustomerByID(customerId);
        System.out.println(result.getCity());
        
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
