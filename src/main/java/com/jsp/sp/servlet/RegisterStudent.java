package com.jsp.sp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

@WebServlet(value="/RegisterStudent")
public class RegisterStudent extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Controller controller = new Controller();
		
		String full_name = req.getParameter("full_name");
		String e_mail = req.getParameter("e_mail");
		String contact = req.getParameter("contact");
		String gender = req.getParameter("gender");
		String address = req.getParameter("address");
		String branch = req.getParameter("branch");
		String password = req.getParameter("Pass");
		long phone_no = Long.parseLong(contact);
		
		HttpSession session = req.getSession();
		String admin_username = (String) session.getAttribute("admin_username");
		String admin_pass = (String) session.getAttribute("admin_pass");
		Admin current_admin = controller.fetchAdmin(admin_username,admin_pass);
		
		if (full_name!=null && e_mail!=null && contact!=null && gender!=null && branch!=null && password!=null) {
			Student student = new Student();
			Student firstStudent = controller.findStudent(1);
			if (firstStudent==null) {
				//Add First Student
				ArrayList<Student> new_students = new ArrayList<Student>();
				student.setFull_name(full_name);
				student.setE_mail(e_mail);
				student.setPhone_no(phone_no);
				student.setGender(gender);
				student.setAddress(address);
				student.setBranch(branch);
				student.setPassword(password);
				new_students.add(student);
				current_admin.setStudents(new_students);
				controller.addStudent(current_admin, student);
				
				int enroll_id = student.getEnroll_id();
				PrintWriter printWriter = resp.getWriter();
				printWriter.print("<!DOCTYPE html>\r\n"
						+ "<html lang=\"en\">\r\n"
						+ "<head>\r\n"
						+ "    <meta charset=\"UTF-8\">\r\n"
						+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
						+ "    <title>Admin</title>\r\n"
						+ "    <link rel=\"stylesheet\" href=\"./font-awesome-4.7.0/font-awesome-4.7.0/css/font-awesome.css\">\r\n"
						+ "    <style>\r\n"
						+ "        body{\r\n"
						+ "            font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;\r\n"
						+ "            background-image: url(./vecteezy_abstract-gradient-pastel-blue-and-purple-background-neon_8617161-1.jpg);\r\n"
						+ "        }\r\n"
						+ "        form{\r\n"
						+ "            height: 360px;\r\n"
						+ "            width: 1100px;\r\n"
						+ "            margin: 0 auto;\r\n"
						+ "            padding: 20px;\r\n"
						+ "            border: 2px solid #0f448d;\r\n"
						+ "            border-radius: 20px;\r\n"
						+ "        }\r\n"
						+ "        label{\r\n"
						+ "            display: inline-block;\r\n"
						+ "            margin-bottom: 15px;\r\n"
						+ "            color: #0c498f;\r\n"
						+ "        }\r\n"
						+ "        input[type=\"text\"]{\r\n"
						+ "            font-family: FontAwesome;"
						+ "            width: 27%;\r\n"
						+ "            padding: 10px;\r\n"
						+ "            border: 1px solid #62abde;\r\n"
						+ "            border-radius: 3px;\r\n"
						+ "            margin: 35px 0px 15px 390px;\r\n"
						+ "        }\r\n"
						+ "			input[type=\"submit\"]{\r\n"
						+ "            margin: 0px 0px 0px 470px;\r\n"
						+ "            background-color: #135de6;\r\n"
						+ "            color: white;\r\n"
						+ "            font-weight: 800;\r\n"
						+ "            padding: 15PX 40PX;\r\n"
						+ "            border: none;\r\n"
						+ "            border-radius: 10PX;\r\n"
						+ "        }\r\n"						
						+ "        h1{\r\n"
						+ "            color: #135de6;\r\n"
						+ "            text-align: center;\r\n"
						+ "            font-size: 50px;\r\n"
						+ "            margin: 0px 0px 10px 0px;\r\n"
						+ "        }\r\n"
						+ "        nav{\r\n"
						+ "            padding: 17px;\r\n"
						+ "            background-color: #135de6;\r\n"
						+ "            margin-bottom: 20px;\r\n"
						+ "        }\r\n"
						+ "        nav ul{\r\n"
						+ "            margin-left: 900px;\r\n"
						+ "        }\r\n"
						+ "        nav ul li{\r\n"
						+ "            list-style: none;\r\n"
						+ "            display: inline-block;\r\n"
						+ "        }\r\n"
						+ "        nav a{\r\n"
						+ "            display: inline-block;\r\n"
						+ "            text-decoration: none;\r\n"
						+ "            color: white;\r\n"
						+ "            font-size: 20px;  \r\n"
						+ "            margin-left: -800px;      \r\n"
						+ "        }\r\n"
						+ "        form a{\r\n"
						+ "            display: inline-block;\r\n"
						+ "            text-decoration: none;\r\n"
						+ "            color: white;\r\n"
						+ "            padding: 16px 80px ;\r\n"
						+ "            margin: 0px 0px 0px 390px;\r\n"
						+ "            background-color: #135de6;\r\n"
						+ "            border-radius: 10px;\r\n"
						+ "        }\r\n"
						+ "		form h2{\r\n"
						+ "			   width: 100%;\r\n"
						+ "        	   margin : -20px 0px 10px -10px;\r\n"
						+ "            color: #0c498f;\r\n"
						+ "            text-align: center;\r\n"
						+ "            background-color: white;\r\n"
						+ "            padding : 10px;\r\n"
						+ "            border-radius: 10px;\r\n"
						+ "        }\r\n"
						+ "        form p{\r\n"
						+ "        	text-align: center;\r\n"
						+ "        	color: #135de6;\r\n"
						+ "        }\r\n"
						+ "        ul{\r\n"
						+ "           margin: 0px 0px 0px 480px;\r\n"
						+ "        }\r\n"
						+ "        ul li{\r\n"
						+ "          	list-style: none;\r\n"
						+ "            display: inline-block;\r\n"
						+ "            margin-left: 20px;\r\n"
						+ "            color: #62abde;\r\n"
						+ "            font-size: 10px\r\n"
						+ "        }\r\n"
						+ "    </style>\r\n"
						+ "</head>\r\n"
						+ "<body>\r\n"
						+ "    <section id=\"Header\">\r\n"
						+ "        <h1>Student Portal</h1>\r\n"
						+ "        <nav>\r\n"
						+ "            <ul>\r\n"
						+ "                <li><a href=\"home.html\"><i class=\"fa fa-home\" aria-hidden=\"true\"></i> Home</a></li>\r\n"
						+ "            </ul>\r\n"
						+ "        </nav>\r\n"
						+ "    </section>\r\n"
						+ "    <section id=\"admin_operations\">\r\n"
						+ "        <form action=\"SearchStudentById\" method=\"post\">\r\n"
						+ "            <h2>ADMIN DASHBOARD</h2>\r\n"
						+ "            <p><i class=\"fa fa-user-plus\" aria-hidden=\"true\"></i> New Student with Id "+enroll_id+" Added <br><br> You may handle Student Operations here. </p> \r\n"
						+ "            <div>\r\n"
						+ "                <a href=\"studentregister.html\"><i class=\"fa fa-user-plus\" aria-hidden=\"true\"></i> ENROLL NEW STUDENT</a>\r\n"
						+ "            </div>\r\n"
						+ "            <div>\r\n"
						+ "                <input type=\"text\" name=\"fetchstudentId\" placeholder=\"&#xf002;Enter Student ID\">\r\n"
						+ "                <input type=\"submit\" value=\"FIND STUDENT\">\r\n"
						+ "            </div>\r\n"
						+ "           <input type=\"hidden\" name=\"hiddenId\" value="+enroll_id+">"
						+ "        </form>\r\n"
						+ "    </section>\r\n"
						+ "    <section id=\"Footer\">\r\n"
						+ "        <ul>\r\n"
						+ "            <li>About Us</li>\r\n"
						+ "            <li>All Rights Reserved.</li>\r\n"
						+ "        </ul>\r\n"
						+ "    </section>\r\n"
						+ "</body>\r\n"
						+ "</html>\r\n"
						+ "");
			} else {
				//Add New Student
				List<Student> students = current_admin.getStudents();
				student.setFull_name(full_name);
				student.setE_mail(e_mail);
				student.setPhone_no(phone_no);
				student.setGender(gender);
				student.setAddress(address);
				student.setBranch(branch);
				student.setPassword(password);
				students.add(student);
				current_admin.setStudents(students);
				controller.addStudent(current_admin, student);
				int enroll_id = student.getEnroll_id();
				PrintWriter printWriter = resp.getWriter();
				printWriter.print("<!DOCTYPE html>\r\n"
						+ "<html lang=\"en\">\r\n"
						+ "<head>\r\n"
						+ "    <meta charset=\"UTF-8\">\r\n"
						+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
						+ "    <title>Admin</title>\r\n"
						+ "    <link rel=\"stylesheet\" href=\"./font-awesome-4.7.0/font-awesome-4.7.0/css/font-awesome.css\">\r\n"
						+ "    <style>\r\n"
						+ "        body{\r\n"
						+ "            font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;\r\n"
						+ "            background-image: url(./vecteezy_abstract-gradient-pastel-blue-and-purple-background-neon_8617161-1.jpg);\r\n"
						+ "        }\r\n"
						+ "        form{\r\n"
						+ "            height: 360px;\r\n"
						+ "            width: 1100px;\r\n"
						+ "            margin: 0 auto;\r\n"
						+ "            padding: 20px;\r\n"
						+ "            border: 2px solid #0f448d;\r\n"
						+ "            border-radius: 20px;\r\n"
						+ "        }\r\n"
						+ "        label{\r\n"
						+ "            display: inline-block;\r\n"
						+ "            margin-bottom: 15px;\r\n"
						+ "            color: #0c498f;\r\n"
						+ "        }\r\n"
			            + "        input[type=\"text\"]{\r\n"
			            + "            font-family: FontAwesome;"
			            + "            width: 27%;\r\n"
			            + "            padding: 10px;\r\n"
			            + "            border: 1px solid #62abde;\r\n"
			            + "            border-radius: 3px;\r\n"
			            + "            margin: 35px 0px 15px 390px;\r\n"
			            + "        }\r\n"
			            + "			input[type=\"submit\"]{\r\n"
			            + "            margin: 0px 0px 0px 470px;\r\n"
			            + "            background-color: #135de6;\r\n"
			            + "            color: white;\r\n"
			            + "            font-weight: 800;\r\n"
			            + "            padding: 15PX 40PX;\r\n"
			            + "            border: none;\r\n"
			            + "            border-radius: 10PX;\r\n"
			            + "        }\r\n"
						+ "        h1{\r\n"
						+ "            color: #135de6;\r\n"
						+ "            text-align: center;\r\n"
						+ "            font-size: 50px;\r\n"
						+ "            margin: 0px 0px 10px 0px;\r\n"
						+ "        }\r\n"
						+ "        nav{\r\n"
						+ "            padding: 17px;\r\n"
						+ "            background-color: #135de6;\r\n"
						+ "            margin-bottom: 20px;\r\n"
						+ "        }\r\n"
						+ "        nav ul{\r\n"
						+ "            margin-left: 900px;\r\n"
						+ "        }\r\n"
						+ "        nav ul li{\r\n"
						+ "            list-style: none;\r\n"
						+ "            display: inline-block;\r\n"
						+ "        }\r\n"
						+ "        nav a{\r\n"
						+ "            display: inline-block;\r\n"
						+ "            text-decoration: none;\r\n"
						+ "            color: white;\r\n"
						+ "            font-size: 20px;  \r\n"
						+ "            margin-left: -800px;      \r\n"
						+ "        }\r\n"
						+ "        form a{\r\n"
						+ "            display: inline-block;\r\n"
						+ "            text-decoration: none;\r\n"
						+ "            color: white;\r\n"
						+ "            padding: 16px 80px ;\r\n"
						+ "            margin: 0px 0px 0px 390px;\r\n"
						+ "            background-color: #135de6;\r\n"
						+ "            border-radius: 10px;\r\n"
						+ "        }\r\n"
						+ "		form h2{\r\n"
						+ "			   width: 100%;\r\n"
						+ "        	   margin : -20px 0px 10px -10px;\r\n"
						+ "            color: #0c498f;\r\n"
						+ "            text-align: center;\r\n"
						+ "            background-color: white;\r\n"
						+ "            padding : 10px;\r\n"
						+ "            border-radius: 10px;\r\n"
						+ "        }\r\n"
						+ "        form p{\r\n"
						+ "        	text-align: center;\r\n"
						+ "        	color: #135de6;\r\n"
						+ "        }\r\n"
						+ "        ul{\r\n"
						+ "           margin: 0px 0px 0px 480px;\r\n"
						+ "        }\r\n"
						+ "        ul li{\r\n"
						+ "          	list-style: none;\r\n"
						+ "            display: inline-block;\r\n"
						+ "            margin-left: 20px;\r\n"
						+ "            color: #62abde;\r\n"
						+ "            font-size: 10px\r\n"
						+ "        }\r\n"
						+ "    </style>\r\n"
						+ "</head>\r\n"
						+ "<body>\r\n"
						+ "    <section id=\"Header\">\r\n"
						+ "        <h1>Student Portal</h1>\r\n"
						+ "        <nav>\r\n"
						+ "            <ul>\r\n"
						+ "                <li><a href=\"home.html\"><i class=\"fa fa-home\" aria-hidden=\"true\"></i> Home</a></li>\r\n"
						+ "            </ul>\r\n"
						+ "        </nav>\r\n"
						+ "    </section>\r\n"
						+ "    <section id=\"admin_operations\">\r\n"
						+ "        <form action=\"SearchStudentById\" method=\"post\">\r\n"
						+ "            <h2>ADMIN DASHBOARD</h2>\r\n"
						+ "            <p><i class=\"fa fa-user-plus\" aria-hidden=\"true\"></i> New Student with Id "+enroll_id+" Added <br><br> You may handle Student Operations here. </p> \r\n"
						+ "            <div>\r\n"
						+ "                <a href=\"studentregister.html\"><i class=\"fa fa-user-plus\" aria-hidden=\"true\"></i> ENROLL NEW STUDENT</a>\r\n"
						+ "            </div>\r\n"
						+ "            <div>\r\n"
						+ "                <input type=\"text\" name=\"fetchstudentId\" placeholder=\"&#xf002;Enter Student ID\">\r\n"
						+ "                <input type=\"submit\" value=\"FIND STUDENT\">\r\n"
						+ "            </div>\r\n"
						+ "        </form>\r\n"
						+ "    </section>\r\n"
						+ "    <section id=\"Footer\">\r\n"
						+ "        <ul>\r\n"
						+ "            <li>About Us</li>\r\n"
						+ "            <li>All Rights Reserved.</li>\r\n"
						+ "        </ul>\r\n"
						+ "    </section>\r\n"
						+ "</body>\r\n"
						+ "</html>\r\n"
						+ "");
			}
			
		} else {
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("failed.html");
			requestDispatcher.include(req, resp);
		}
		
	}
}