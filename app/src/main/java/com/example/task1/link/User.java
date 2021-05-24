package com.example.task1.link;

public class User {

    private int id;
    private String name;
    private String username;
    private String password;
    private String sex;
    private String phone;


    public User() {
    }

    public User(int id, String name, String username, String password, String sex, String phone) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.sex=sex;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() { return sex; }

    public void setSex(String sex) { this.sex = sex; }
}

