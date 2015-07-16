package edu.usc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.OracleResultSet;
import oracle.jdbc.driver.OracleDriver;

public class OracleConnect {
	private static final String HOST = "128.125.163.168";
	private static final String PORT = "1521";
	private static final String DBNAME = "csCI585";
	private static final String DBUSER = "team18";
	private static final String DBPASS = "toliu";
	private static final String DBURL = "jdbc:oracle:thin:@" + HOST + ":" + PORT + ":" + DBNAME;
	
	private static Connection con;
	
	public OracleConnect() {
		try {
			DriverManager.registerDriver(new OracleDriver());
			con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
        
        public OracleResultSet getOracleResultSet(String query) throws SQLException {
            System.out.println(query);
            Statement stmt = con.createStatement();
            return (OracleResultSet) stmt.executeQuery(query);
        }
        
        public Connection getConnection() {
            return con;
        }
	
	public ResultSet executeQuery(String query) throws SQLException {
		Statement stmt = con.createStatement();
		System.out.println(query);
		return stmt.executeQuery(query);
	}
	
	public int updateQuery(String query) throws SQLException {
		Statement stmt = con.createStatement();
		System.out.println(query);
		return stmt.executeUpdate(query);
	}
}
