package com.Pcavers.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Pcavers.bean.Page;
import com.Pcavers.bean.Users;
import com.Pcavers.dao.PageDao;
import com.Pcavers.dao.UserDao;
import com.Pcavers.dao.Imp.UserDaoImp;
import com.Pcavers.servers.SuccessServer;

@WebServlet("/success")
public class SuccessServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PageDao pageDao = new PageDao();
		int pageIndex = 1;
		int count = 5;
		Page page = pageDao.getPage(pageIndex, count);
		SuccessServer server = new SuccessServer();
		List<Users> users = server.getPageUsers(page);
		req.setAttribute("users", users);
		req.setAttribute("page", page);
		req.getRequestDispatcher("/success.jsp").forward(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PageDao pageDao = new PageDao();
		int count = Integer.valueOf(req.getParameter("count"));
		int startRecord = Integer.valueOf(req.getParameter("startRecord"));
		Page page = null;
		if(startRecord!=-1) {
			page = pageDao.getPage(count,startRecord,"帅哥");
		}else {
			int pageIndex = Integer.valueOf(req.getParameter("pageIndex"));
			page = pageDao.getPage(pageIndex, count);
		}
		SuccessServer server = new SuccessServer();
		List<Users> users = server.getPageUsers(page);
		req.setAttribute("users", users);
		req.setAttribute("page", page);
		req.getRequestDispatcher("/success.jsp").forward(req, resp);
	}
	
}
