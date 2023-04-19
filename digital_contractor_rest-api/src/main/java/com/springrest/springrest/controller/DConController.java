package com.springrest.springrest.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springrest.springrest.entities.AdminNotification;
import com.springrest.springrest.entities.HouseUpload;
import com.springrest.springrest.entities.LandUpload;
import com.springrest.springrest.entities.SignUp;
import com.springrest.springrest.entities.Upload;
import com.springrest.springrest.services.DConService;

import antlr.StringUtils;

@RestController
public class DConController {
	@Autowired
	private DConService dconService;


	///@@@@@@@@@@@@@@@@@@@@@@@@@@@@------------Sign up
	//for single add

	@PostMapping(path="/signup",consumes="application/json")

	public ResponseEntity<String> signUpInfo(@RequestBody SignUp signup)
	{

		return this.dconService.signUpInfo(signup);

	}


	///@@@@@@@@@@@@@@@@@@@@@@@@@@@@------------login

	@PostMapping("/login")

	public String loginUser(@RequestParam("phonenumber") String phone,@RequestParam("password") String pass)
	{


		return this.dconService.loginAuth(Long.parseLong(phone),pass);					
	}	
	
	///@@@@@@@@@@@@@@@@@@@@@@@@@@@@------------house upload

		@PostMapping("/houseupload")

		public ResponseEntity<String> uploadHouseProperty(@RequestBody HouseUpload houseproperty)
		{
			return this.dconService.addHouse(houseproperty);					

					
		}	
		
		///@@@@@@@@@@@@@@@@@@@@@@@@@@@@------------land upload

		
		@PostMapping("/landupload")

		public ResponseEntity<String> uploadLandProperty(@RequestBody LandUpload landproperty)
		{
			return this.dconService.addLand(landproperty);					

					
		}	
		
		
	
				///@@@@@@@@@@@@@@@@@@@@@@@@@@@@------------getting Houses and land list from database
				//for houses

				@GetMapping(path="/gethouseproperty")

				public ResponseEntity<List<HouseUpload>> houseData()
				{
					System.out.println("hhh:"+this.dconService.housesProperties());

					return this.dconService.housesProperties();

				}
				
				//for land

				@GetMapping(path="/getlandproperty")

				public ResponseEntity<List<LandUpload>> landData()
				{

					return this.dconService.landProperties();

				}
				///@@@@@@@@@@@@@@@@@@@@@@@@@@@@------------getting Houses and land  list of SINGLE user from database by using session phone number
				//for houses

				@GetMapping("/getsingleuserhouseproperty/{sessionPhoneNumber}")

				public ResponseEntity<List<HouseUpload>> singleUserHouseData(@PathVariable String sessionPhoneNumber)
				{

					return this.dconService.singleUserHouseProperties(Long.parseLong(sessionPhoneNumber));

				}
				
				//for land

				@GetMapping("/getsingleuserlandproperty/{sessionPhoneNumber}")

				public ResponseEntity<List<LandUpload>> singleUserLandData(@PathVariable String sessionPhoneNumber)
				{

					return this.dconService.singleUserLandProperties(Long.parseLong(sessionPhoneNumber));

				}
				
				///@@@@@@@@@@@@@@@@@@@@@@@@@@@@-----------Total user count

				@GetMapping("/usercount")

				public ResponseEntity<String> userCount()
				{
					return this.dconService.getUserCount();					

							
				}
				///@@@@@@@@@@@@@@@@@@@@@@@@@@@@-----------Total house count

				@GetMapping("/housecount")

				public ResponseEntity<String> houseCount()
				{
					return this.dconService.getCountHouse();					

							
				}
				///@@@@@@@@@@@@@@@@@@@@@@@@@@@@-----------Total land count

				@GetMapping("/landcount")

				public ResponseEntity<String> landCount()
				{
					return this.dconService.getCountLand();					

							
				}
				
				//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@-----Admin add notification
				@PostMapping(path="/notification",consumes="application/json")

				public ResponseEntity<String> admnNoti(@RequestBody AdminNotification newnotice)
				{
					return this.dconService.addNotification(newnotice);					
				}	
				
				//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@-----getting all notification

				@GetMapping(path="/notification")

				public ResponseEntity<List<AdminNotification>> adminNotification()
				{

					return this.dconService.getAllNotification();

				}
				
				
//				//Retrive image
//				@GetMapping(value = "/getImg")
//				public @ResponseBody byte[]  getImage() throws IOException {
//				    InputStream in = getClass()
//				      .getResourceAsStream("D:\\upload\\landupload\\20220912_143914.jpg");
//				    System.out.println("img "+in);
//				    return IOUtils.toByteArray(in);
//				}
				
}
