package com.thoughtworks.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	
	private static final String MYPOS_SCHEMA = "proxool.mypos";
	
	public static Connection getConnection() {
		return getProxoolConnection(MYPOS_SCHEMA);
	}
	
	public static Connection getProxoolConnection(String schema)
	{
		Connection con = null;
		try 
		{
			con = DriverManager.getConnection(schema);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	public static void rollback(Connection con)
	{
		try
		{
			if(con != null)
			{
				con.rollback();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void close(Connection con,PreparedStatement pst, ResultSet rs) 
	{
		close(con);
		close(pst);
		close(rs);
	}
	
	public static void close(Connection con, Statement stmt, ResultSet rs) 
	{
		close(con);
		close(stmt);
		close(rs);
	}
	
	public static void close(Connection con, CallableStatement cstmt, ResultSet rs)
	{
		close(con);
		close(cstmt);
		close(rs);
	}
	
	public static void close(CallableStatement cstmt)
	{
		try
		{
			if(cstmt != null)
			{
				cstmt.close();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt)
	{
		try
		{
			if(stmt != null)
			{
				stmt.close();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement pst)
	{
		try
		{
			if(pst != null)
			{
				pst.close();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs)
	{
		try
		{
			if(rs != null)
			{
				rs.close();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void close(Connection con)
	{
		try
		{
			if(con != null)
			{
				con.close();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static int executeUpdate(String sql)
	{
		Connection con = null;
		Statement stmt = null;
		try {
			con = DBUtil.getConnection();
			stmt = con.createStatement();
			return stmt.executeUpdate(sql);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, stmt, null);
		}
		return 0;
	}
	
}
