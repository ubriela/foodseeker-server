/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usc.test;

import edu.usc.utils.OracleConnect;
import java.awt.geom.Point2D;
import java.sql.Connection;
import oracle.spatial.geometry.JGeometry;
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
public class GeometryTest {
    
    public GeometryTest() {
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
     * Test of printGeometries method, of class Geometry.
     */
    @Test
    public void testPrintGeometries() throws Exception {
        System.out.println("printGeometries");
        OracleConnect connect = new OracleConnect();
        Connection dbConnection = connect.getConnection();
        String tableName = "buildings";
        String geoColumn = "location";
        String predicate = "";
        int printStyle = 2;
        Geometry.printGeometries(dbConnection, tableName, geoColumn, predicate, printStyle);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printPoint method, of class Geometry.
     */
    @Test
    public void testPrintPoint_doubleArr() {
        System.out.println("printPoint");
        double[] point = null;
        String expResult = "";
        String result = Geometry.printPoint(point);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printPoint method, of class Geometry.
     */
    @Test
    public void testPrintPoint_Point2D() {
        System.out.println("printPoint");
        Point2D point = null;
        String expResult = "";
        String result = Geometry.printPoint(point);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printGeom method, of class Geometry.
     */
    @Test
    public void testPrintGeom() {
        System.out.println("printGeom");
        JGeometry geom = null;
        String expResult = "";
        String result = Geometry.printGeom(geom);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
