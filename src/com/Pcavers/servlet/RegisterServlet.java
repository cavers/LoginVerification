package com.Pcavers.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Pcavers.dao.UserDao;
import com.Pcavers.dao.Imp.UserDaoImp;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserDao dao = new UserDaoImp();
		String username = req.getParameter("username");
		System.out.println(username);
		boolean isName = dao.isUsername(username);
		if(isName) {
			resp.getWriter().println("{\"result\":\"true\"}");
		}else {
			resp.getWriter().println("{\"result\":\"false\"}");
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserDao dao = new UserDaoImp();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String address = req.getParameter("address");
		String cellphone = req.getParameter("cellphone");
		int age = Integer.valueOf(req.getParameter("age"));
		dao.register(username, password, address, cellphone, age);
	}
}
