/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usc.services;

import edu.usc.controller.Statistic;
import edu.usc.utils.OracleConnect2;
import java.awt.geom.Point2D;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleResultSet;
import org.apache.derby.client.am.DateTime;

/**
 *
 * @author ubriela
 */
public class StatisticServices {

    public void saveLocation(int uid, Point2D location, boolean isNewSession) {
	try {
	    double lon = location.getX();
	    double lat = location.getY();

	    OracleConnect2 connect = new OracleConnect2();

	    //  Get session id
	    int maxSessionId = 0;
	    String sessionQuery = "SELECT MAX(sessionid) as sessionid from USERSTATISTIC WHERE userid = " + uid;
	    OracleResultSet rs = connect.getOracleResultSet(sessionQuery);
	    while (rs.next()) {
		maxSessionId = rs.getInt("sessionid");
	    }

	    int sessionId = 0;
	    if (isNewSession) {
		sessionId = maxSessionId + 1;
	    } else {
		sessionId = maxSessionId;
	    }

	    String sqlQuery = "INSERT INTO USERSTATISTIC(sessionid, userid, time, location) VALUES( "
		    + sessionId + ", " + uid + ", CURRENT_TIMESTAMP(3),"
		    + "SDO_GEOMETRY(2001,8307,SDO_POINT_TYPE("
		    + lon + ", " + lat
		    + ", NULL),NULL,NULL))";

	    System.out.println(sqlQuery);
	    connect.updateQuery(sqlQuery);

	} catch (SQLException ex) {
	    Logger.getLogger(StatisticServices.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    public Statistic getStatistic(int uid) {
	Statistic statistic = new Statistic();
	try {
	    OracleConnect2 connect = new OracleConnect2();

	    //  Get number of sessions
	    int numberOfSessions = 0;
	    String sessionQuery = "SELECT COUNT(distinct sessionid) as count from USERSTATISTIC WHERE userid = " + uid;
	    ResultSet rs = connect.executeQuery(sessionQuery);
	    while (rs.next()) {
		numberOfSessions = rs.getInt("count");
	    }
	    System.out.println(numberOfSessions);
	    
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    String ts = sdf.format(cal.getTime());
	    cal.add(Calendar.DAY_OF_MONTH, 1);
	    String ts2 = sdf.format(cal.getTime());
	    
	    ts += " 00:00:00";
	    ts2 += " 00:00:00";
	    
	    //	Get time spent for icampus
	    String timeQuery = "select sessionid, max(time) - min(time) as time from userstatistic us where userid = " + uid 
		    + " and time >= timestamp'" + ts + "' "
		    + " and time < timestamp'" + ts2 + "'"
		    + " group by us.sessionid";
	    
	    System.out.println(timeQuery);
	    int minutes = 0;
	    int totalHours = 0;
	    int totalMins = 0;
	    ResultSet rs2 = connect.executeQuery(timeQuery);
	    while (rs2.next()) {
		String time = rs2.getString("time").split(" ")[1];
		
		int hours = Integer.valueOf(time.split(":")[0]);
		int mins = Integer.valueOf(time.split(":")[1]);
		totalHours += totalHours + hours;
		totalMins += mins;
	    }
	    
	    totalHours += (totalMins/60);
	    totalMins = totalMins % 60;
	    statistic.setTimes(totalHours + "h " + totalMins + "\"");
	    statistic.setNumberOfSessions(numberOfSessions);
	    statistic.setSpeed(0);

	} catch (SQLException ex) {
	    Logger.getLogger(StatisticServices.class.getName()).log(Level.SEVERE, null, ex);
	}
	return statistic;
    }
}