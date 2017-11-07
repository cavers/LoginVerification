package com.Pcavers.dao.Util;

import java.sql.Connection;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {
	/**
	 * 此方法可以获取一个connection连接
	 * 
	 * @return Connection or null
	 */
	private static Connection getConnection() {
		Connection connection = DBCP.getInstance().getConnection();
		if (connection == null) {
			System.out.println("dbcp获取连接失败!");
			return null;
		} else {
			return connection;
		}
	}

	/**
	 * 此方法可以完成增删改所有的操作
	 * 
	 * @param sql
	 * @param params
	 * @return true or false
	 */
	public boolean excuteUpdate(String sql, Object... params) {
		int res = 0;// 受影响的行数
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);// 装载sql语句
			if (params != null) {
				// 加入有？占位符，在执行之前把？占位符替换?
				for (int i = 0; i < params.length; i++) {
					pstmt.setObject(i + 1, params[i]);
				}
			}
			res = pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return res > 0 ? true : false;
	}

	/**
	 * 使用泛型方法和反射机制进行封装
	 * 
	 * @param sql
	 * @param params
	 * @param cls
	 * @return
	 */
	public <T> List<T> executeQuery(String sql, Class<T> cls, Object... params) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<T> data = new ArrayList<T>();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);// 装载sql语句
			if (params != null) {
				// 加入有？占位符，在执行之前把？占位符替换�?
				for (int i = 0; i < params.length; i++) {
					pstmt.setObject(i + 1, params[i]);
				}
			}
			rs = pstmt.executeQuery();
			// 把查询出来的记录封装成对应的实体类对�?
			ResultSetMetaData rsd = rs.getMetaData();// 获得列对�?,通过此对象可以得到表的结构，包括，列名，列的个数，列的数据类�?
			while (rs.next()) {
				T m = null;
				try {
					m = cls.newInstance();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				for (int i = 0; i < rsd.getColumnCount(); i++) {
					String col_name = rsd.getColumnName(i + 1);// 获得列名
					Object value = rs.getObject(col_name);// 获得列所对应的�??
					Field field = null;
					try {
						field = cls.getDeclaredField(col_name);
						field.setAccessible(true);// 给私有属性设置可访问�?
						field.set(m, value);// 给对象的私有属�?�赋�?
					} catch (NoSuchFieldException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					}catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
				data.add(m);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return data;
	}

	/**
	 * 释放相应的资�?
	 * 
	 * @param rs
	 * @param pstmt
	 * @param conn
	 */
	public void closeAll(ResultSet rs, Statement pstmt, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
