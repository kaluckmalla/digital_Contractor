package com.springrest.springrest.dconrepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springrest.springrest.entities.AdminNotification;
import com.springrest.springrest.entities.SignUp;

public interface AdminNootificationRepository  extends JpaRepository<AdminNotification,Long>{

}
