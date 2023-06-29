package com.online.shopping.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

@Entity
@Table
public class Address implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 4768406255534312243L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty
	@Length(min = 2, max = 25)
	@Pattern(regexp = ".*([a-zA-Z]{2}$)")
	@Column(nullable = false)
	private String firstName;

	@NotEmpty
	@Length(min = 2, max = 25)
	@Pattern(regexp = ".*([a-zA-Z]{2}$)")
	@Column(nullable = false)
	private String lastName;

	private String country;

	@NotEmpty
	@Length(min = 2, max = 150)
	@Pattern(regexp = ".*([a-zA-Z0-9]{2}$)")
	@Column(nullable = false)
	private String address1;

	@NotEmpty
	@Length(min = 0,max = 150)
	@Pattern(regexp = ".*([a-zA-Z0-9]{0}$)")
	@Column(nullable = false)
	private String address2;

	private int pincode;

	private String city;

	private long phone;

	@Column(nullable = false)
	@NotEmpty
	@Email
	private String email;

	@ManyToOne
	@JoinColumn(name = "users_id")
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address(int id, String firstName, String lastName, String country, String address1, String address2, int pincode,
			String city, long phone, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
		this.address1 = address1;
		this.address2 = address2;
		this.pincode = pincode;
		this.city = city;
		this.phone = phone;
		this.email = email;
	}

	public Address(String firstName, String lastName, String country, String address1, String address2, int pincode,
			String city, long phone, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
		this.address1 = address1;
		this.address2 = address2;
		this.pincode = pincode;
		this.city = city;
		this.phone = phone;
		this.email = email;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

}
