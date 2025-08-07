package com.example.prj;


import java.util.List;
import java.util.Optional;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QualificationService {
	@Autowired
	public QualificationRepository qualificationRepository;

	@Autowired
	@Lazy
	private StudentRepository studentRepository;

	public List<Qualification> getQualificationDetails() {
		return qualificationRepository.findAll();
	}

	public Qualification getQualification(Qualification qual) {
		return qualificationRepository.save(qual);
		
	}
	
	public Optional<Qualification> getQualificationById(int id){
		return qualificationRepository.findById(id);
	}
	public Qualification updateQualification(Qualification qual) {
		Optional<Qualification> existingQualification=qualificationRepository.findById(qual.getQualificationId());
		if(existingQualification.isPresent()){
			existingQualification.get().setCourseName(qual.getCourseName());
			existingQualification.get().setPercentage(qual.getPercentage());
			existingQualification.get().setPassOut(qual.getPassOut());
			existingQualification.get().setUniversity(qual.getUniversity());
			existingQualification.get().setStudentId(qual.getStudentId());
			return qualificationRepository.saveAndFlush(existingQualification.get());
			}
		return null;
		
	}
	public boolean deleteQualification(int id){
		Optional<Qualification> qualification=qualificationRepository.findById(id);
		if(qualification.isPresent()) {
			studentRepository.deleteById(id);
			return true;
		}
		return false;	
	}
}


