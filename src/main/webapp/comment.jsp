<%@ page import="com.vishnu.commentapp.CommentsFetch" %>
<%@ page import="extras.CommentsData" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>

<head>
    <title>Comment</title>
    <link href="css/style.css" rel="stylesheet" />
</head>

<script>
    let count = 1;
    function addCommentContainer(username,comment){
        let emptyDiv = document.createElement('DIV');
        let parentDiv = document.createElement('DIV');
        emptyDiv.id = "commentBox" + count;
        emptyDiv.className="user-comment";
        emptyDiv.innerText=comment;
        const commentLabel = document.createElement("Label");
        commentLabel.setAttribute("for",emptyDiv.id);
        commentLabel.style="color:black";
        commentLabel.innerHTML = username;
        parentDiv.append(commentLabel);
        parentDiv.append(emptyDiv);
        document.body.append(parentDiv);
        parentDiv.style="margin-top: 2%;";
        parentDiv.className="comment-container";
        document.getElementById("exist").append(parentDiv)
        count=count+1;
    }
</script>

<body>
<%
    response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
    HttpSession loginSession=request.getSession();
    System.out.println(loginSession.getAttribute("loggedInUser"));
    if(loginSession.getAttribute("loggedInUser")==null){
        response.sendRedirect("index.jsp");
    }
%>
<header class="header">
    <a href="index.jsp" id="logo">
        <img class="logo" src="bg/logo.jpg" alt="CommentApp Logo" />
    </a>
    <a class="main-nav-link " href="logout" id="logout">Logout</a>

</header>

<div class="comment-main">
<div class="comments-section">
    <div>
        <form method="post" action="post-comment" class="new-comment-section">
        <div class="sharing-section">
            <label for="user-entered-comment">What would you like to share with world</label>
            <textarea name="user-entered-comment" maxlength="250" class="user-entered-comment" id="user-entered-comment">${userComment}</textarea>
        </div>
        <input type="submit" class="comment-submit-btn" value="Submit"/>
        </form>
        <span class="error-label">${commentStatus}</span>
        <% request.getSession().removeAttribute("commentStatus");%>
    </div>
    <div class="refresh-comment">
        <form method="post" action="commentsFetch">
            <input type="submit" value="Refresh" class="align-right"/>
        </form>
    </div>
    <div class="existing-comment-section">
        <div class="existing-comment-header" id="existing-comment-header">
            <h1>Comments</h1>
            <form method="post" action="recent-comments">
                <input type="submit"  class="comment-submit-btn filter-btn" value="Filter"/>
            </form>
        </div>
        <div id="exist"></div>
            <% if(request.getSession().getAttribute("commentExist")!=null && request.getSession().getAttribute("commentExist").equals("true")){
                for (CommentsData commentData:(ArrayList<CommentsData>) request.getSession().getAttribute("retrievedComments")){ %>
            <script>addCommentContainer("<%=commentData.getUsername()%>","<%=commentData.getComment()%>");</script>
            <% }
            }
            System.out.println(request.getSession().getAttribute("commentExist"));
            %>
        </div>
    </div>
</div>
</body>

</html>
