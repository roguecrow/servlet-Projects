<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
.main-reg {
    max-width: 400px;
    height: fit-content;
    margin: 0 auto;
    padding: 40px;
    background-color: rgba(236,236,236,255);  
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    font-family: 'CustomFontRegular', sans-serif;
}
.image-container h1 {
    font-family: "Dancing Script", cursive;
    display: flex;
    justify-content: center;
    align-items: center;
    font-optical-sizing: auto;
    font-style: normal;
}

form {
    padding-right: 100px;
}

input[type="text"], 
input[type="email"],
input[type="password"],
input[type="date"],
input[type="number"], 
input[type="phone"],
button[type="submit"] {
    width: 150%;
    padding: 10px;
    margin-bottom: 15px;
    border: 1px solid #ccc;
    border-radius: 5px;
}

button[type="submit"] {
    background-color: #ff735c;
    color: #fff;
    cursor: pointer;
}

button[type="submit"]:hover {
    background-color: #961400;
}

</style>
</head>
    <body>
        <div class="container">
            <div class="image-container">
                <h1>Update your account details</h1>
            </div>
            <div class="main-reg">
               <form id="updationForm" action="activeServlet" method="post">
                <label for="name">Full Name:</label><br>
                <input type="text" id="name" name="name" required minlength="2" maxlength="30" placeholder="Enter your full name"><br>
                
                <label for="email">Email:</label><br>
                <input type="email" id="email" name="email" required  placeholder="Enter your email"><br>
                
                <input type="hidden" name="action" value="edit">
                <input type="hidden" name="editid" value="<%= request.getParameter("editid") %>">
                <button type="submit">Update</button>
                </form>
            </div>
        </div>
        
    </body>
</html>