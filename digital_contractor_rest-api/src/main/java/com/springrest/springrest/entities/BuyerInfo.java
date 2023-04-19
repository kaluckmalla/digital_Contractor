package com.springrest.springrest.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "buyer_info")

public class BuyerInfo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private long buyerId;
private String phone;
private String name;
private String address;
}
