package com.example.medical;

import android.widget.EditText;

public class User {
    public String name, email, pass, aadhar, phone,age,bloodgroup;

    public User() {}

    public User(String name, String email, String pass, String aadhar, String phone, String age, String bloodgroup) {
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.aadhar = aadhar;
        this.phone = phone;
        this.age = age;
        this.bloodgroup = bloodgroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }
}

