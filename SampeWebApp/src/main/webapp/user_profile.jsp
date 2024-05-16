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
    .profile {
      float: right;
      width: 40%;
      padding: 20px;
      box-sizing: border-box;
    }
    .profile img {
      width: 100%;
      border-radius: 50%;
      border: 5px solid #fff;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
    }
    .details {
      padding: 20px;
      box-sizing: border-box;
      overflow: hidden;
    }
    .details h2 {
      margin-top: 0;
    }
    .details p {
      margin: 10px 0;
    }
  </style>
</head>
<body>
  <div class="container">
    <div class="profile">
      <img src="profile-image.jpg" alt="Profile Image">
    </div>
    <div class="details">
      <h2>User Details</h2>
      <p><strong>Name:</strong> John Doe</p>
      <p><strong>Email:</strong> johndoe@example.com</p>
      <p><strong>Date of Birth:</strong> January 1, 1990</p>
      <p><strong>Age:</strong> 34</p>
      <p><strong>Profession:</strong> Software Engineer</p>
      <!-- Add more details as needed -->
    </div>
  </div>
</body>
</html>

