/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usc.utils;

import java.sql.ResultSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author infolab
 */
public class OracleConnectTest {
    
    public OracleConnectTest() {
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
     * Test of executeQuery method, of class OracleConnect.
     */
    @Test
    public void testExecuteQuery() throws Exception {
        System.out.println("executeQuery");
        String query = "SELECT * FROM USERS";
        OracleConnect instance = new OracleConnect();
        
        ResultSet expResult = null;
        ResultSet result = instance.executeQuery(query);
        while (result.next()) {
            System.out.println(result.getString("Firstname"));
        }
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateQuery method, of class OracleConnect.
     */
    @Test
    public void testUpdateQuery() throws Exception {
        System.out.println("updateQuery");
        String query = "";
        OracleConnect instance = new OracleConnect();
        int expResult = 0;
        int result = instance.updateQuery(query);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
