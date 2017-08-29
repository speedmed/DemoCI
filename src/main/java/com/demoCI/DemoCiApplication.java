package com.demoCI;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class DemoCiApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(DemoCiApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}

/*	@Override
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

    }*/
}
