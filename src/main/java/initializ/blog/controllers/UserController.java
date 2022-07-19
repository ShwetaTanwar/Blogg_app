package initializ.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import initializ.blog.payloads.ApiResponse;
import initializ.blog.payloads.UserDto;
import initializ.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
	@Autowired
	private UserService userService;

	@PostMapping("/addUser")
	public ResponseEntity <UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto createUserDto= this.userService.createUser(userDto);
	return new ResponseEntity<>(createUserDto , HttpStatus.CREATED);
	}
	
	@PutMapping("/{userId}")
public ResponseEntity<UserDto>updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId){
		UserDto updateUser=this.userService.updateUser(userDto, userId);
		return ResponseEntity .ok(updateUser);
	}
	// 2) for ADMIN setting authorization   For 1) see security config
	@PreAuthorize("hasRole('Admin')")
	@DeleteMapping("/{userId}")
	public ResponseEntity <ApiResponse> deleteUser (@PathVariable ("userId") Integer uid){
		this.userService.deleteUser(uid);
		return new ResponseEntity <ApiResponse>(new ApiResponse("User Deleted Successful", false),HttpStatus.OK);
	}
	//@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/")
	public ResponseEntity<List<UserDto>>getAllUser(){
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto>getSingleUser(@PathVariable Integer userId){
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}
	
	
}
