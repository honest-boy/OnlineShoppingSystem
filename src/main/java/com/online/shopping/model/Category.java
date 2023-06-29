package com.online.shopping.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Entity
@Table
@Data
public class Category implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 891201756451813846L;

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	@Column(name = "category_id")
	private int id;

	@NotBlank()
	@Length(min = 2, max = 25)
	@Pattern(regexp = ".*([a-zA-Z]{2}$)")
	@Column(nullable = false, unique = true)
	private String name;

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

	public Category(String name) {
		super();
		this.name = name;
	}

	public Category(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}

}
