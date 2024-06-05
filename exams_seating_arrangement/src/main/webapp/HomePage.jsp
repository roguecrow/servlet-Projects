<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.manage.model.UserDetails" %>
<%@ page import ="com.manage.model.ExamDetails" %>
<%@ page import ="com.manage.util.DbManager" %>
<%@ page import = "java.util.List"%>
<%@ page import="java.sql.SQLException" %>



<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="HomePageStyles.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <!-- Bootstrap JS -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    
    <title>Home Screen</title>
    <style>
        .profile-dropdown {
            margin-right: 20px; /* Adjust this value as needed to move the icon left */
        }
        .profile-container {
            display: flex;
            align-items: center;
            border-radius: 20px; /* Rounded border */
            background-color: #f8f9fa; /* Background color */
            border: 1px solid #ddd; /* Light rounded border */
            padding: 5px 10px; /* Padding for the container */
            color: black; /* Black color for profile icon and name */
        }
        .profile-container:hover {
            color: blue; /* Change to blue on hover */
        }
        .profile-icon {
            font-size: 1.5em; /* Adjust size as needed */
            border-radius: 50%;
            padding: 5px;
            background-color: #fff; /* Background color for the icon */
            margin-right: 10px; /* Space between icon and name */
        }
        .username {
            font-size: 1em; /* Adjust size as needed */
            font-weight: bold; /* Bold text */
        }
        .dropdown-item i {
            margin-right: 10px; /* Space between icon and text */
        }
        .navbar-nav {
            margin-left: 20px; /* Move nav items to the right */
        }
        .container-wrapper {
            padding: 20px;
            height: calc(100vh - 72px); /* Adjust for navbar height */
        }
        .left-container, .right-container {
            height: 100%; /* Take full height of the parent */
        }
        .left-container {
            position: relative; /* Add relative positioning */
            border-radius: 8px;
            flex: 2;
            background-color: #ffffff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            margin-right: 20px; /* Add margin to create space */
            overflow-y: auto; /* Enable vertical scrolling */
        }
        .right-container {
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            flex: 1;
            background-color: #ffffff;
            padding: 20px;
        }
        .card {
            margin-bottom: 20px; /* Add space between cards */
            border-radius: 10px; /* Rounded corners for the cards */
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Add shadow to the cards */
            transition: transform 0.3s, box-shadow 0.3s; /* Smooth transition on hover */
        }
        .card:hover {
            transform: translateY(-5px); /* Move the card up slightly on hover */
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2); /* Add a stronger shadow on hover */
            cursor: pointer;
        }
        .card-body {
            padding: 20px; /* Add padding inside the card body */
        }
        .card-title {
            font-size: 20px; /* Customize the font size of the card title */
            margin-bottom: 10px; /* Add space below the card title */
        }
        .card-text {
            font-size: 16px; /* Customize the font size of the card text */
        }
        .add-exam-button {
            position: absolute; /* Position the button absolutely */
            top: 20px; /* Adjust as needed */
            right: 20px; /* Adjust as needed */
        }
        .right-container {
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Soft shadow */
    border-radius: 8px; /* Rounded corners */
    flex: 1;
    background-color: #ffffff; /* White background */
    padding: 20px;
    overflow-y: auto; /* Enable vertical scrolling if content overflows */
    transition: box-shadow 0.3s; /* Smooth shadow transition on hover */
}

.right-container:hover {
    box-shadow: 0 4px 16px rgba(0, 0, 0, 0.2); /* Stronger shadow on hover */
}

#examDetails {
    background-color: #f8f9fa; /* Light gray background for the details section */
    border-radius: 8px; /* Rounded corners */
    padding: 15px; /* Padding inside the details section */
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1); /* Soft shadow for the details section */
    transition: background-color 0.3s; /* Smooth background color transition on hover */
}

#examDetails h2 {
    font-size: 24px; /* Larger font size for the heading */
    margin-bottom: 15px; /* Space below the heading */
    color: #333; /* Darker text color for the heading */
}

#examDetails p {
    font-size: 16px; /* Font size for the paragraph */
    line-height: 1.5; /* Line height for better readability */
    color: #666; /* Gray color for the paragraph text */
}

