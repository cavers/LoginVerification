package com.Pcavers.dao.Imp;

import java.util.List;

import com.Pcavers.bean.Users;
import com.Pcavers.dao.UserDao;
import com.Pcavers.dao.Util.DBHelper;

public class UserDaoImp implements UserDao {
	DBHelper dbHelper = new DBHelper();

	public boolean login(String username, String password) {
		String sql = "select * from users where username = ? and password = ?";
		return (dbHelper.executeQuery(sql, Users.class, username, password)).size() > 0 ? true : false;
	}

	public boolean isUsername(String username) {
		String sql = "select * from users where username=?";
		return dbHelper.executeQuery(sql, Users.class, username).size() > 0 ? true : false;
	}

	public boolean register(String username, String password, String adress, String cellphone, int age) {
		String sql = "insert into users(username,password,address,cellphone,age) value (?,?,?,?,?)";
		return dbHelper.excuteUpdate(sql, username, password, adress, cellphone, age);
	}

	public Users getUser(String username) {
		String sql = "select * from users where username=?";
		return dbHelper.executeQuery(sql, Users.class, username).get(0);
	}

	public boolean updateUser(int userid, String username, String password, String adress, String cellphone, int age) {
		String sql = "UPDATE  users SET username=?,password=?,address=?,cellphone=?,age=? where userid=?";
		return dbHelper.excuteUpdate(sql, username, password, adress, cellphone, age, userid);
	}

	@Override
	public List<Users> getUsersAll() {
		String sql = "select * from users";
		List<Users> users = dbHelper.executeQuery(sql, Users.class);
		if (users.size() > 0)
			return users;
		else
			return null;
	}

	@Override
	public List<Users> getPageUsers(int startIndex,int count) {
		String sql ="select * from users limit ?,?";
		return dbHelper.executeQuery(sql, Users.class, startIndex,count);
	}
}
