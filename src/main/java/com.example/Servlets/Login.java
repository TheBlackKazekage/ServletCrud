package com.example.Servlets;

import com.africastalking.AfricasTalking;
import com.africastalking.SmsService;
import com.africastalking.sms.Recipient;
import com.example.DBConnection.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "Login", urlPatterns = "/Login")
public class Login extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Initialize
        String username = "testApp1st";    // use 'sandbox' for development in the test environment
        String apiKey = "b240ee759ae89a58961aa870c808974811e0d93a0e822de4b44763f4b1e123d2";       // use your sandbox app API key for development in the test environment
        AfricasTalking.initialize(username, apiKey);

// Initialize a service e.g. SMS
        SmsService sms = AfricasTalking.getService(AfricasTalking.SERVICE_SMS);

// Use the service
        List<Recipient> response = sms.send("Hello Message!", new String[] {"+254700835636"}, true);

        for(Recipient s : response){
            System.out.println(s.cost);
            System.out.println(s.messageId);
            System.out.println(s.number);
            System.out.println(s.status);
        }

        if(DBConnection.connect()){
            String username1 = req.getParameter("username");
            String password = req.getParameter("password");

            if(DBConnection.login(username1, password)){
                HttpSession session = req.getSession();
                session.setAttribute("username", username);

                resp.sendRedirect("main.html");
            }else{
//                PrintWriter out = resp.getWriter();
//                out.println("Error logging in. Try again...");

                resp.sendRedirect("login.html");

//                req.setAttribute("status_msg", "Create Post not successful");
//                req.getRequestDispatcher("login.html").forward(req, resp);
            }
        }else{
            resp.getWriter().println("Error establishing a connection with the DB...");
        }
    }
}
