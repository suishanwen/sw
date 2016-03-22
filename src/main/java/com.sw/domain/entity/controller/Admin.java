package com.sw.domain.entity.controller;


import javax.persistence.*;

@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String admin;
    private String password;
    private String state;
    private String employeeNo;

    public Admin() {
    }

    public Admin(String admin, String password) {
        this.admin = admin;
        this.password = password;
    }

    public Admin(String admin, String password, String state,String employeeNo) {
        this.admin = admin;
        this.password = password;
        this.state = state;
        this.employeeNo = employeeNo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }
}
