package com.springrest.springrest.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "upload")
public 
class Upload {
	
		public Upload() {
		super();
		// TODO Auto-generated constructor stub
	}
		public Upload(String name, String imagename) {
		super();
		this.name = name;
		this.imagename = imagename;
	}
		@Override
	public String toString() {
		return "Upload [name=" + name + ", imagename=" + imagename + ", getName()=" + getName() + ", getImagename()="
				+ getImagename() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
		public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImagename() {
		return imagename;
	}
	public void setImagename(String imagename) {
		this.imagename = imagename;
	}
		@Id
	private String name;
	private String imagename;
	
}
