package com.valid.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class User {
	@Id
	@JsonProperty("user_id")
	@JsonAlias("id")
	@NotBlank(message="id can't be empty")
	@Pattern(regexp="^(?!.*\\s)(?=.*[a-zA-Z])(?=.*[0-9]).+$"
			,message="id format is not valid")
	@Length(min=6, max=15, message="id length must in {min} to {max}")
	private String id;
	
	@JsonProperty("user_name")
	@JsonAlias("name")
	@NotBlank(message="name can't be empty")
	private String name;
	
	@JsonIgnore
	private String sex;
	
	@Email(message="Email is invalid")
	private String email;
	
	@JsonFormat(pattern="yyyy/MM/dd",timezone="GMT+8")
	@Past(message="birth should be earlier than today")
	private Date   birth;
	
	@JsonIgnore
	private String phone;
	
	
	public User(){
		
	}
	
	public User(String id, String name, String sex, String email, Date birth, String phone) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.email = email;
		this.birth = birth;
		this.phone = phone;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
