package com.example.prj;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	public AddressRepository addressRepository;

	@Autowired
	public QualificationRepository qualificationRepository;

	public Student saveDepartment(Student student) {
		return studentRepository.save(student);
	}

	public List<Student> fetchStudentList() {
		return studentRepository.findAll();
	}

	public Optional<Student> getId(int id) {
		return studentRepository.findById(id);
	}

	public Student addstudent(Student stud) {
		return studentRepository.save(stud);
	}

	public Student updateStudent(Student data) {
		Optional<Student> existingStudent = studentRepository.findById(data.getStudentId());
		if (existingStudent.isPresent()) {
			existingStudent.get().setStudentName(data.getStudentName());
			existingStudent.get().setEmail(data.getEmail());
			existingStudent.get().setDob(data.getDob());
			existingStudent.get().setPhoneNo(data.getPhoneNo());
			existingStudent.get().setAge(data.getAge());
			return studentRepository.saveAndFlush(existingStudent.get());
		}
		return null;

	}

	public boolean deleteStudentById(int id) {
		if (id < 0) {
			throw new RuntimeException("Invalid student ID: must be positive.");
		}
		if (studentRepository.existsById(id)) {
			studentRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	public StudentDTO getStudentDetails(int id) {
		Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
		System.out.println("Student fetched = " + student);

		StudentDTO dto = new StudentDTO();
		dto.setStudentId(student.getStudentId());
		dto.setName(student.getStudentName());
		dto.setEmail(student.getEmail());
		dto.setAge(student.getAge());
		dto.setAddress(addressRepository.findByStudentId(dto.getStudentId()));
		dto.setQualification(qualificationRepository.findByStudentId(dto.getStudentId()));
		return dto;
	}

	public List<AllDTO> getFullStudentDetails2(int id) {
		return studentRepository.getStudentDetailsById(id);
	}
	
	public List<Student> getFullDetails(int pincode) {
		return studentRepository.getDetails(pincode);
		
	}

}
