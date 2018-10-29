package com.example.Model;

public class Post {
    private String userName;
    private String postDetails;

    public Post(String userName, String postDetails) {
        this.userName = userName;
        this.postDetails = postDetails;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPostDetails() {
        return postDetails;
    }

    public void setPostDetails(String postDetails) {
        this.postDetails = postDetails;
    }
}
