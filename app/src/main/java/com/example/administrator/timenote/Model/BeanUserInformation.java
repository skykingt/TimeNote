package com.example.administrator.timenote.Model;

import java.util.Date;

/**
 * Created by Sprou on 2018/5/27.
 */

public class BeanUserInformation {
    public static BeanUserInformation currentLoginUser=null;
    public static BeanUserInformation tryLoginUser=null;
    private int userid;
    private String username;
    private String userpassword;
    private String useremail;
    private String drimindid;
    private Date creatdate;
    private Date stopdate;
    private Boolean usercalendar;
    private String usertypeface;
    private int pomoid;
    private int achievement;


    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getDrimindid() {
        return drimindid;
    }

    public void setDrimindid(String drimindid) {
        this.drimindid = drimindid;
    }

    public Date getCreatdate() {
        return creatdate;
    }

    public void setCreatdate(Date creatdate) {
        this.creatdate = creatdate;
    }

    public Date getStopdate() {
        return stopdate;
    }

    public void setStopdate(Date stopdate) {
        this.stopdate = stopdate;
    }

    public Boolean getUsercalendar() {
        return usercalendar;
    }

    public void setUsercalendar(Boolean usercalendar) {
        this.usercalendar = usercalendar;
    }

    public String getUsertypeface() {
        return usertypeface;
    }

    public void setUsertypeface(String usertypeface) {
        this.usertypeface = usertypeface;
    }

    public int getPomoid() {
        return pomoid;
    }

    public void setPomoid(int pomoid) {
        this.pomoid = pomoid;
    }

    public int getAchievement() {
        return achievement;
    }

    public void setAchievement(int achievement) {
        this.achievement = achievement;
    }
}
