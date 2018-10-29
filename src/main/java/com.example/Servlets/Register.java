package com.example.Servlets;

import com.example.DBConnection.DBConnection;
import sun.security.pkcs11.Secmod;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Register", urlPatterns = "/Register")
public class Register extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String username = req.getParameter("username");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String password = req.getParameter("password");


        if(DBConnection.connect()){
           boolean newUser = DBConnection.newUser(username, firstName, lastName, password);

            if(newUser){
                resp.sendRedirect("index.jsp");

            }else{
                resp.sendRedirect("register.html");
            }
        }else{
            resp.getWriter().println("Error establishing a connection with the DB...");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }


}
