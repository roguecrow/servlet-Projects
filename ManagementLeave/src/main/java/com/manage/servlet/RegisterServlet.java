package com.manage.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.manage.model.LeaveDetails;
import com.manage.util.ServerManager;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LeaveDetails details = new LeaveDetails();

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		try {
			Map<Integer, String> departmentMap = new HashMap<>();
		    // Adding department details to the HashMap
		    departmentMap.put(1, "Sales");
		    departmentMap.put(2, "Marketing");
		    departmentMap.put(3, "Human Resources");
		    departmentMap.put(4, "Finance");
			ServerManager manage = new ServerManager();
			
			
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			int deptId = Integer.parseInt(request.getParameter("department"));
			PrintWriter out = response.getWriter();
//			out.println(name);
//			out.println(password);
//			out.println(deptId);
//			out.println(departmentMap.get(deptId));
			details.setEmployeeId(password);
			details.setUsername(name);
			details.setDeptId(deptId);
			details.setDeptName(departmentMap.get(deptId));
			
			if(!manage.findEmpID(details, password)) {
				manage.userRegisteration(details);
				System.out.println("Account Created Successfully");
				HttpSession session=request.getSession();
				session.setAttribute("password",password);
	    		request.getRequestDispatcher("HomePage.jsp").forward(request, response);
			}
			else {
				System.out.println("Account in use");
				 request.setAttribute("errorMessage", "Account in use. Please choose a different employee ID.");
		         request.getRequestDispatcher("registerationForm.html").forward(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
