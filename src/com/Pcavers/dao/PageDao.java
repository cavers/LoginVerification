package com.Pcavers.dao;

import com.Pcavers.bean.Page;
import com.Pcavers.dao.Imp.UserDaoImp;

public class PageDao {
	/**
	 * 获取带有总页数的page对象
	 * @param pageindex : 当前页数
	 * @param count : 每页显示的条数
	 * */
	public Page getPage(int pageIndex,int count){
		UserDao dao = new UserDaoImp();
		int usersSiz = dao.getUsersAll().size(); // 获取总记录数
		int coutPage = usersSiz%count==0 ? usersSiz/count : usersSiz/count+1; // 获取总页数
		int startRecord = (pageIndex-1)*count;
		Page page = new Page(pageIndex,startRecord,count,usersSiz,coutPage);
		return page;
	}
	/**
	 * 获取带有总页数的page对象
	 * @param count : 每页显示的条数
	 * @param startRecord : 当前页的起始记录数
	 * @param fign : 方法的区别
	 * */
	public Page getPage(int count,int startRecord,String fign){
		UserDao dao = new UserDaoImp();
		int usersSiz = dao.getUsersAll().size(); // 获取总记录数
		int coutPage = usersSiz%count==0 ? usersSiz/count : usersSiz/count+1; // 获取总页数
		int pageIndex = 1;
		for(int i=1;i<=coutPage;i++) {
			if(i*count>startRecord) {
				pageIndex = i;
				break;
			}
		}
		System.out.println("当前页：--》"+pageIndex);
		Page page = new Page(pageIndex,startRecord,count,usersSiz,coutPage);
		return page;
	}
}
