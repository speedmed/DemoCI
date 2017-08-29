package com.demoCI;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.demoCI.controller.dto.UserRegisterDTO;
import com.demoCI.model.Role;
import com.demoCI.model.User;
import com.demoCI.service.RoleService;
import com.demoCI.service.UserService;

@SpringBootApplication
public class DemoCiApplication implements CommandLineRunner{

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private ModelMapper modelMapper;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoCiApplication.class, args);
	}
	
	@Override
	@Transactional
    public void run(String... args) throws Exception {
		
		UserRegisterDTO userDto = new UserRegisterDTO();
		userDto.setUsername("username1");
		userDto.setEmail("email1");
		userDto.setPassword("pass");
		
		UserRegisterDTO userDto2 = new UserRegisterDTO("username2", "email2", null, "pass2");
		
		Role role = roleService.create(new Role("USER"));
		User u1 = modelMapper.map(userDto, User.class);
		User u2 = modelMapper.map(userDto2, User.class);
		u1.addRole(role);
		u2.addRole(role);
		userService.create(u1);
		userService.create(u2);

    }
}
