package com.claim.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.claim.entity.Person;

@Controller
public class UserController {

	@RequestMapping("/") //use spring to create a servlet for your index page --- if user doesn't enter a path this is the default
	public String index() {
		return "index"; //tell spring to find and display the index page
	}

	//When the user tries to view the login page this servlet will handle the request 
	//and add a spring Model Attribute object to map to the login form called "userLogin", which is a person object
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView login(Model model) {
		return new ModelAndView("login", "userLogin", new Person());
	}

}
