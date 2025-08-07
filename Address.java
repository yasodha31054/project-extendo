package com.example.prj;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressId;

	@NotBlank(message = "Street name is mandatory")
	@Size(min = 2, max = 200, message = "street length must between 2 to 200")
	@Column(name = "street_name")
	private String streetName;

	@Column(name = "city")
	@NotBlank(message = "City is mandatory")
	@Size(min = 2, max = 200)
	private String city;

	@Column(name = "pincode")
	private int pincode;


//	@JoinColumn(name = "student_id") 
//	@JsonBackReference
	
	@Column(name = "student_id")
	private int studentId;

}
