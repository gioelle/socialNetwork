package com.claim.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.claim.entity.Person;
import com.claim.entity.Post;
import com.claim.service.MailService;
import com.claim.service.PersonService;
import com.claim.service.PostService;

@Controller
public class UserController {

	@RequestMapping("/") //use spring to create a servlet for your index page --- if user doesn't enter a path this is the default
	public String index() {
		return "index"; //tell spring to find and display the index page
	}

	@Autowired
	private MailService emailSender;
	private final static String newUserMessage = "We're so happy you've joined our network!";
	private final static String newUserSubject = "Welcome to the Social Network!";
	@Autowired
	private PersonService personService;
	@Autowired
	private PostService postService;
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public ModelAndView signup(Model model) {	
		return new ModelAndView("signup", "newUser", new Person());
	}
	
	//Do error handling try catch here to avoid problems with internet on demo day
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public ModelAndView handleSignup(Model model, @ModelAttribute("newUser") Person newPerson) {
		this.personService.save(newPerson);
		emailSender.sendMail(newPerson.getEmail(), newUserSubject, newUserMessage);
		System.out.println(newPerson.getEmail() + newUserSubject);	
		return login(model);
	}
	//When the user tries to view the login page this servlet will handle the request 
	//and add a spring Model Attribute object to map to the login form called "userLogin", which is a person object
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView login(Model model) {
		return new ModelAndView("login", "userLogin", new Person());
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String handleLogin(Model model, @ModelAttribute("userLogin") Person loginPerson, HttpSession session) {
		Person p = this.personService.login(loginPerson.getEmail(), loginPerson.getPassword());
		if(p == null) {
			model.addAttribute("loginError", "username or password invalid");
			return "login";
		} else {
			session.setAttribute("loggedInUser", p);
			addUserPost(model, p.getEmail());
			return "home";
		}
		
	}

	@RequestMapping(value="/post", method=RequestMethod.POST)
	public String post(Model model, @ModelAttribute("post")Post post) {
		postService.savePost(post);
		addUserPost(model, post.getEmail());
		//List <Post> posts = postService.findMyPost(post.getEmail());
		return "home";
	}
	
	private void addUserPost(Model model, String email) {
		model.addAttribute("post", new Post());
		model.addAttribute("posts", postService.findMyPost(email));
	}

	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String uploadFileHandler(@RequestParam("file") MultipartFile file, HttpSession session, Model model) {
		Person p = (Person) session.getAttribute("loggedInUser");
		try {
			if(!file.isEmpty()) {
				String fileName = file.getOriginalFilename();
				String basePath = "C:\\Users\\Joelle\\Workspace\\socialNetwork\\src\\main\\resources\\static\\img";
				String uploadPath = basePath+"\\"+p.getEmail()+"\\"+fileName;
				String profilePath = "/img/" + p.getEmail() + "/" + fileName;
				File fileToUpload = new File(uploadPath);
				FileUtils.writeByteArrayToFile(fileToUpload, file.getBytes());
				p.setProfilePic(profilePath);
				personService.save(p);
			}
		}catch(Exception e) {
			
		}
		this.addUserPost(model, p.getEmail());
		return "home";
	}
}
