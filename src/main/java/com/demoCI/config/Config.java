/**
 * 
 */
package com.demoCI.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Med
 * 19 août 2017
 */
@Configuration
public class Config {

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
}
