package com.bimoku.dataplatform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bimoku.dataplatform.entity.dto.PressDTO;

@Entity
@Table(name = "Press")
public class Press {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "PHONE")
	private String phone;
	
	public Press() {}
	
	public Press(PressDTO dto) {
		this.name = dto.getName();
		this.address = dto.getAddress();
		this.phone = dto.getPhone();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
