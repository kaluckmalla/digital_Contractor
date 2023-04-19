package com.springrest.springrest.dconrepository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springrest.springrest.entities.SignUp;

public interface SignUpRepository  extends JpaRepository<SignUp,Long>{
	SignUp findById(long phone);//for finding single row by phonenumber which is primary key
	boolean existsByEmail(String email);//for checking  email  exist or not because email is unique so act as primary key
}
