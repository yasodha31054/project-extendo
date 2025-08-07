package com.example.prj;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.prj.Responsegenerator.ResponseGenerator;

import jakarta.validation.Valid;

@RestController
public class QualificationController {

	@Autowired
	public QualificationService qualificationService;
	
	@GetMapping("/Qualification")
	public ResponseEntity<?> QualificationDetails(){
		try {
		List<Qualification> response=qualificationService.getQualificationDetails();
		if(response!=null) {
		return  ResponseGenerator.successResponse("data fetched succesfully!..", response);
		}else {
			return ResponseGenerator.errorResponse("data fetched failed");}
	}catch(Exception e)
	{
		return ResponseGenerator.errorResponse(e.getMessage());
	}
	}
	
	@PostMapping("/Qualification")
	public ResponseEntity<?> addQualificationDetails(@Valid @RequestBody Qualification qual){
		try {
			Qualification response=qualificationService.getQualification(qual);
			if(response!=null) {
				return ResponseGenerator.successResponse("Qualification added succesfully!..", response);
			}else {
				return ResponseGenerator.errorResponse("Qualification added failed");
			}
			
		}catch(Exception e) {
			return ResponseGenerator.errorResponse(e.getMessage());
		}
	}
	@GetMapping("/Qualification/{id}")
	public ResponseEntity<?> QualificationDetails(@PathVariable int id){
		try {
			Optional<Qualification> response=qualificationService.getQualificationById(id);
				if(response!=null) {
					return ResponseGenerator.successResponse("fetched succesfullt!..", response);
				}else {
					return ResponseGenerator.errorResponse("Can't be fetched");
				}
			
		}catch(Exception e) {
			return ResponseGenerator.errorResponse(e.getMessage());
		}
	}
	
	@PutMapping("/Qualification")
	public ResponseEntity<?> updateQualificationDetails(@Valid @RequestBody Qualification qual) {
	    try {
	        Qualification response = qualificationService.updateQualification(qual);
	        if (response != null) {
	            return ResponseGenerator.successResponse("Updated successfully!..", response);
	        } else {
	            return ResponseGenerator.errorResponse("Update failed");
	        }
	    } catch (Exception e) {
	        return ResponseGenerator.errorResponse(e.getMessage());
	    }
	}
	
	@DeleteMapping("/Qualification/{id}")
	public ResponseEntity<?> deleteQualificationDetails(@PathVariable int id){
		try {
			boolean response=qualificationService.deleteQualification(id);
			if(response!=false) {
				return ResponseGenerator.successResponse("Deleted succesfully", response);
			}
			return ResponseGenerator.errorResponse("deleteed failed") ;
			}catch(Exception e)
			{
				return ResponseGenerator.errorResponse(e.getMessage());
			}
}
}

	

	
	
	
