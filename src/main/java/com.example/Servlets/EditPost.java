package com.example.Servlets;

import com.example.DBConnection.DBConnection;
import com.example.Model.Post;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditPost", urlPatterns = "/EditPost")
public class EditPost extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String username = req.getParameter("username");
//        String post = req.getParameter("postDetails");
        ServletContext context = req.getServletContext();
        Post editPost = (Post) context.getAttribute("editPost");

        String username = editPost.getUserName();
        String post = editPost.getPostDetails();
        String messageNew = req.getParameter("message");

        if(DBConnection.editPost(username, post, messageNew)){
            resp.sendRedirect("main.html");
        }else{
            resp.getWriter().print("Wololo");
        }

    }
}
