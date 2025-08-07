package com.example.prj;

import java.util.List;

import lombok.Data;

@Data
public class StudentDTO {
	private int studentId;
	private String name;
	private int age;
	private String email;
	private List<Address> address;
	private List<Qualification> qualification;
}
