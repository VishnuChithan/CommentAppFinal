package com.vishnu.commentapp;

import CommentDatabase.UserDB;
import extras.CustomEncrypt;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "sign-in-servlet", value = "/sign-in-servlet")
public class SignInServlet  extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher;
        if(request.getSession().getAttribute("loggedInUser")!=null){
            requestDispatcher=request.getRequestDispatcher("commentsFetch");
            requestDispatcher.forward(request,response);
        }
        if(UserDB.getInstance().authenticate(request.getParameter("userEmail"),new CustomEncrypt().encrypt(request.getParameter("userPass")))){
            request.getSession().setAttribute("loggedInUser",request.getParameter("userEmail"));
            requestDispatcher=request.getRequestDispatcher("commentsFetch");
            requestDispatcher.forward(request,response);
        }
        else{
            request.getSession().setAttribute("result","Invalid Credentials");
            response.sendRedirect("index.jsp");
        }
    }
}
