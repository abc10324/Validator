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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.valid.model.util.NotSpace;

import lombok.Data;

@Entity
@Data
public class User {
	@Id
	@JsonProperty("user_id")
	@JsonAlias("id")
	@NotBlank
	@Pattern(regexp="^(?=.*[a-zA-Z])(?=.*[0-9]).+$"
			,message="invalid format")
	@Length(min=6, max=15)
	@NotSpace
	private String id;
	
	@JsonProperty("user_name")
	@JsonAlias("name")
	@NotBlank
	@NotSpace
	private String name;
	
	@JsonProperty(access=Access.WRITE_ONLY)
	@NotSpace
	private String sex;
	
	@Email
	private String email;
	
	@JsonFormat(pattern="yyyy/MM/dd",timezone="GMT+8")
	@Past
	private Date   birth;
	
	@JsonProperty(access=Access.WRITE_ONLY)
	@NotSpace
	private String phone;
	
}
