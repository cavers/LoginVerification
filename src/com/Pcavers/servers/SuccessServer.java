package com.Pcavers.servers;

import java.util.List;

import com.Pcavers.bean.Page;
import com.Pcavers.bean.Users;
import com.Pcavers.dao.UserDao;
import com.Pcavers.dao.Imp.UserDaoImp;

public class SuccessServer {
	public List<Users> getPageUsers(Page page){
		UserDao dao = new UserDaoImp();
		return dao.getPageUsers(page.getStartRecord(), page.getCount());
	}
}
