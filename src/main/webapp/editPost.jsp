<%@ page import="com.example.Model.Post" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <title>Edit Post</title>
</head>
<body>
<nav class="navbar navbar-inverse navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="main.html">Condolence Book</a>
        </div>

        <div class="navbar-header">
            <form action="Display" method="post"> <button type="submit" class="btn btn-primary"> Display Posts </button> </form> <br>
            <form action="Logout" method="post"> <button type="submit" class="btn btn-primary"> Log Out </button> </form>
        </div>
    </div>
</nav>

<div align="center" class="container">
    <form action="EditPost" method="get">
        <h2>Enter the new post...</h2>

        <h6><%=request.getParameter("username")%>: <%=request.getParameter("postDetails")%> </h6>

        <%
            //String[] values = request.getParameterValues("postEdit");
            String[] values = {request.getParameter("username"), request.getParameter("postDetails")};
            Post editPost = new Post(values[0], values[1]);
            request.setAttribute("editPost", editPost);
        %>

        <textarea id="message" name="message" placeholder="Write something.." style="width:400px; height:100px"></textarea><br>

        <input type="submit" value="Submit"><br><br>
    </form>
</div>


</body>
</html>