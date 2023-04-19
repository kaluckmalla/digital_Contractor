package com.springrest.springrest.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sign_up")
//@IdClass(SignUpPK.class)
////For two primary key in same table
//@Data
//@AllArgsConstructor
//@NoArgsConstructor

public class SignUp {
	
	
//	//@EmbeddedId
//	@Column(name="Id")
//	@Id
//    private long userId;
	
	@Id
    private long phoneNumber;	
	
		
	@Column(nullable = false)
	private String userType;
	
	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;
	
	@Column(nullable = false)
	private String address;
	
	
	@Column(nullable = false,unique=true)
    private String email;
	
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = true)
	private String date;
	
	@Column(nullable = false)
	private String status;
	
	
	//default constructor
	public SignUp() {
		super();
		// TODO Auto-generated constructor stub
	}

	//constructor using fields

	public SignUp(long phoneNumber, String userType, String firstName, String lastName, String address,
			String email, String password,String status,String date) {
		super();
		//this.userId = userId;
		this.phoneNumber = phoneNumber;

		this.userType = userType;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.password = password;
		this.date = date;
		this.status = status;

		
	}
	
	//getter and setter

	public long getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


//	public long getUserId() {
//		return userId;
//	}
//
//
//	public void setUserId(long userId) {
//		this.userId = userId;
//	}


	public String getUserType() {
		return userType;
	}


	public void setUserType(String userType) {
		this.userType = userType;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	
	
	
	//toString For return

	@Override
	public String toString() {
		return "SignUp [phoneNumber=" + phoneNumber + ", userType=" + userType + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", address=" + address + ", email=" + email + ", password=" + password
				+ ", date=" + date + ", status=" + status + ", getPhoneNumber()=" + getPhoneNumber()
				+ ", getUserType()=" + getUserType() + ", getFirstName()=" + getFirstName() + ", getLastName()="
				+ getLastName() + ", getAddress()=" + getAddress() + ", getEmail()=" + getEmail() + ", getPassword()="
				+ getPassword() + ", getDate()=" + getDate() + ", getStatus()=" + getStatus() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}


}
