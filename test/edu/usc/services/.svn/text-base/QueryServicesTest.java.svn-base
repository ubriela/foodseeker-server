/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usc.services;

import java.awt.geom.Point2D;
import java.util.Vector;
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
public class QueryServicesTest {
    
    public QueryServicesTest() {
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
     * Test of queryWithinDistance method, of class QueryServices.
     */
    @Test
    public void testQueryWithinDistance() throws Exception {
        System.out.println("queryWithinDistance");
        Point2D center = new Point2D() {

            @Override
            public double getX() {
                return 34.0202098185817;
            }

            @Override
            public double getY() {
                return -118.285686557556;
            }

            @Override
            public void setLocation(double d, double d1) {
                
            }

        };
        String tableName = "Buildings";
        float distance = 100F;
        String unit = "METER";
        QueryServices instance = new QueryServices();
        Vector expResult = null;
        Vector result = instance.queryWithinDistance(center, tableName, distance, unit);
        
        System.out.println(result.size());
        
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of queryKNN method, of class QueryServices.
     */
    @Test
    public void testQueryKNN() throws Exception {
        System.out.println("queryKNN");
        Point2D center = null;
        String tableName = "";
        int k = 0;
        String unit = "";
        QueryServices instance = new QueryServices();
        Vector expResult = null;
        Vector result = instance.queryKNN(center, tableName, k, unit);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of queryRectangle method, of class QueryServices.
     */
    @Test
    public void testQueryRectangle() throws Exception {
        System.out.println("queryRectangle");
        Point2D leftBelowPoint = null;
        Point2D rightAbovePoint = null;
        String tableName = "";
        QueryServices instance = new QueryServices();
        Vector expResult = null;
        Vector result = instance.queryRectangle(leftBelowPoint, rightAbovePoint, tableName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of queryPolygon method, of class QueryServices.
     */
    @Test
    public void testQueryPolygon() throws Exception {
        System.out.println("queryPolygon");
        String[] points = null;
        String tableName = "";
        QueryServices instance = new QueryServices();
        Vector expResult = null;
        Vector result = instance.queryPolygon(points, tableName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
