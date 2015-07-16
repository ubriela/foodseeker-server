/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usc.services;

import edu.usc.controller.Statistic;
import java.awt.geom.Point2D;
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
public class StatisticServicesTest {

    public StatisticServicesTest() {
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
     * Test of saveLocation method, of class StatisticServices.
     */
    @Test
    public void testSaveLocation() {
	System.out.println("saveLocation");
	int uid = 0;
	Point2D location = new Point2D() {

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
	StatisticServices instance = new StatisticServices();
//	instance.saveLocation(1099653312, location, true);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of getStatistic method, of class StatisticServices.
     */
    @Test
    public void testGetStatistic() {
	System.out.println("getStatistic");
	int uid = 1099653312;
	StatisticServices instance = new StatisticServices();
	Statistic expResult = null;
	Statistic result = instance.getStatistic(uid);
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }
}