/**
 * 
 */
package com.demoCI.controller;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demoCI.controller.dto.UserDTO;
import com.demoCI.controller.dto.UserRegisterDTO;
import com.demoCI.model.User;
import com.demoCI.service.PageOf;
import com.demoCI.service.UserService;
import com.google.common.reflect.TypeToken;

/**
 * @author Med
 * 25 août 2017
 */
@RestController
@RequestMapping("/api")
public class UserController {
	
	private UserService userService;
	private ModelMapper modelMapper;
	@Autowired
	public UserController(UserService userService, ModelMapper modelMapper){
		this.userService = userService;
		this.modelMapper = modelMapper;
	}
	
	@RequestMapping(path="/users/", method=RequestMethod.POST)
	public ResponseEntity<UserDTO> register(@RequestBody UserRegisterDTO uRegisterDto){
		
		User u = userService.create(modelMapper.map(uRegisterDto, User.class));
		UserDTO userDto = modelMapper.map(u, UserDTO.class);
		return ResponseEntity.ok(userDto);
	}

	@RequestMapping(path="/users/{userId}", method=RequestMethod.GET)
	public ResponseEntity<UserDTO> findUserById(@PathVariable("userId") Long userId){
		User user = userService.read(userId);
		UserDTO userDto = modelMapper.map(user, UserDTO.class);
		return ResponseEntity.ok(userDto);
	}
	
	@RequestMapping(path="/users", params={"username"}, method=RequestMethod.GET)
	public ResponseEntity<UserDTO> findByUsername(@RequestParam("username") String username){
		User user = userService.findByUsername(username);
		UserDTO userDto = modelMapper.map(user, UserDTO.class);
		return ResponseEntity.ok(userDto);
	}
	
	@RequestMapping(path="/users", params = {"page"}, method=RequestMethod.GET)
	public ResponseEntity<PageOf<UserDTO>> findUserPage(@RequestParam("page") int page){
		PageOf<User> userPage = userService.findByPage(page, 10);
		Type typeList = new TypeToken<List<UserDTO>>() {}.getType();
		List<UserDTO> listUsers = modelMapper.map(userPage.getContents(), typeList);
		PageOf<UserDTO> userDtoPage = new PageOf<>(listUsers, page, userPage.getSize(), userPage.getTotalValues(), userPage.getTotalPages());
		return ResponseEntity.ok(userDtoPage);
	}
	
	@RequestMapping(path="/users/", method=RequestMethod.PUT)
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserRegisterDTO uRegisterDto){
		User user = userService.update(modelMapper.map(uRegisterDto, User.class));
		UserDTO userDto = modelMapper.map(user, UserDTO.class);
		return ResponseEntity.ok(userDto);
	}
	
	@RequestMapping(path="/users/{userId}", method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteUser(@PathVariable("userId") Long userId){
		userService.delete(userId);
		return ResponseEntity.ok("User deleted");
	}
}
