<%@ page import="java.util.ArrayList" %>
<%@ page import="com.samweb.model.UserInfo" %>


<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
  <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
 
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
      padding-top:50px;
      justify-content: center;
       align-items: center;
       padding-left: 220px;
    }
    .search-form {
    width:  400px;
    padding: 10px;
    padding-bottom: 20px;
    
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
.button-row {
    display: flex;
    gap: 10px; /* Adjust the gap between buttons as needed */
}

.button-form {
    margin: 0; /* Ensure no extra margin around forms */
}
.d-container {
    display: flex;
    justify-content: flex-end; /* Align items to the right */
    padding: 20px; /* Optional: add padding to the container */
}

.custom-btn {
    padding: 10px 20px; /* Adjust padding as needed */
}

  </style>
</head>
<body>
  <div class="container">
    <div class="details">
      <h2>User Details</h2>
      <form class="d-flex search-form" role="search" action="SearchServlet" method="GET">
      <input class="form-control me-2 search-bar" type="search" name="query" placeholder="Search User..." aria-label="Search">
      <button class="btn btn-outline-success submit-button btn-black" type="submit">
      <i class="fas fa-search"></i>
      </button>
      </form>
      <div class="d-container">
      <div class="dropdown">
        <button class="btn btn-secondary dropdown-toggle custom-btn" type="button" data-bs-toggle="dropdown" aria-expanded="false">
            Order By
        </button>
        <ul class="dropdown-menu">
            <li><button class="dropdown-item" type="button">Ascending Order</button></li>
            <li><button class="dropdown-item" type="button">Descending Order</button></li>
        </ul>
        </div>
        </div>
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
                  <div class="button-row">
                   <form action="activeServlet" method="post">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="deleteid" value="<%= info.getId() %>">
                        <button class="delete-btn" type="submit">Delete</button>
                    </form>	
                        <input type="hidden" name="action" value="edit">
                         <input type="hidden" name="editid" value="<%= info.getId() %>">
                         <button class="edit-btn" type="button" onclick="location.href = 'update_user.jsp?editid=<%= info.getId() %>'">Edit</button>
                  </div>
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
