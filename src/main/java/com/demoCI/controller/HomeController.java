/**
 * 
 */
package com.demoCI.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Med
 * 7 août 2017
 */
@Controller
@RequestMapping("/")
public class HomeController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home(ModelAndView mv){
		mv.addObject("helloMsg", "Home Sweet Home");
		mv.setViewName("home");
		
		return mv;
		
	}
}
