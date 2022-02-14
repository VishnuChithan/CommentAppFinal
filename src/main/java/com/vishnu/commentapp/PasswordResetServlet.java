package com.vishnu.commentapp;

import CommentDatabase.UserDB;
import extras.CustomEncrypt;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "reset-password", value = "/reset-password")
public class PasswordResetServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username=request.getParameter("userEmail");
        String code= new CustomEncrypt().encrypt(request.getParameter("userPass"));
        try {
            String result=UserDB.getInstance().verifyUser(username,code);
            if(result!=null){
                result=new CustomEncrypt().decrypt(result);
                request.getSession().setAttribute("resetResult","Password to login: "+result);
            }
            else{
                request.getSession().setAttribute("resetResult","Details does not match.!");
            }
            response.sendRedirect("forgot_password.jsp");

        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
            request.getSession().setAttribute("resetResult","Something went wrong "+e.getMessage());
            response.sendRedirect("forgot_password.jsp");
        }
    }
}
