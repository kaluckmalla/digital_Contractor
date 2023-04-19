package com.springrest.springrest.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.springrest.springrest.dconrepository.AdminNootificationRepository;
import com.springrest.springrest.dconrepository.SignUpRepository;
import com.springrest.springrest.dconrepository.UploadHouseRepository;
import com.springrest.springrest.dconrepository.UploadLandRepository;
import com.springrest.springrest.entities.AdminNotification;
import com.springrest.springrest.entities.HouseUpload;
import com.springrest.springrest.entities.LandUpload;
import com.springrest.springrest.entities.SignUp;
@Service
public class DconServiceImpl implements DConService{
	@Autowired	
	private SignUpRepository signUpRepo;//Used by Sign up,login 
	@Autowired
	private UploadHouseRepository uploadHouseRepo;
	@Autowired
	private UploadLandRepository uploadLandRepo;
	@Autowired
	private AdminNootificationRepository notificationRepo;


	// @@@@@@@@@@@@@@@@@@@@@@@@@@--------------------Checking phone number and email and if not present then insert info and send success message
	@Override
	public ResponseEntity<String> signUpInfo(SignUp signup) {

		boolean phoneexist=signUpRepo.existsById(signup.getPhoneNumber());
		boolean emailexist=signUpRepo.existsByEmail(signup.getEmail());

		if(phoneexist==true){
			return ResponseEntity.ok().body("phone already exist");

		}
		else if(emailexist==true)
		{

			return  ResponseEntity.ok().body("email already exist");
		}
		else{
			try {			

				signUpRepo.save(signup);
				return  ResponseEntity.ok().body("success");
			}catch(Exception e) {
				return  ResponseEntity.internalServerError().body("internal server error");
			}
		}

	}

	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@----------------Login  check phone number and password if present return success  by using signup table
	@Override
	public String loginAuth(long parseLong, String pass) {



		boolean phoneexist=signUpRepo.existsById(parseLong);

		if(phoneexist==true) {	

			if(signUpRepo.findById(parseLong).getPassword().equals(pass) && signUpRepo.findById(parseLong).getUserType().equals("Admin")) {
				String ad="A";
				String se="0";//Single(Admin) ko value send garda JSON converter le if condition maa kaam nagareko le arko ko value 0 garera send gareko

				String firstname=signUpRepo.findById(parseLong).getFirstName();
				String lastname=signUpRepo.findById(parseLong).getLastName();
				String address=signUpRepo.findById(parseLong).getAddress();
				String email=signUpRepo.findById(parseLong).getEmail();

				//for session in android 
				//String return bhayekole  data lai JSON String maa convert garera send gareko volley le response maa yaslai garera JSON object maa convert garera individual data lai  fetch garne
				String jsonAdmin  = 	"{\"seller\":\""+se+"\",\"admin\":\""+ad+"\",\"firstname\":\""+firstname+"\",\"lastname\":\""+lastname+"\",\"address\":\""+address+"\",\"email\":\""+email+"\"}";

				return jsonAdmin;
			}
			else if(signUpRepo.findById(parseLong).getPassword().equals(pass) && signUpRepo.findById(parseLong).getUserType().equals("Seller")) {
				String se="S";
				String ad="0";//Single(Admin) ko value send garda JSON converter le if condition maa kaam nagareko le arko ko value 0 garera send gareko

				String firstname=signUpRepo.findById(parseLong).getFirstName();
				String lastname=signUpRepo.findById(parseLong).getLastName();
				String address=signUpRepo.findById(parseLong).getAddress();
				String email=signUpRepo.findById(parseLong).getEmail();

				//for session in android 
				//String return bhayekole  data lai JSON String maa convert garera send gareko volley le response maa yaslai garera JSON object maa convert garera individual data lai  fetch garne
				String jsonAdmin  = "{\"admin\":\""+ad+"\",\"seller\":\""+se+"\",\"firstname\":\""+firstname+"\",\"lastname\":\""+lastname+"\",\"address\":\""+address+"\",\"email\":\""+email+"\"}";

				return jsonAdmin;			}
			else {
				return "Wrong password";
			}

		}
		else {
			return "Phone number not match";

		}
	}


	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@----------------------------house and land upload by checking land /house id and image exist or not
	//house
	@Override
	public ResponseEntity<String> addHouse(HouseUpload houseproperty) {
		boolean houseexist=uploadHouseRepo.existsById(houseproperty.getHouseId());
		if(houseexist==true) {
			return  ResponseEntity.ok().body("house already uploaded");
		}
		else {
		try {
			String path="D:\\upload\\houseupload" + File.separator+houseproperty.getImageName(); //best way
			File directory=new File(path);
			if(directory.exists()) {//checking if given file name present in file server

				int index = houseproperty.getImageName().lastIndexOf(".");
				String  onlyImageName="";
				String onlyExtension="";
				if (index > 0) {
					onlyImageName = houseproperty.getImageName().substring(0, index);
					onlyExtension = houseproperty.getImageName().substring(index + 1);
				}


				int i=1;
				while(true) {//until satisfy following condition

					String newImageName=onlyImageName.toString()+"("+(i)+")"+"."+onlyExtension.toString();
					String newpath="D:\\upload\\houseupload" + File.separator+newImageName; //best way
					File newdirectory=new File(newpath);

					if(!newdirectory.exists()) {			

						FileOutputStream fos=new FileOutputStream("D:upload/houseupload/"+newImageName);
						byte [] barr =			Base64.getMimeDecoder().decode(houseproperty.getEncodedImage());
						fos.write(barr);
							uploadHouseRepo.save(houseproperty);
							fos.close();	

							return  ResponseEntity.ok().body("success");
						


					}
					i++;
				}
			}
			else {
				FileOutputStream fos=new FileOutputStream("D:upload/houseupload/"+houseproperty.getImageName());
				byte [] barr =			Base64.getMimeDecoder().decode(houseproperty.getEncodedImage());
				fos.write(barr);
						
					uploadHouseRepo.save(houseproperty);
					fos.close();

					return  ResponseEntity.ok().body("success");
				
			}
		}catch(Exception e) {
			return  ResponseEntity.internalServerError().body("internal server error");

		}
		}
		}
		

//land
	@Override
	public ResponseEntity<String> addLand(LandUpload landproperty) {
			
		boolean landexist=uploadLandRepo.existsById(landproperty.getLandId());
		if(landexist==true) {
			return  ResponseEntity.ok().body("land already uploaded");
		}
		else {
			
		try {
			String path="D:\\upload\\landupload" + File.separator+landproperty.getImageName(); //best way
			File directory=new File(path);
			if(directory.exists()) {//checking if given file name present in file server
 
				int index = landproperty.getImageName().lastIndexOf(".");
				String  onlyImageName="";
				String onlyExtension="";
				if (index > 0) {
					onlyImageName = landproperty.getImageName().substring(0, index);
					onlyExtension = landproperty.getImageName().substring(index + 1);
				}


				int i=1;
				while(true) {//until satisfy following condition

					String newImageName=onlyImageName.toString()+"("+(i)+")"+"."+onlyExtension.toString();
					String newpath="D:\\upload\\landupload" + File.separator+newImageName; //best way
					File newdirectory=new File(newpath);

					if(!newdirectory.exists()) {			

						FileOutputStream fos=new FileOutputStream("D:upload/landupload/"+newImageName);
						byte [] barr =			Base64.getMimeDecoder().decode(landproperty.getEncodedImage());
						fos.write(barr);
						uploadLandRepo.save(landproperty);
							fos.close();
							
							return  ResponseEntity.ok().body("success");
						
					}
					i++;
				}
			}
			else {
				FileOutputStream fos=new FileOutputStream("D:upload/landupload/"+landproperty.getImageName());
				byte [] barr =Base64.getMimeDecoder().decode(landproperty.getEncodedImage());
				fos.write(barr);
						
				uploadLandRepo.save(landproperty);
					fos.close();

					return  ResponseEntity.ok().body("success");
				
			}
		}catch(Exception e) {
			return  ResponseEntity.internalServerError().body("internal server error");

		}
		}
	}

	
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@----------------------------house and land Load/retrieve data from database to show in dashboard

