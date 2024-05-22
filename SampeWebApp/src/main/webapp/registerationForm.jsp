<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Dancing+Script:wght@400..700&family=Pacifico&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="reg_form_styles.css">

     
        <title>Registration Form</title>
        <link rel="icon" type="image/x-icon" href="C:\Users\babu3560\Desktop\Registration form\images\favicon.ico">
    </head>
    <body>
        <div class="container">
            <div class="image-container">
                <h1>Lets get you an Account</h1>
            </div>
            <div class="main-reg">
               <form id="registrationForm" action="activeServlet" method="get" onsubmit="return validateForm()">
                <label for="name">Full Name:</label><br>
                <input type="text" id="name" name="name" required minlength="2" maxlength="30" placeholder="Enter your full name"><br>
                
                <label for="email">Email:</label><br>
                <input type="email" id="email" name="email" required  placeholder="Enter your email"><br>
                
                <label for="password">Password:</label><br>
                <input type="password" id="password" name="password" required minlength="8" maxlength="16" placeholder="Enter your password"><br>
                
                <label for="confirmPassword">Confirm Password:</label><br>
                <input type="password" id="confirmPassword" name="confirmPassword" required minlength="8" maxlength="16" placeholder="Enter your password again"><br>
                
              <!--   <label for="phone">Mobile Number:</label><br>
                <input type="number" id="phone" name="phone"placeholder="Enter your mobile number"><br>
                
                <label for="age">Enter age:</label><br>
                <input type="number" id="age" name="age" required  placeholder="Enter your age"  min="1" max="100"><br>
                
                <label for="profession">profession:</label><br>
                <input type="text" id="profession" name="profession" required  placeholder="Enter your profession"><br>
             -->
                <button type="submit">Register</button>
                </form>
<script>
    function validateForm() {
        var password = document.getElementById("password").value;
        var confirmPassword = document.getElementById("confirmPassword").value;

        if (password != confirmPassword) {
            alert("Passwords do not match.");
            return false;
        }
        return true;
    }
</script>
 <h5 class="sign-navigator">Already have an Account? <a href="login_page.html">Sign In</a></h5>
            </div>
        </div>
        
    </body>
</html>
