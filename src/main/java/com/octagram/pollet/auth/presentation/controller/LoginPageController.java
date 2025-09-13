package com.octagram.pollet.auth.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginPageController {

	@GetMapping("/auth-test/login")
	public String login() {
		return "login";
	}
}