#examDetails:hover {
    background-color: #e9ecef; /* Slightly darker background on hover */
}

.search-form {
    margin-left: 70px;
    width: 400px;
}

.search-bar {
    border-radius: 20px;
}

.submit-button {
    border-radius: 20px;
}

.btn-black {
    color: #000; 
    background-color: #0000; 
    border-color: #000;
}

.btn-black:hover {
    color: #fff; 
    background-color: #000; 
    border-color: #000;
}
body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    background-color: whitesmoke;
}

.navbar-brand img {
    margin-left: 10px;
    width: 100px;
    height: auto; 
    filter: brightness(0%) contrast(100%); 
}

.custom-navbar {
    background-color: #ffffff;
}

.navbar-nav .nav-link:hover {
    color: blue; /* Change to the desired color on hover */
}
   .update-button {
            position: absolute;
            bottom: 20px;
            right: 20px;
        }
    </style>
</head>
<body>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setHeader("Expires", "0"); // Proxies.

if (session == null || session.getAttribute("userDetails") == null) {
    response.sendRedirect("login.jsp");
    return;
}

UserDetails userDetails = (UserDetails) session.getAttribute("userDetails");
String userName = userDetails != null ? userDetails.getUsername() : "User";
int roleId = userDetails != null ? userDetails.getRoleId() : 1; // Default to student if role is not set
%>

<div id="nav-placeholder"></div>
<% String message = request.getParameter("message"); %>
<% if (message != null) { %>
    <div class="message">
        <% if (message.equals("examAddedSuccessfully")) { %>
            <p>Exam added successfully!</p>
        <% } else if (message.equals("errorAddingExam")) { %>
            <p>Something went wrong. Please try again later.</p>
        <% } %>
    </div>
<% } %>

<div class="container-fluid container-wrapper">
    <div class="row h-100">
        <div class="col-lg-8 col-md-7 col-sm-12 left-container">
            <% if (roleId == 0) { %>
            <button class="btn btn-primary add-exam-button" data-toggle="modal" data-target="#addExamModal">Add New Exam</button>
            <% } %>
            <h2>Exams</h2>
            <%
            // Retrieve exam details from the database
            DbManager dbManager = new DbManager();
            List<ExamDetails> exams = null;
            try {
            	exams = dbManager.getAllExams();
            	} catch (SQLException e) {
            		e.printStackTrace();
            		}
            if (exams != null) {
            	for (ExamDetails exam : exams) {
            	%>
            <div class="card" id="exam<%= exam.getExamId() %>">
                <div class="card-body">
                    <h5 class="card-title"><%= exam.getExamName() %></h5>
                    <p class="card-text"><%= exam.getDescription() %></p>
                </div>
            </div>
<%
        }
    }
%>
        </div>
        <div class="col-lg-4 col-md-5 col-sm-12 right-container">
            <div id="examDetails">
                <h2>Exam Details</h2>
                <p>Select an exam to view details.</p>
            </div>
             <div class="update-button">
                <button class="btn btn-info">Update</button>
            </div>
        </div>
    </div>
</div>
<div id="addExamModalPlaceholder"></div>
<script>
$(function(){
    // Load navbar content
    $("#nav-placeholder").load("navbar.jsp");

    // Load modal content and handle click events
    $("#addExamModalPlaceholder").load("AddNewExamModel.jsp", function() {
        // JavaScript to handle click event on cards
        $(".card").click(function(){
            // Get the exam ID from the card's ID
            var examId = $(this).attr("id").substring(4); // Remove "exam" from the ID
            
            // Retrieve exam details from the server using AJAX
            $.ajax({
                url: "GetExamDetailsServlet", // Replace with the servlet URL that retrieves exam details
                method: "GET",
                data: {examId: examId},
                success: function(response) {
                    // Update the exam details container with the retrieved details
                    $("#examDetails").html(response);
                },
                error: function(xhr, status, error) {
                    console.error("Error retrieving exam details:", error);
                }
            });
        });
    });
    
});
</script>
</body>

</html>

