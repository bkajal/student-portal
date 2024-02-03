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
@WebServlet(value="/StudentLogin")
public class StudentLogin extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Controller controller = new Controller();
		
		String enrollment_id = req.getParameter("fetchstudentId");
		String password = req.getParameter("Pass");
		int enroll_id = Integer.parseInt(enrollment_id);
		Student firstStudent = controller.findStudent(1);
		HttpSession session = req.getSession();
		session.setAttribute("enrollment_id",enrollment_id);
		session.setAttribute("password", password);

		if (firstStudent!=null) {
			List<Student> studentlist = controller.fetchStudents();
			Student foundStudent = null;
			for (Student student : studentlist) {
				if (enroll_id==student.getEnroll_id() && password.equals(student.getPassword()) ) {
					foundStudent = student;
				}
			}
			if (foundStudent!=null) {
				int s_id = foundStudent.getEnroll_id();
				String s_full_name = foundStudent.getFull_name();
				String s_e_mail = foundStudent.getE_mail();
				long s_phone_no = foundStudent.getPhone_no();
				String s_gender = foundStudent.getGender();
				String s_address = foundStudent.getAddress();
				String s_branch = foundStudent.getBranch();
				String s_password = foundStudent.getPassword();
				PrintWriter printWriter = resp.getWriter();
				printWriter.print("<!DOCTYPE html>\r\n"
						+ "<html lang=\"en\">\r\n"
						+ "<head>\r\n"
						+ "    <meta charset=\"UTF-8\">\r\n"
						+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
						+ "    <title>Student</title>\r\n"
						+ "    <link rel=\"stylesheet\" href=\"./font-awesome-4.7.0/font-awesome-4.7.0/css/font-awesome.css\">\r\n"
						+ "    <style>\r\n"
						+ "        body{\r\n"
						+ "            font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;\r\n"
						+ "            background-image: url(\"./vecteezy_abstract-gradient-pastel-blue-and-purple-background-neon_8617161-1.jpg\");\r\n"
						+ "            overflow-y: scroll;\r\n"
						+ "        }\r\n"
						+ "        form{\r\n"
						+ "            height: 360px;\r\n"
						+ "            width: 1100px;\r\n"
						+ "            margin: 0 auto;\r\n"
						+ "            padding: 20px;\r\n"
						+ "            border: 2px solid #0f448d;\r\n"
						+ "            border-radius: 20px;\r\n"
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
						+ "            margin-left: 800px;\r\n"
						+ "        }\r\n"
						+ "        nav ul li{\r\n"
						+ "            list-style: none;\r\n"
						+ "            display: inline-block;\r\n"
						+ "        }\r\n"
						+ "        nav a{\r\n"
						+ "        	 display : inline-block;\r\n"
						+ "        	 text-decoration : none;\r\n"
						+ "        	 color : white;\r\n"
						+ "        	 font-size: 20px;\r\n"
						+ "        	 margin-left : -700px;    \r\n"
						+ "        }\r\n"
						+ "        form h2{\r\n"
						+ "			   width: 100%;\r\n"
						+ "        	   margin : -20px 0px 10px -10px;\r\n"
						+ "            color: #0c498f;\r\n"
						+ "            text-align: center;\r\n"
						+ "            background-color: white;\r\n"
						+ "            padding : 10px;\r\n"
						+ "            border-radius: 10px;\r\n"
						+ "        }\r\n"
						+ "        form h4{\r\n"
						+ "        	   margin : -80px 0px 0px 800px;\r\n"
						+ "            color: #0c498f;\r\n"
						+ "            text-align: left;\r\n"
						+ "        }\r\n"
						+ "        form h5{\r\n"
						+ "        	   margin : -180px 0px 0px -839px;\r\n"
						+ "            color: #0c498f;\r\n"
						+ "            text-align: center;\r\n"
						+ "        }\r\n"
						+ "			input[type=\"submit\"],button{\r\n"
						+ "            margin: 6px 0px 0px 6px;\r\n"
						+ "            background-color: green;\r\n"
						+ "            color: white;\r\n"
						+ "            font-weight: 800;\r\n"
						+ "            padding: 15PX 40PX;\r\n"
						+ "            border: none;\r\n"
						+ "            border-radius: 10PX;\r\n"
						+ "        }\r\n"
						+ "        ul{\r\n"
						+ "            margin: 0px 0px 0px 480px;\r\n"
						+ "        }\r\n"
						+ "        ul li{\r\n"
						+ "            list-style: none;\r\n"
						+ "            display: inline-block;\r\n"
						+ "            margin-left: 20px;\r\n"
						+ "            color: #62abde;\r\n"
						+ "            font-size: 10px\r\n"
						+ "        }\r\n"
						+ "        table{\r\n"
						+ "            width: 450px;\r\n"
						+ "            border: 2px solid #0f448d;\r\n"
						+ "            padding: 15px;\r\n"
						+ "            border-radius: 5px;\r\n"
						+ "            margin: 30px 0px 0px 323px;\r\n"
						+ "        }\r\n"
						+ "        table tr{\r\n"
						+ "            padding: 10px;\r\n"
						+ "            text-align: left;\r\n"
						+ "            background-color: #98c7e8;\r\n"
						+ "            color: #135de6;\r\n"
						+ "            font-size: 20px;\r\n"
						+ "        }\r\n"
						+ "        table td{\r\n"
						+ "            width: 50%;\r\n"
						+ "        }\r\n"
						+ "	       input[type=\"text\"]{"
						+ "            width: 95%;\r\n"
						+ "        }"
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
						+ "    <section id=\"studentdashboard\">\r\n"
						+ "        <form action=\"UpdateStudent\">\r\n"
						+ "            <h2>STUDENT DASHBOARD</h2>\r\n"
						+ "            <table>\r\n"
						+ "                <tr>\r\n"
						+ "                    <td><label for=\"e_id\"> Enrollment ID </label></td>\r\n"
						+ "                    <td><input type=\"text\" name=\"e_id\" id=\"e_id\" value="+s_id+"  disabled></td>\r\n"
						+ "                </tr>\r\n"
						+ "                <tr>\r\n"
						+ "                    <td><label for=\"fname\"> Full Name </label></td>\r\n"
						+ "                    <td><input type=\"text\" name=\"full_name\" id=\"fname\" value="+s_full_name+"  disabled></td>\r\n"
						+ "                </tr>\r\n"
						+ "                <tr>\r\n"
						+ "                    <td><label for=\"e_mail\"> E-mail </label></td>\r\n"
						+ "                    <td><input type=\"text\" name=\"e_mail\" id=\"e_mail\" value="+s_e_mail+"  disabled></td>\r\n"
						+ "                </tr>\r\n"
						+ "                <tr>\r\n"
						+ "                    <td><label for=\"phone\"> Contact </label></td>\r\n"
						+ "                    <td><input type=\"text\" name=\"contact\" id=\"phone\" value="+s_phone_no+"  disabled></td>\r\n"
						+ "                </tr>\r\n"
						+ "					<tr>\r\n"
						+ "						<td><label for=\"branch\"> Branch </label></td>\r\n"
						+ "						<td><input type=\"text\" name=\"branch\" id=\"branch\" value="+s_branch+"  disabled></td>\r\n"
						+ "					</tr>\r\n"
						+ "                <tr>\r\n"
						+ "                    <td><label for=\"gender\"> Gender </label></td>\r\n"
						+ "                    <td><input type=\"text\" name=\"gender\" id=\"gender\" value="+s_gender+"  disabled></td>\r\n"
						+ "                </tr>\r\n"
						+ "                <tr>\r\n"
						+ "                    <td><label for=\"address\"> Address </label></td>\r\n"
						+ "                    <td><input type=\"text\" name=\"address\" id=\"address\" value="+s_address+"  disabled></td>\r\n"
						+ "                </tr>\r\n"
						+ "                <tr>\r\n"
						+ "                    <td><label for=\"password\"> Password </label></td>\r\n"
						+ "                    <td><input type=\"text\" name=\"Pass\" id=\"password\" value="+s_password+"  disabled></td>\r\n"
						+ "                </tr>"
						+ "            </table>\r\n"
						+ "            <h5><i class=\"fa fa-pencil-square-o\" aria-hidden=\"true\"></i> Do you want to Update Student Details ?</h5>\r\n"
						+ "            <button onclick=\"makeEditable()\"> EDIT </button>\r\n"
						+ "            <input type=\"submit\" id=\"update\" value=\"UPDATE\" hidden>\r\n"
						+ "            <h4>Update Instructions  : <i class=\"fa fa-level-down\" aria-hidden=\"true\"></i><br>"
						+ "            <i class=\"fa fa-check-square-o\" aria-hidden=\"true\"></i> Press EDIT button first then UPDATE.<br> "
						+ "            <i class=\"fa fa-check-square-o\" aria-hidden=\"true\"></i> Enter Data without Spaces.<br> "
						+ "            <i class=\"fa fa-check-square-o\" aria-hidden=\"true\"></i> Separate words by Capitals (A-Z)/ Comma (,)</h4> "
						+ "        </form>\r\n"
						+ "    </section>\r\n"
						+ "    <section id=\"Footer\">\r\n"
						+ "        <ul>\r\n"
						+ "            <li>About Us</li>\r\n"
						+ "            <li>All Rights Reserved.</li>\r\n"
						+ "        </ul>\r\n"
						+ "    </section>\r\n"
						+ "    <script>\r\n"
						+ "        function makeEditable(){\r\n"
						+ "            event.preventDefault();"
						+ "            document.getElementById(\"fname\").disabled = false;"
						+ "            document.getElementById(\"fname\").focus();"
						+ "            document.getElementById(\"e_mail\").disabled = false;"
						+ "            document.getElementById(\"e_mail\").focus();"
						+ "            document.getElementById(\"phone\").disabled = false;"
						+ "            document.getElementById(\"phone\").focus();"
						+ "            document.getElementById(\"branch\").disabled = false;"
						+ "            document.getElementById(\"branch\").focus();"
						+ "            document.getElementById(\"address\").disabled = false;"
						+ "            document.getElementById(\"address\").focus();"
						+ "            document.getElementById(\"password\").disabled = false;"
						+ "            document.getElementById(\"password\").focus();"
						+ "            document.getElementById(\"update\").hidden = false;"
						+ "        }\r\n"
						+ "    </script>\r\n"
						+ "</body>\r\n"
						+ "</html>\r\n"
						+ "");
			} else {
			    //Student Not Found
			    PrintWriter printWriter = resp.getWriter();
			    printWriter.print("<!DOCTYPE html>\r\n"
			            + "<html lang=\"en\">\r\n"
			            + "<head>\r\n"
			            + "    <meta charset=\"UTF-8\">\r\n"
			            + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
			            + "    <title>Student</title>\r\n"
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
			            + "		   form h2{\r\n"
						+ "			   width: 100%;\r\n"
						+ "            margin : -20px 0px 10px -10px;\r\n"
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
						+ "            margin: 0px 0px 0px 480px;\r\n"
			            + "        }\r\n"
			            + "        ul li{\r\n"
			            + "            list-style: none;\r\n"
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
			            + "        <form >\r\n"
			            + "            <h2>STUDENT DASHBOARD</h2>\r\n"
			            + "            <p>STUDENT NOT FOUND. <br><br> You may <i class=\"fa fa-user-plus\" aria-hidden=\"true\"></i> ENROLL/REGISTER New Student by Admin. <br><br> OR <br><br> Check STUDENT CREDENTIALS Again </p> \r\n"
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
