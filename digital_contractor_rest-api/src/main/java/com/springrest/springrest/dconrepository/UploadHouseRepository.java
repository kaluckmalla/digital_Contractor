package com.springrest.springrest.dconrepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springrest.springrest.entities.HouseUpload;
import com.springrest.springrest.entities.LandUpload;
import com.springrest.springrest.entities.SignUp;

public interface UploadHouseRepository  extends JpaRepository<HouseUpload,String> {


	List<HouseUpload> findByHouseOwnerPhone(long parseLong);//retrieving data using house owner phone

	
}
