package com.manage.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.manage.model.LeaveDetails;
import com.manage.util.ServerManager;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LeaveDetails details = new LeaveDetails();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String firstName = request.getParameter("name");
		String password = request.getParameter("password");
		PrintWriter out = response.getWriter();
		out.println(firstName);
		out.println(password);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		try {
			ServerManager manage = new ServerManager();
			
			
			String firstName = request.getParameter("name");
			String password = request.getParameter("password");
			PrintWriter out = response.getWriter();
			out.println(password);
			
			if(manage.findEmpID(details, password)) {
				System.out.println("id found");
				HttpSession session=request.getSession();
				session.setAttribute("password",password);
	    		request.getRequestDispatcher("HomePage.jsp").forward(request, response);
			}
			else {
				System.out.println("not found");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
