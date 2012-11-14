package com.pcitc.uk.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping(value = "/welcome.html")
	public String welcome() {

		return "welcome";
	}

	@RequestMapping(value = "/index.html")
	public String index() {

		return "main";
	}

}
