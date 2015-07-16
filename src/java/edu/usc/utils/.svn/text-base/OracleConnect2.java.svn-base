package edu.usc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.OracleResultSet;
import oracle.jdbc.driver.OracleDriver;

public class OracleConnect2 {
	private static final String HOST = "icampus.usc.edu";
	private static final String PORT = "1521";
	private static final String DBNAME = "csci585";
	private static final String DBUSER = "hientt";
	private static final String DBPASS = "ubiela";
	private static final String DBURL = "jdbc:oracle:thin:@" + HOST + ":" + PORT + ":" + DBNAME;
	
	private static Connection con;
	
	public OracleConnect2() {
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
		return stmt.executeQuery(query);
	}
	
	public int updateQuery(String query) throws SQLException {
		Statement stmt = con.createStatement();
		return stmt.executeUpdate(query);
	}
}
