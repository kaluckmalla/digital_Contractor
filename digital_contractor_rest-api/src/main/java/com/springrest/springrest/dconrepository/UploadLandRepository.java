package com.springrest.springrest.dconrepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springrest.springrest.entities.HouseUpload;
import com.springrest.springrest.entities.LandUpload;

public interface UploadLandRepository  extends JpaRepository<LandUpload,String> {

	List<LandUpload> findByLandOwnerPhone(long parseLong);//finding list of land property of single user


}
