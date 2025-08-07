package com.example.prj;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

//import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Qualification {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int QualificationId;
	
	@NotBlank(message="coursename is mandatory")
	@Column(name="course_name")
	@Size(min=2,max=100)
	private String courseName;
	
	@DecimalMin(value="0.0")
	@DecimalMax(value="100.0")
	@Column(name="percentage")
	private Float percentage;
	
	
	@Column(name="pass_out")
	@Min(value = 2000, message = "Year must be after 2000")
	@Max(value = 2100, message = "Year must be before 2100")
	private int passOut;
	
	@NotBlank(message="university field is mandatory")
	@Column(name="university")
	private String university;

	

//	@JoinColumn(name = "student_id")
//	@JsonBackReference
	@Column(name = "student_id")
	private int studentId;
	
}
