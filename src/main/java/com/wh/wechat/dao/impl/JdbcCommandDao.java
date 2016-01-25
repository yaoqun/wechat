package com.wh.wechat.dao.impl;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.wh.wechat.Command;
import com.wh.wechat.MyLogger;
import com.wh.wechat.dao.CommandDao;

import java.sql.*;

/**
 * Created by Whiker on 2016/1/23.
 */
public class JdbcCommandDao implements CommandDao {

	public boolean addCommand(Command command) {
		if (command == null || command.getName().trim().length() == 0) {
			return false;
		}
		PreparedStatement pstat = null;
		try {
			pstat = mConn.prepareStatement(
					"INSERT INTO command(name, comment, content) VALUES(?, ?, ?)");
			pstat.setString(1, command.getName());
			pstat.setString(2, command.getComment());
			pstat.setString(3, command.getContent());
			pstat.executeUpdate();
			return true;
		}
		catch (MySQLIntegrityConstraintViolationException e) {
			return false;
		}
		catch (Exception e) {
			MyLogger.logError(e.toString());
			return false;
		}
		finally {
			if (pstat != null) {
				try {
					pstat.close();
				}
				catch (Exception e) {
					MyLogger.logError(e.toString());
				}
			}
		}
	}

	public Command queryCommand(String name) {
		if (name == null || name.trim().length() == 0) {
			return null;
		}
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try {
			pstat = mConn.prepareStatement(
					"SELECT id,name,comment,content FROM command WHERE name=?");
			pstat.setString(1, name);
			rs = pstat.executeQuery();
			if (rs.next()) {
				Command command = new Command();
				command.setId(rs.getInt(1));
				command.setName(rs.getString(2));
				command.setComment(rs.getString(3));
				command.setContent(rs.getString(4));
				return command;
			}
			else
				return null;
		}
		catch (Exception e) {
			MyLogger.logError(e.toString());
			return null;
		}
		finally {
			if (rs != null) {
				try {
					rs.close();
				}
				catch (Exception e) {
					MyLogger.logError(e.toString());
				}
			}
		}
	}

	public void deleteCommand(String name) {
		if (name == null || name.trim().length() == 0) {
			return;
		}
		PreparedStatement pstat = null;
		try {
			pstat = mConn.prepareStatement(
					"DELETE FROM command WHERE name=?");
			pstat.setString(1, name);
			pstat.executeUpdate();
		}
		catch (Exception e) {
			MyLogger.logError(e.toString());
		}
		finally {
			if (pstat != null) {
				try {
					pstat.close();
				}
				catch (Exception e) {
					MyLogger.logError(e.toString());
				}
			}
		}
	}

	private static JdbcCommandDao jdbcCommandDao = new JdbcCommandDao();

	public static JdbcCommandDao getInstance() {
		return jdbcCommandDao;
	}

	private JdbcCommandDao() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			mConn = DriverManager.getConnection(
					"jdbc:mysql://localhost/wechat",
					"root", "wh1991");
		}
		catch (Exception e) {
			MyLogger.logError(e.toString());
		}
	}

	private Connection mConn;

}
