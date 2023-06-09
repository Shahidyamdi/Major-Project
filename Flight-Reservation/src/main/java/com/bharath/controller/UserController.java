package com.bharath.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bharath.Repo.UserRepository;
import com.bharath.entities.User;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/showReg")
	public String showRegister() {
		return "login/registerUser";
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String Register(@ModelAttribute("user") User user) {
		userRepository.save(user);

		return "login/login";

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("email") String email, @RequestParam("password") String password,
			ModelMap modelmap) {
		User user = userRepository.findByEmail(email);

		if (user.getPassword().equals(password)) {

			return "login/findFlights";

		} else {
			modelmap.addAttribute("msg", "Invaild email and password...please try again");
		}
		return "login/login";

	}

}
