package com.samweb.app;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.user.model.UserInfo;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet(urlPatterns = "/activeServlet")
public class FirstWebServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public FirstWebServlet() {
    	super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String firstName = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("confirmPassword");
		PrintWriter out = response.getWriter();
		System.out.println(firstName);
		out.println(firstName);
		out.println(email);
		out.println(password);
		
		// Create a User object to hold the user details
        UserInfo user = new UserInfo(firstName, email, password, null, 0);

        // Store the User object in the session
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        // Redirect to the user profile page
        response.sendRedirect("user_profile.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
