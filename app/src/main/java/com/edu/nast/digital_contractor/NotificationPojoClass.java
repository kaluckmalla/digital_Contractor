package com.edu.nast.digital_contractor;

public class NotificationPojoClass {
    private String adminPhone;
    private String Notification;
    private String Date;

    public NotificationPojoClass() {
    }

    public NotificationPojoClass(String adminPhone, String notification, String date) {
        this.adminPhone = adminPhone;
        Notification = notification;
        Date = date;
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }

    public String getNotification() {
        return Notification;
    }

    public void setNotification(String notification) {
        Notification = notification;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
