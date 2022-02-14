<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/style.css" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css2?family=Rubik:ital,wght@0,400;0,500;0,600;1,600&display=swap" rel="stylesheet">
    <title>Forgot Password</title>
</head>

<body>
    <div class="main">
        <div class="container">
            <h1 class="heading-primary">Forgot Password</h1>
            <form class="sign-in-container" method="post" action="reset-password" >
                <div>
                    <label>Email</label>
                    <input type="email" name="userEmail" required />
                </div>
                <div>
                    <label>Secret</label>
                    <input type="password" name="userPass" required />
                </div>
                <input type="submit" class="btn btn--form " name="VerifyDetails" value="Verify Details"/>
                <p class="error-label">${resetResult}</p>
                <%request.getSession().removeAttribute("resetResult");%>
            </form>
        </div>
    </div>
</body>

</html>