<%@ page import="java.util.ArrayList" %>
<%@ page import="com.samweb.model.UserInfo" %>


<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>User Profile</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f4f4f4;
    }
    .container {
      max-width: 800px;
      margin: 20px auto;
      background-color: #fff;
      border-radius: 10px;
      overflow: hidden;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    .details {
      padding: 20px;
      box-sizing: border-box;
      overflow: hidden;
    }
    .details h2 {
      margin-top: 0;
    }
    .details table {
      width: 100%;
      border-collapse: collapse;
    }
    .details th, .details td {
      padding: 8px;
      border: 1px solid #ccc;
    }
    .details th {
      background-color: #f2f2f2;
    }
    .details tr:nth-child(even) {
      background-color: #f9f9f9;
    }
    .details tr:hover {
      background-color: #e6e6e6;
    }
    .details .edit-btn {
      display: inline-block;
      padding: 6px 10px;
      background-color: #4CAF50;
      color: white;
      border: none;
      border-radius: 4px;
      cursor: pointer;
    }
.details .delete-btn {
    display: inline-block;
    padding: 6px 10px;
    background-color: #cc4444;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }
  .details .add-btn {
    display: inline-block;
    padding: 10px 20px;
    background-color: #4f5fb7;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }
    .details .edit-btn:hover {
      background-color: #45a049;
    }
    .details .delete-btn:hover {
    background-color: #6e0202;
    }
    .details .add-btn:hover {
    background-color: #4250a0;
    }
    .details .add-line {
      justify-content: center;
       align-items: center;
       padding-left: 220px;
    } 
  </style>
</head>
<body>
  <div class="container">
    <div class="details">
      <h2>User Details</h2>
      <table>
        <thead>
          <tr>
            <th>S.No</th>
            <th>Name</th>
            <th>Email</th>
            <!-- <th>Profession</th>
            <th>Age</th>
            <th>Phone Number</th> -->
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          <% 
         
          ArrayList<UserInfo> userRegister = (ArrayList<UserInfo>) request.getAttribute("userRegister");
            if (userRegister != null) {
              for (UserInfo info : userRegister) { %>
                <tr>
                  <td><%= userRegister.indexOf(info)+1%></td>
                  <td><%= info.getFullName() %></td>
                  <td><%= info.getEmail() %></td>
                  <%-- <td><%= info.getProfession() %></td>
                  <td><%= info.getAge() %></td>
                  <td><%= info.getPhoneNumber() %></td> --%>
                  <td>
                  <form action="activeServlet" method="post">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="deleteid" value="<%= info.getId() %>">
                        <button class="delete-btn" type="submit">Delete</button>
                    </form><br>
                        <input type="hidden" name="action" value="edit">
                         <input type="hidden" name="editid" value="<%= info.getId() %>">
                         <button class="edit-btn" type="button" onclick="location.href = 'update_user.jsp?editid=<%= info.getId() %>'">Edit</button>
                </tr>
              <% } 
            } %>
        </tbody>
      </table>
      <h4 class="add-line">Click here to Add new user <button class="add-btn" onclick="location.href = 'registerationForm.jsp'">ADD</button></h4>
    </div>
  </div>
</body>
</html>
