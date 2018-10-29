package com.example.Servlets;

import com.example.DBConnection.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "Display", urlPatterns = "/Display")
public class Display extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(DBConnection.connect()){
            resp.setContentType("text/html");

            try{
                ResultSet rs = DBConnection.displayPosts();

                PrintWriter out = resp.getWriter();

                out.println("<html> " +
                        "<head>" +
                        "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\"> " +
                        "</head>" +
                        "<body>" +
                        "<nav class=\"navbar navbar-inverse navbar-static-top\">\n" +
                        "    <div class=\"container\">\n" +
                        "        <div class=\"navbar-header\">\n" +
                        "            <a class=\"navbar-brand\" href=\"main.html\">Condolence Book</a>\n" +
                        "        </div>\n" +
                        "\n" +
                        "<div class=\"navbar-header\">\n" +
                        "     <form action=\"Logout\" method=\"post\"> <button type=\"submit\" class=\"btn btn-primary\"> Log Out </button> </form>\n" +
                        "</div>" +
                        "" +
                        "    </div>\n" +
                        "</nav>" +
                        "<div align = \"center\" class=\"container-fluid\">" +
                        "<ul class=\"list_group\"");

                if(rs.next()){
                    while(rs.next()) {
                        String username = rs.getString("username");
                        String postDetails = rs.getString("posts");

                        String str = String.format("<a href=Bridge?username=%s&postDetails=%s> Edit </a></li>", username, java.net.URLEncoder.encode(postDetails, "UTF-8"));

                        String[] toEdit = {username, postDetails};

                        req.setAttribute("postEdit", toEdit);

                        out.print("<li class=\"list-group-item\">" + username + ": ");
                        out.print(postDetails + " " + str);
                    }
                    out.println("</ul></div></body></html>");

                }else{
                    resp.getWriter().println("Nothing to display");
                }

            } catch (SQLException e) {
                e.getMessage();
            }
        }else{
            resp.getWriter().println("Error establishing a connection with the DB...");
        }
    }
}


//+ "<form action=\"Edit\" method=\"post\"> <button type=\"submit\"> Edit Post </button> </form>" +
//        "<form action=\"Delete\" method=\"post\"> <button type=\"submit\"> Delete Post </button> </form>");
//        }
//  "<a href=\"editPost.jsp\"?username=\"username\"&postDetails=\"postDetails\"> Edit </a>" + "</li>"
