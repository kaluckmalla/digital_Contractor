package com.springrest.springrest.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admin_notification")
public class AdminNotification {
	public AdminNotification() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private long id;
@Override
public String toString() {
	return "AdminNotification [id=" + id + ", adminPhone=" + adminPhone + ", Notification=" + Notification + ", Date="
			+ Date + "]";
}
public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
public AdminNotification(long id, String adminPhone, String notification, String date) {
		super();
		this.id = id;
		this.adminPhone = adminPhone;
		Notification = notification;
		Date = date;
	}
public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}

private String adminPhone;
private String Notification;
private String Date;

}
