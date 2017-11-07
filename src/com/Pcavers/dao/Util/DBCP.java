package com.Pcavers.dao.Util;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class DBCP {
	private static final BasicDataSource bds;
	static {
		bds = new BasicDataSource();
		PropertiesUtil.getInstance("jdbc.properties");
	}
	private DBCP() {
		bds.setUrl(PropertiesUtil.getProperties("url"));
		bds.setDriverClassName(PropertiesUtil.getProperties("driver"));
		bds.setUsername(PropertiesUtil.getProperties("username"));
		bds.setPassword(PropertiesUtil.getProperties("password"));
		bds.setMaxIdle(Integer.valueOf(PropertiesUtil.getProperties("MaxIdle")));
		bds.setMinIdle(Integer.valueOf(PropertiesUtil.getProperties("MinInle")));
		bds.setInitialSize(Integer.valueOf(PropertiesUtil.getProperties("InitialSize")));
	}
	private static class DBCPHolder{
		private static final DBCP INSTANCE = new DBCP();
	} 
	public static final DBCP getInstance() {
		return DBCPHolder.INSTANCE;
	}
	public Connection getConnection() {
		Connection connection = null;
		try {
			if(DBCP.bds==null)
				getConnection();
			connection = DBCP.bds.getConnection();
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
