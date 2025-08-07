package com.example.prj;


import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//
//import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "student")
public class Student {

	@Valid
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;

    @Column(name="student_name")
    @NotBlank(message="StudentName is mandatory")
    @Size(min=2,max=100,message="name must be between 2 to 100")
    private String studentName;
    
    @NotBlank(message="email is mandatory")
    @Email(message = "Invalid email format")
    private String email;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
    
    @NotNull(message="phoneno is mandatory")
    private Long phoneNo;
    
    @NotNull(message="age is mandatory")
    @Min(value=1,message="age greater then 0")
    private int age;
    
    
    
//    public Student(int studentId, String studentName, String email, Long phoneNo, int age) {
//        this.studentId = studentId;
//        this.studentName = studentName;
//        this.email = email;
//        this.phoneNo = phoneNo;
//        this.age = age;
//    }
	
	
}