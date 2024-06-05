<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <link rel="stylesheet" href="welcome_styles.css">
    <title>Home Screen</title>
</head>
<body>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setHeader("Expires", "0"); // Proxies.

Cookie[] cookies = request.getCookies();
boolean authenticated = false;
if (cookies != null) {
    for (Cookie cookie : cookies) {
        if (cookie.getName().equals("user")) {
        	System.out.println(cookie.getName() + "-" + cookie.getValue());
        	
            authenticated = true;
            break;
        }
    }
}
if (!authenticated) {
    response.sendRedirect("login_page.html");
    return;
}

    if (session == null || session.getAttribute("password") == null) {
    	System.out.println("session null from home page");
        response.sendRedirect("login_page.html");
        return;
    }
%>
<header>
    <nav class="navbar navbar-expand-lg custom-navbar">
        <div class="container-fluid">
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
              <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="welcomeScreen.html">Home</a>
              </li>
              <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="support_page.html">Services</a>
              </li>
              <li class="nav-item"> 
                <a class="nav-link active" aria-current="page" href="about_us_page.html">Contact Admin</a>
              </li>
              <form class="d-flex search-form" role="search">
                <input class="form-control me-2 search-bar" type="search" placeholder="Search Courses..." aria-label="Search">
                <button class="btn btn-outline-success submit-button btn-black" type="submit">
                    <i class="fas fa-search"></i>
                </button>
            </form>                
            </ul>
            <div>
            <form action="LogoutServlet" method="get">
                <button type="submit" class="btn btn-dark">Log out</button>
                </form>
            </div>
          </div>
        </div>
      </nav>
</header>
</body>
</html>
