<%@ page import="java.util.HashMap" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/style.css" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css2?family=Rubik:ital,wght@0,400;0,500;0,600;1,600&display=swap" rel="stylesheet">
    <title>SignUp for CommentApp</title>
</head>

<body>
<%
    String emailEntered= (String) request.getSession().getAttribute("emailEntered");
    if(emailEntered==null){
        emailEntered="";
    }

%>
    <div class="main">
        <div class="container">
            <h1 class="heading-primary">Sign Up</h1>
            <form class="sign-in-container" action="signup-servlet" method="post">
                <div>
                    <label for="userEmail">Email</label>
                    <input type="email" name="userEmail" id="userEmail" value="<%=emailEntered%>" required/>
                    <label class="error-label">${emailError}</label>
                </div>
                <div>
                    <label for="userPassword">Password</label>
                    <input type="password" name="userPass" id="userPassword"required />
                    <label class="error-label">${passwordError}</label>
                </div>
                <div>
                    <label for="userSecret">Secret</label>
                    <input type="password" name="userSecret" id="userSecret" required />
                    <label class="error-label">${secretError}</label>
                </div>

                <button type="submit" class="btn btn--form">Sign Up</button>
                <p class="signup-terms">By clicking the "Sign up" button, you are creating an account, and you agree to Terms of Use</p>
                <p class="error-label">${result}</p>
                <% session.removeAttribute("emailError");
                    session.removeAttribute("passwordError");
                    session.removeAttribute("userSecret");
                %>
            </form>
        </div>
    </div>
</body>

</html>