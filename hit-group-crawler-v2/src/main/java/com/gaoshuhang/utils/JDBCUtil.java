package com.gaoshuhang.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * JDBC操作辅助
 * @author gaoshuhang
 */
public class JDBCUtil
{
	private static String dbDriver;
	private static String url;
	private static String user;
	private static String password;

	static
	{
		Properties props = new Properties();
		InputStream in = JDBCUtil.class.getClassLoader().getResourceAsStream("dbcfg.properties");
		try
		{
			props.load(in);
		}
		catch (IOException e)
		{
			throw new RuntimeException("数据库配置文件读取出错");
		}
		dbDriver = props.getProperty("dbDriver");
		url = props.getProperty("url");
		user = props.getProperty("user");
		password = props.getProperty("password");
		try
		{
			Class.forName(dbDriver);
		}
		catch (ClassNotFoundException e)
		{
			throw new RuntimeException("数据库驱动加载出错");
		}
	}

	public static Connection getConnection()
	{
		try
		{
			return DriverManager.getConnection(url, user, password);
		}
		catch (SQLException e)
		{
			throw new RuntimeException("无法获得数据库连接");
		}
	}

	public static void realease(Connection conn, PreparedStatement stmt, ResultSet rs)
	{
		if(rs != null)
		{
			try
			{
				rs.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		if(stmt != null)
		{
			try
			{
				stmt.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		if(conn != null)
		{
			try
			{
				conn.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
}
