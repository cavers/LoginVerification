package com.Pcavers.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Pcavers.bean.Users;
import com.Pcavers.dao.UserDao;
import com.Pcavers.dao.Imp.UserDaoImp;
import com.alibaba.fastjson.JSON;

@WebServlet("/UpdateUser")
public class UpdateUserServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserDao dao = new UserDaoImp();
		String Pcavers = req.getParameter("Pcavers");
		if (Pcavers.equalsIgnoreCase("1")) {
			String name = req.getParameter("name");
			Users u = dao.getUser(name);
			resp.getWriter().println(JSON.toJSONString(u));
		} else if(Pcavers.equalsIgnoreCase("2")){
			int userid = Integer.valueOf(req.getParameter("userid"));
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String address = req.getParameter("address");
			String cellphone = req.getParameter("cellphone");
			int age = Integer.valueOf(req.getParameter("age"));
			boolean a = dao.updateUser(userid, username, password, address, cellphone, age);
			if (a) {
				resp.getWriter().println("{\"result\":\"true\"}");
			} else {
				resp.getWriter().println("{\"result\":\"false\"}");
			}
		}
	}
}
