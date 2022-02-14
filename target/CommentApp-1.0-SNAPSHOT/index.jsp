<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/style.css" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css2?family=Rubik:ital,wght@0,400;0,500;0,600;1,600&display=swap" rel="stylesheet">
    <title>CommentApp</title>
</head>
<body>
<%
    response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
    HttpSession loginSession=request.getSession();
    if(loginSession.getAttribute("loggedInUser")!=null){
        response.sendRedirect("comment.jsp");
    }
    String emailEntered= (String) request.getSession().getAttribute("emailEntered");
    if(emailEntered==null){
        emailEntered="";
    }
%>
<div class="main">
    <div class="container">
        <h1 class="heading-primary">Sign In</h1>
        <span>Don't have an account?</span>
        <a href="signup.jsp">Sign Up </a>
        <form class="sign-in-container" action="sign-in-servlet" method="post">
            <div>
                <label>Email</label>
                <input type="email" name="userEmail" required value="<%=emailEntered%>"/>
            </div>
            <div>
                <label>Password</label>
                <input type="password" name="userPass" required />
            </div>
            <div>
                <a href="forgot_password.jsp">Forgot your password?</a>
            </div>
            <input type="submit" class="btn btn--form " name="Sign In" value="Sign In"/>
            <p class="error-label">${result}</p>
            <%
                session.removeAttribute("result");
            %>

        </form>
    </div>
</div>
</body>

</html>