/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unitedairlinesdemo;

/**
 *
 * @author Mital Patel
 */
public class MemberLogin {
    private String Memberid;
    private String Password;
    private String SecQue1;
    private String SecAns1;
    private String SecQue2;
    private String SecAns2;

    public String getMemberid() {
        return Memberid;
    }

    public void setMemberid(String Memberid) {
        this.Memberid = Memberid;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getSecQue1() {
        return SecQue1;
    }

    public void setSecQue1(String SecQue1) {
        this.SecQue1 = SecQue1;
    }

    public String getSecAns1() {
        return SecAns1;
    }

    public void setSecAns1(String SecAns1) {
        this.SecAns1 = SecAns1;
    }

    public String getSecQue2() {
        return SecQue2;
    }

    public void setSecQue2(String SecQue2) {
        this.SecQue2 = SecQue2;
    }

    public String getSecAns2() {
        return SecAns2;
    }

    public void setSecAns2(String SecAns2) {
        this.SecAns2 = SecAns2;
    }

    @Override
    public String toString() {
        return "Login{" + "Memberid=" + Memberid + ", Password=" + Password + ", SecQue1=" + SecQue1 + ", SecAns1=" + SecAns1 + ", SecQue2=" + SecQue2 + ", SecAns2=" + SecAns2 + '}';
    }
}
