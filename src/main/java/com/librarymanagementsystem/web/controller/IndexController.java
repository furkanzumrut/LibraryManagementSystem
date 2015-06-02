package com.librarymanagementsystem.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This controller shows our welcome file to spring mvc, so our visitor will see index.html first.
 * @author furkanzumrut
 *
 */
@Controller
public class IndexController {

	@RequestMapping("/")
	String home() {
		return "index";
	}

}
