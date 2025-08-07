package com.example.prj;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

//import com.example.prj.Jwt.TransactionContext;
import com.example.prj.Responsegenerator.ResponseGenerator;

import jakarta.validation.Valid;

@RestController
//@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping("/students")
	public ResponseEntity<?> getAllStudent() {
		try {
			List<Student> response = studentService.fetchStudentList();
			if (response != null) {
				return ResponseGenerator.successResponse("Data fetched successfully..!", response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseGenerator.errorResponse("Data can't be fetched!...");

	}

	@GetMapping("/students/{id}")
	public ResponseEntity<?> getStudentId(@PathVariable int id) {

		Optional<Student> response = studentService.getId(id);
		if (response.isPresent()) {
			return ResponseGenerator.successResponse("Data fetched successfully..!", response);
		} else {
			return ResponseGenerator.errorResponse("Student not found!...");
		}
	}

	@PostMapping("/students")
	public ResponseEntity<?> addstudent(@Valid @RequestBody Student student) {
		try {
			Student response = studentService.addstudent(student);
			if (response != null) {
				return ResponseGenerator.successResponse("Data added sucessfully!...", response);
			} else
				return ResponseGenerator.errorResponse("data not added");
		} catch (Exception e) {
			return ResponseGenerator.errorResponse(e.getMessage());

		}
	}

	@PutMapping("/students")
	public ResponseEntity<?> updateStudent(@Valid @RequestBody Student student) {
		try {
			Student response = studentService.updateStudent(student);
			if (response != null) {
				return ResponseGenerator.successResponse("Data updated successfully..!", response);

			} else
				return ResponseGenerator.errorResponse("Student not found");
		} catch (Exception e) {
			return ResponseGenerator.errorResponse(e.getMessage());
		}
	}

	@DeleteMapping("/students/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable int id) {
		try {
			boolean response = studentService.deleteStudentById(id);
			if (response != false) {
				return ResponseGenerator.successResponse("Data deleted successfully..!", response);
			} else
				return ResponseGenerator.errorResponse("data not in the table");
		} catch (Exception e) {
			return ResponseGenerator.errorResponse(e.getMessage());
		}
	}

	@GetMapping("/student/{id}")
	public ResponseEntity<?> getFullStudentDetails(@PathVariable int id) {
		try {
			StudentDTO response = studentService.getStudentDetails(id);
			return ResponseGenerator.successResponse("Data fethed successfully!..", response);
		} catch (Exception e) {
			return ResponseGenerator.errorResponse(e.getMessage());
		}
	}

	@GetMapping("/stud/{id}")
	public ResponseEntity<?> getFullStudentDetails1(@PathVariable int id) {
		try {
			List<AllDTO> response = studentService.getFullStudentDetails2(id);
			return ResponseGenerator.successResponse("Data fethed successfully!..", response);
		} catch (Exception e) {
			return ResponseGenerator.errorResponse(e.getMessage());
		}
	}

	@GetMapping("/studentByPincode/{pincode}")
	public ResponseEntity<?> getFullDetails(@PathVariable int pincode) {
		try {
			List<Student> response = studentService.getFullDetails(pincode);
			return ResponseGenerator.successResponse("Data fethed successfully!..", response);
		} catch (Exception e) {
			return ResponseGenerator.errorResponse(e.getMessage());
		}
	}

}
