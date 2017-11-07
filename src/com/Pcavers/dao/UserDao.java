package com.Pcavers.dao;

import java.util.List;

import com.Pcavers.bean.Users;

public interface UserDao {
	/**
	 * 验证用户名和密码是否正确
	 * */
	public boolean login(String username,String password);
	/**
	 * 验证用户名是否存在
	 * */
	public boolean isUsername(String username);
	/**
	 * 注册用户
	 * */
	public boolean register(String username,String password,String adress,String cellphone,int age);
	/**
	 * 通过用户名查询用户信息
	 * */
	public Users getUser(String username);
	
	/**
	 * 修改用户信息
	 * */
	public boolean updateUser(int userid,String username,String password,String adress,String cellphone,int age);
	/**
	 * 获取所有的用户信息
	 * */
	public List<Users> getUsersAll();
	/**
	 * 获取指定位置的用户信息
	 * */
	public List<Users> getPageUsers(int startIndex,int count);
}
