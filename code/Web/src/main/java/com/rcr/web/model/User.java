package com.rcr.web.model;


import com.rcr.web.model.Role;

public class User {

    private String username;

    private String password;

    private Role role;

    public User() {
    }

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean canPerform(String operation) {
        if (role.getOperations().contains("all")) return true;
        return role.getOperations().contains(operation);
    }
}
