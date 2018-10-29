package com.example.DBConnection;

import java.sql.*;

public class DBConnection {
    private static Connection conn = null;
    private static Statement statement = null;
    private static ResultSet rs = null;

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/servlet_crud?useSSL=false";

    private static final String USER = "root";
    private static final String PASS = "password";

    public static boolean connect(){
        try{
            Class.forName(JDBC_DRIVER);

            //System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            if(conn != null){
                return true;
            }

        }catch (SQLException | ClassNotFoundException e){
            e.getMessage();
        }

        return false;
    }

    public static boolean newUser(String username, String firstName, String lastName, String password){
        connect();

        String query2 = "INSERT INTO users VALUES(?, ?, ?, ?)";

        try{
            conn.setAutoCommit(false);
            PreparedStatement statement1 = conn.prepareStatement(query2);

            statement1.setString(1, username);
            statement1.setString(2, firstName);
            statement1.setString(3, lastName);
            statement1.setString(4, password);
            int i = statement1.executeUpdate();

            if(i > 0){
                conn.commit();
                return true;
            }

//            PreparedStatement ps = con.prepareStatement("insert into USERDETAILS values(?,?,?,?)");
//
//            ps.setString(1, n);
//            ps.setString(2, p);
//            ps.setString(3, e);
//            ps.setString(4, c);
//
//            int i = ps.executeUpdate();
//            if (i > 0)
//                out.print("You are successfully registered...");

            conn.close();

        } catch (SQLException e) {
            e.getMessage();
        }

        return false;
    }

    public static boolean login(String username, String password){
        String query = "SELECT * from users WHERE username = ? and password = ?";

        try{
            connect();

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static ResultSet displayPosts(){
        String query = "SELECT username, posts FROM posts";

        try{
            connect();

            statement = conn.createStatement();

            rs = statement.executeQuery(query);

            if(rs != null){
                return rs;
            }

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    public static boolean newPost(String username, String message){
        String query = "INSERT INTO posts VALUES(?, ?)";

        try{
            connect();

            PreparedStatement stm = conn.prepareStatement(query);

            stm.setString(1, username);
            stm.setString(2, message);

            int i = stm.executeUpdate();

            if(i > 0){
                conn.commit();
                return true;
            }
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean editPost(String username, String messagePrev, String messageNew){
        String query = "UPDATE posts SET username = ? posts = ? WHERE username = ? AND posts = ?";

        try{
            connect();
            PreparedStatement stm = conn.prepareStatement(query);

            stm.setString(1, username);
            stm.setString(2, messageNew);
            stm.setString(3, username);
            stm.setString(4, messagePrev);

            int i = stm.executeUpdate();
            if(i > 0){
                conn.commit();
                return true;
            }

            conn.close();
        } catch (SQLException e) {
            e.getMessage();
        }

        return false;
    }

}
