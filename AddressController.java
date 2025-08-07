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
public class AddressController {
@Autowired
public AddressService addressSerice;

@GetMapping("/address")
public ResponseEntity<?> getAddressDetails(){
	try {
	List<Address> response= addressSerice.AddressDetails();
	if(response!=null) {
		return ResponseGenerator.successResponse("address fetched sucessfully!..",response);
	}
	return ResponseGenerator.errorResponse("error");
	}catch(Exception e)
	{
		return ResponseGenerator.errorResponse(e.getMessage());
	}
}

@GetMapping("/address/{id}")
public ResponseEntity<?> getAddressById(@Valid @PathVariable int id){
	try {
	Optional<Address> response=addressSerice.getId(id);
	if(response.isPresent()) {
		return ResponseGenerator.successResponse("data fetched successfully!..", response);
	}
	return ResponseGenerator.errorResponse("error-can't be fetched");
	}catch(Exception e)
	{
		return ResponseGenerator.errorResponse(e.getMessage());
	}
	
}

@PostMapping("/address")
public ResponseEntity<?> AddaddressDetails(@Valid @RequestBody Address adr){
	try {
	Address response=addressSerice.addAddress(adr);
	if(response!=null) {
		return ResponseGenerator.successResponse("Added successfully", response);
	}
	return ResponseGenerator.errorResponse("error - can't be add data");
	}catch(Exception e) {
		return ResponseGenerator.errorResponse(e.getMessage());
	}
	
}

@DeleteMapping("/address/{id}")
public ResponseEntity<?> deleteAddressDetails(@PathVariable int id){
	try {
	boolean response=addressSerice.deleteAddress(id);
	if(response!=false) {
		return ResponseGenerator.successResponse("Deleted succesfully", response);
	}
	return ResponseGenerator.errorResponse("deleteed failed") ;
	}catch(Exception e)
	{
		return ResponseGenerator.errorResponse(e.getMessage());
	}
	
}

@PutMapping("/address")
public ResponseEntity<?> updateAddressDetails(@RequestBody Address adr){
	try {
	Address response=addressSerice.updateAddress(adr);
	if(response!=null) {
		return ResponseGenerator.successResponse("Updated successfully!..", response);
	}
	return ResponseGenerator.errorResponse("updated failed");
	}catch(Exception e)
	{
		return ResponseGenerator.errorResponse(e.getMessage());
	}
	
}
}

