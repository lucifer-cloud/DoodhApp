package com.multirest.vishalverma.doodhmaster.Model;

public class VendorGeneralModel {
    public VendorGeneralModel(String email, String name, String phone, String uid) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.uid = uid;
    }

    public String email;
    public String name;
    public String phone;
    public String uid;

    public VendorGeneralModel() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
