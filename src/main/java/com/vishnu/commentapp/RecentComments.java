package com.vishnu.commentapp;

import CommentDatabase.CommentsDB;
import extras.CommentsData;
import extras.TimeComparator;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

@WebServlet(name = "recent-comments",value = "/recent-comments")

public class RecentComments extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CommentsDB commentsDB = new CommentsDB();
        ArrayList<CommentsData> result = commentsDB.retrieveComments();
        System.out.println("V="+result.size());

        if (result.isEmpty()) {
            request.getSession().setAttribute("commentExist", "false");
            request.getSession().setAttribute("retrievedComments", "");
        } else {
            result.sort(new TimeComparator());
            request.getSession().setAttribute("commentExist", "true");
            request.getSession().setAttribute("retrievedComments", result);
        }
        System.out.println(result);
        response.sendRedirect("comment.jsp");
    }
}