	@Override
	public ResponseEntity<List<HouseUpload>> housesProperties() {
		
		return ResponseEntity.ok(uploadHouseRepo.findAll());
		
			}

	@Override
	public ResponseEntity<List<LandUpload>> landProperties() {
		return ResponseEntity.ok(uploadLandRepo.findAll());

	}

	
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@----------------------------house and land Load/retrieve data of single user from database to show in profile
//house
	@Override
	public ResponseEntity<List<HouseUpload>> singleUserHouseProperties(long parseLong) {
				
	 		return new ResponseEntity<List<HouseUpload>>(uploadHouseRepo.findByHouseOwnerPhone(parseLong),HttpStatus.OK);

	}

	//land
	@Override
	public ResponseEntity<List<LandUpload>> singleUserLandProperties(long parseLong) {
	
		return new ResponseEntity<List<LandUpload>>(uploadLandRepo.findByLandOwnerPhone(parseLong),HttpStatus.OK);

	}

	
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@----------------------------Total user count

	@Override
	public ResponseEntity<String> getUserCount() {
		String str1 = Long.toString(signUpRepo.count()).toString();

		//String s=signUpRepo.count();
		return ResponseEntity.ok().body(str1);
	}
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@----------------------------Total house count

	@Override
	public ResponseEntity<String> getCountHouse() {
		String str1 = Long.toString(uploadHouseRepo.count()).toString();

		//String s=signUpRepo.count();
		return ResponseEntity.ok().body(str1);
	}
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@----------------------------Total land count

	@Override
	public ResponseEntity<String> getCountLand() {
		String str1 = Long.toString(uploadLandRepo.count()).toString();

		//String s=signUpRepo.count();
		return ResponseEntity.ok().body(str1);
	}
	
	
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@----------------------------Notification added by admin

	
	@Override
	public  ResponseEntity<String> addNotification(AdminNotification newnotice) {
		try {			

			notificationRepo.save(newnotice);
			return  ResponseEntity.ok().body("success");
		}catch(Exception e) {
			return  ResponseEntity.internalServerError().body("internal server error");
	}
	}
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@----------------------------getting all notification added by admin

	@Override
	public ResponseEntity<List<AdminNotification>> getAllNotification() {
		return ResponseEntity.ok(notificationRepo.findAll());

	}
}
