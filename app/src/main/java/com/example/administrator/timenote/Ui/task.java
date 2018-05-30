package com.example.administrator.timenote.Ui;

import java.util.Date;

public class task {
    private Date date_1;
    private String taskdes;
    private String taskname;
    private String taskp;
    private int imageId;
    private int ViewId;
    public int getImageId() {
        return imageId;
    }

    public int getViewId() {
        return ViewId;
    }



    public String getTaskdes() {
        return taskdes;
    }

    public String getTaskp() {
        return taskp;
    }
    public task(Date date_1){
        this.date_1=date_1;
    }



    public task(String taskname, Date date_1,String taskdes,String taskp,int imageId,int viewId){
        this.date_1=date_1;
        this.taskname=taskname;
        this.taskdes=taskdes;
        this.taskp=taskp;
        this.imageId=imageId;
        this.ViewId=viewId;
    }
    public String getTaskname() {
        return taskname;
    }


    public Date getDate_1() {
        return date_1;
    }


}
