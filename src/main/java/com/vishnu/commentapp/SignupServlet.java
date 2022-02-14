package com.vishnu.commentapp;

import CommentDatabase.UserDB;
import extras.CustomEncrypt;
import extras.FieldValidation;
import extras.UserDetails;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "signup-servlet", value = "/signup-servlet")
public class SignupServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        FieldValidation fieldValidation=new FieldValidation();
        UserDetails userDetails=new UserDetails();
        userDetails.setUsername(request.getParameter("userEmail"));
        userDetails.setPassword(request.getParameter("userPass"));
        userDetails.setSecretCode(request.getParameter("userSecret"));
        HashMap<String, Boolean> validation=fieldValidation.validate(userDetails);
        if(validation.get("emailValidation") && validation.get("passwordValidation") && validation.get("secretValidation")){
            if(UserDB.getInstance().isUserExist(userDetails.getUsername())){
                request.getSession().setAttribute("emailError","This account already exist");
                request.getSession().setAttribute("emailEntered",userDetails.getUsername());
                response.sendRedirect("signup.jsp");
            }
            else{
                userDetails.setPassword(new CustomEncrypt().encrypt(userDetails.getPassword()));
                userDetails.setSecretCode(new CustomEncrypt().encrypt(userDetails.getSecretCode()));
                if(UserDB.getInstance().addUser(userDetails)){
                    request.getSession().setAttribute("result","Signed up successfully.");
                    response.sendRedirect("index.jsp");
                }
                else{
                    request.getSession().setAttribute("result","Something went wrong! Please try again");
                    response.sendRedirect("signup.jsp");
                }
            }
        }
        else{
            HttpSession session=request.getSession();
            session.setAttribute("emailEntered",userDetails.getUsername());
            if(!validation.get("emailValidation")){
                session.setAttribute("emailError","Please Provide Valid Email ID");
            }
            else{
                session.removeAttribute("emailError");
            }
            if(!validation.get("passwordValidation")){
                session.setAttribute("passwordError","Please Provide Valid Password with length greater than 5");
            }
            else{
                session.removeAttribute("passwordError");
            }
            if(!validation.get("secretValidation")){
                session.setAttribute("secretError","Please Provide Secret code with length greater than 3 ");
            }
            else{
                session.removeAttribute("secretError");
            }
            response.sendRedirect("signup.jsp");
        }

    }

    public void destroy() {
    }
}