package com.samweb.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.samweb.manager.DataSaver;
import com.samweb.model.UserInfo;
import com.samweb.util.DbManager;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet(urlPatterns = "/activeServlet")
public class FirstWebServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DataSaver saver = new DataSaver();
	UserInfo userData =new UserInfo();
	

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
		String mobileNumber = request.getParameter("phone");
		String age = request.getParameter("age");
		String profession = request.getParameter("profession");

		PrintWriter out = response.getWriter();
		System.out.println(firstName);
		out.println(firstName);
		out.println(email);
		out.println(password);
		out.println(mobileNumber);
		out.println(age);
		userData.setEmail(email);
		userData.setFullName(firstName);
		userData.setPassword(password);
		try {
			DbManager manage = new DbManager();
			if(userData.getEmail()!= null && userData.getFullName()!= null && userData.getPassword()!=null) {
				manage.createUser(userData);
				System.out.println("User Register: " +  manage.viewUserDetails());
				request.setAttribute("userRegister", manage.viewUserDetails());
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		saver.addUser(firstName,email,password,profession,Integer.parseInt(age),Long.parseLong(mobileNumber));

		request.getRequestDispatcher("user_profile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Post called");
		String action = request.getParameter("action");
		String firstName = request.getParameter("name");
		String email = request.getParameter("email");
		System.out.println("id :"+ request.getParameter("editid"));
		System.out.println(action+" " + firstName + " "+ email);
		try {
			DbManager manage = new DbManager();
	        if (action != null) {
	            switch (action) {
	                case "edit":
	                manage.updateUserDetails(Integer.parseInt(request.getParameter("editid")), firstName, email);
	                    break;
	                case "delete":
	                manage.deleteUserDetails(Integer.parseInt(request.getParameter("deleteid")));
	                    break;
	            }
				request.setAttribute("userRegister", manage.viewUserDetails());
	    		request.getRequestDispatcher("user_profile.jsp").forward(request, response);
	    		//response.sendRedirect(action)
	        }

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
