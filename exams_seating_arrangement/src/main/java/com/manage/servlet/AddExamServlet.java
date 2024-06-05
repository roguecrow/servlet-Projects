package com.manage.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.manage.util.DbManager;

/**
 * Servlet implementation class AddExam
 */
@WebServlet("/AddExam")
public class AddExamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddExamServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String examName = request.getParameter("examName");
        String examDescription = request.getParameter("examDescription");
        Date examDate = Date.valueOf(request.getParameter("examDate")); // Convert String to Date

        // Parse application start and end dates from Strings to Timestamps
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Timestamp applicationStart = null;
        Timestamp applicationEnd = null;
        try {
            applicationStart = new Timestamp(dateFormat.parse(request.getParameter("applicationStart")).getTime());
            applicationEnd = new Timestamp(dateFormat.parse(request.getParameter("applicationEnd")).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            DbManager manage = new DbManager();
            int affectedRows = manage.createExam(examName, examDescription, examDate, applicationStart, applicationEnd);
            if (affectedRows == 1) {
                response.sendRedirect("HomePage.jsp?message=examAddedSuccessfully");
            } else {
                response.sendRedirect("HomePage.jsp?message=errorAddingExam");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("HomePage.jsp?message=errorAddingExam");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("HomePage.jsp?message=errorAddingExam");
        }

        // Print retrieved parameters
        System.out.println("Exam Name: " + examName);
        System.out.println("Exam Description: " + examDescription);
        System.out.println("Exam Date: " + examDate);
        System.out.println("Application Start Date: " + applicationStart);
        System.out.println("Application End Date: " + applicationEnd);
    }


}
