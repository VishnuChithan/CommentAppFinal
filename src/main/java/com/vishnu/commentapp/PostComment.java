package com.vishnu.commentapp;

import CommentDatabase.CommentsDB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "post-comment",value = "/post-comment")
public class PostComment extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String commentReceived=request.getParameter("user-entered-comment");
        String loggedInUser= (String) request.getSession().getAttribute("loggedInUser");
        System.out.println(commentReceived);
        System.out.println(loggedInUser);
        if(loggedInUser!=null && commentReceived!=null && commentReceived.length()!=0){
            CommentsDB commentsDB=new CommentsDB();
            if(commentsDB.addComment(loggedInUser,commentReceived)){
                request.getSession().setAttribute("commentStatus","Comments added successfully!");
                request.getSession().setAttribute("userComment","");
                RequestDispatcher requestDispatcher=request.getRequestDispatcher("commentsFetch");
                requestDispatcher.forward(request,response);
            }
            else{
                request.getSession().setAttribute("commentStatus","Something went wrong! Please try again");
                response.sendRedirect("comment.jsp");
            }
        }
        else{
            request.getSession().setAttribute("commentStatus","Please provide comment and Email ID to add comments");
            response.sendRedirect("comment.jsp");
        }



    }
}
