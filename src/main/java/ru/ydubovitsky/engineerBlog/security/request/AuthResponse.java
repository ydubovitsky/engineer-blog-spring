package ru.ydubovitsky.engineerBlog.security.request;

public class AuthResponse {

    private String jwttoken;
    private String username;

    public AuthResponse(String jwttoken, String user) {
        this.jwttoken = jwttoken;
        this.username = user;
    }

    public String getJwttoken() {
        return jwttoken;
    }

    public void setJwttoken(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
