package com.manage.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        try {
	            ServerManager manage = new ServerManager();
	            String firstName = request.getParameter("name");
	            String password = request.getParameter("password");

	            // Check if password is null or empty and redirect to login page if true
	            if (password == null || password.trim().isEmpty()) {
	                response.sendRedirect("login_page.html");
	                return;
	            }

	            if (manage.findEmpID(details, password)) {
	            	Cookie userCookie = new Cookie("user", password);
	                userCookie.setMaxAge(30 * 60); //	 Cookie expires in 30 minutes
	                response.addCookie(userCookie);
	                System.out.println("id found");
	                HttpSession session = request.getSession();
	                session.setAttribute("password", password);
	                session.setAttribute("firstName", firstName);
	                response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	                response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	                response.setDateHeader("Expires", 0); // Proxies.
	                response.sendRedirect("HomePage.jsp");
	            } else {
	                System.out.println("not found");
	                response.sendRedirect("login_page.html"); // Redirect to login page on failure
	            }
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
	    }

}
