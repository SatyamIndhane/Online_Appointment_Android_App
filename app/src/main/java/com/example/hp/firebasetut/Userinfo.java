package com.example.hp.firebasetut;

import java.io.Serializable;



public class Userinfo implements Serializable {
    public String userid;
    public String name;
    public String contact;
    public String reason;
    public String rejectreason;
    public String time;


    public Userinfo() {
    }

    public Userinfo(String userid, String name, String contact, String reason, String time, String rejectreason) {
        this.userid=userid;
        this.name = name;
        this.contact = contact;
        this.reason = reason;
        this.rejectreason = rejectreason;
        this.time = time;
    }

    public String getUserid() {
        return userid;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getReason() {
        return reason;
    }

    public String getRejectreason() {
        return rejectreason;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Userinfo{" +
                "userid='" + userid + '\'' +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", reason='" + reason + '\'' +
                ", rejectreason='" + rejectreason + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
