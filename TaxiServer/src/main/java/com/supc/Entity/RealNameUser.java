package com.supc.Entity;

import it.unisa.dia.gas.jpbc.Element;

import java.io.Serializable;

public class RealNameUser implements Serializable {
    private String userId;
    private String phoneNum;
    private String password;
    private Element C;

    public RealNameUser() {
    }

    public RealNameUser(String userId, String phoneNum, String password) {
        this.userId = userId;
        this.phoneNum = phoneNum;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Element getC() {
        return C;
    }

    public void setC(Element c) {
        C = c;
    }
}
