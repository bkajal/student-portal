package com.jsp.sp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.sp.controller.Controller;
import com.jsp.sp.model.Admin;
import com.jsp.sp.model.Student;
@WebServlet(value="/AdminLogin")
public class AdminLogin extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Controller controller = new Controller();
		String admin_username = req.getParameter("Admin");
		String admin_pass = req.getParameter("Pass");
		Admin first_admin = controller.firstAdmin();
		
		if (first_admin==null) {
			Admin o_admin = new Admin();
			controller.addAdmin(o_admin);
			Admin admin = controller.firstAdmin();
			if (admin.getUsername().equals(admin_username) && admin.getPassword().equals(admin_pass)) {
				HttpSession session = req.getSession();
				session.setAttribute("admin_username", admin_username);
				session.setAttribute("admin_pass", admin_pass);
				resp.sendRedirect("admindashboard.html");
			} else {
				RequestDispatcher requestDispatcher = req.getRequestDispatcher("failed.html");
				requestDispatcher.include(req, resp);
			}
		}
		else if(admin_username!=null && admin_pass!=null){
			Admin admin = controller.fetchAdmin(admin_username, admin_pass);
			if (admin!=null) {
				if (admin.getUsername().equals(admin_username) && admin.getPassword().equals(admin_pass)) {
					HttpSession session = req.getSession();
					session.setAttribute("admin_username", admin_username);
					session.setAttribute("admin_pass", admin_pass);
					resp.sendRedirect("admindashboard.html");
				}
			} else {
				PrintWriter printWriter = resp.getWriter();
				printWriter.print("<h1 style=\"color:blue\">Invalid Credentials</h1>");
				RequestDispatcher requestDispatcher = req.getRequestDispatcher("failed.html");
				requestDispatcher.include(req, resp);
			}
		}
		else {
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("failed.html");
			requestDispatcher.include(req, resp);
		}
	}
}
