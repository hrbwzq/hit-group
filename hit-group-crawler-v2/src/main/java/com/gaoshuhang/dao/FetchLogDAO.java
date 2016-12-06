package com.gaoshuhang.dao;

import com.gaoshuhang.domain.News;
import com.gaoshuhang.utils.HashUtil;
import com.gaoshuhang.utils.JDBCUtil;

import java.sql.*;
import java.util.Date;

/**
 * 数据库操作DAO类
 * @author gaoshuhang
 */
public class FetchLogDAO
{
	public boolean isNewsExists(News news)
	{

		String titleHash = HashUtil.strHash(news.getTitle(), "MD5");

		try
		{
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select count(log_id) from t_log where title_hash=?");
			preparedStatement.setString(1, titleHash);
			ResultSet resultSet = preparedStatement.executeQuery();

			int result = -1;

			if(resultSet.next())
			{
				result = resultSet.getInt(1);
			}

			JDBCUtil.realease(connection, preparedStatement, resultSet);

			if(result == -1)
			{
				throw new SQLException("查询出错");
			}
			else if(result == 0)
			{
				return false;
			}
			else if(result == 1)
			{
				return true;
			}
			else
			{
				throw new SQLException("结果数错误");
			}

		}
		catch (SQLException e)
		{
			throw new RuntimeException("数据库查询出错");
		}
	}

	public void saveNewsLog(News news)
	{

		String title = news.getTitle();
		String title_hash = HashUtil.strHash(title, "MD5");
		Date date = new Date();

		try
		{
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("insert into t_log (title_hash, create_time) values (?,?)");
			preparedStatement.setString(1, title_hash);
			preparedStatement.setTimestamp(2, new Timestamp(date.getTime()));
			preparedStatement.executeUpdate();

			JDBCUtil.realease(connection, preparedStatement, null);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new RuntimeException("数据库插入出错");
		}
	}
}
