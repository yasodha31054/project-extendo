package com.example.prj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prj.Jwt.JwtUtil;
import com.example.prj.Responsegenerator.ResponseGenerator;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/User")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllUserDetails(){
		try {
			List<User> response=userService.getAllUSer();
			if(response!=null) {
				return ResponseGenerator.successResponse("Data fetched successfully!..", response);
			}else {
				return ResponseGenerator.errorResponse("Data fetched failed");
			}
			
		}catch(Exception e) {
			return ResponseGenerator.errorResponse(e.getMessage());
		}
	}
	@PostMapping("/adduser")
	public ResponseEntity<?> addUserDetails(@Valid @RequestBody User user ){
		try {
			User response=userService.addUSer(user);
			if(response!=null) {
				return ResponseGenerator.successResponse("data added successfully!..", response);
			}else {
				return ResponseGenerator.errorResponse("data added failed");
			}
		}catch(Exception e) {
			return ResponseGenerator.errorResponse(e.getMessage());
		}
	}
	
	@GetMapping("/{userid}")
	public ResponseEntity<?> getUserById(@Valid @PathVariable int userid){
		try {
			Optional<User> response=userService.getUser(userid);
			if(response.isPresent()) {
				return ResponseGenerator.successResponse("data fetched succesfully!..", response);
			}else {
				return ResponseGenerator.errorResponse("data can;t be fetched");
			}
		}catch(Exception e) {
			return ResponseGenerator.errorResponse(e.getMessage());
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateUserDetails(@RequestBody User userid){
		try {
			User response=userService.updateUser(userid);
			if(response!=null) {
				return ResponseGenerator.successResponse("updated succesfully!..", response);
			}else {
				return ResponseGenerator.errorResponse("updated failed");
			}
		}catch(Exception e) {
			return ResponseGenerator.errorResponse(e.getMessage());
		}
	}
	
	@DeleteMapping("/{userid}")
	public ResponseEntity<?> DeteleUserDEtails(@PathVariable int userid){
		try {
			boolean response=userService.deleteUser(userid);
			if(response!=false) {
				return ResponseGenerator.successResponse("deleted sucessfully", response);
			}else {
				return ResponseGenerator.errorResponse("deleted failed");
			}
			
		}catch(Exception e) {
			return ResponseGenerator.errorResponse(e.getMessage());
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginDetails(@RequestBody User request){
		try {
			User response=userService.getUserDetails(request.getUserName(),request.getPassword());
			if(response!=null) {
				String token=jwtUtil.generateToken(request.getUserName());
				Map<String,Object> result=new HashMap<String, Object>();
				result.put("token", token);
				result.put("user",response);
			return ResponseGenerator.successResponse("login successfull", result);
			}else {
				return ResponseGenerator.errorResponse("login failed");
			}
		}catch(Exception e)
		{
			
			return ResponseGenerator.errorResponse(e.getMessage());
		}
	}
}
