package com.springrest.springrest.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.springrest.springrest.entities.AdminNotification;
import com.springrest.springrest.entities.HouseUpload;
import com.springrest.springrest.entities.LandUpload;
import com.springrest.springrest.entities.SignUp;

public interface DConService {
	ResponseEntity<String> signUpInfo(SignUp signup);//For Sign up

	String loginAuth(long parseLong, String pass);//For login

	ResponseEntity<String> addHouse(HouseUpload houseproperty);//uploading house

	ResponseEntity<String> addLand(LandUpload landproperty);//uploading land

	ResponseEntity<List<HouseUpload>> housesProperties();//retrieving all  houses list

	ResponseEntity<List<LandUpload>> landProperties();//retrieving all land list

	ResponseEntity<List<HouseUpload>> singleUserHouseProperties(long parseLong);//Retrieving single user houses property

	ResponseEntity<List<LandUpload>> singleUserLandProperties(long parseLong);//Retrieving single user land property


	ResponseEntity<String> getUserCount();//total user count

	ResponseEntity<String> getCountHouse();//total house count

	ResponseEntity<String> getCountLand();//total land count

	ResponseEntity<String> addNotification(AdminNotification newnotice);//adding notification by admin

	ResponseEntity<List<AdminNotification>> getAllNotification();//getting all notification



}
