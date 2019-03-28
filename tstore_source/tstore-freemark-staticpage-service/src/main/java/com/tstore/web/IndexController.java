package com.tstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    
	@GetMapping("/item")
	public String index(ModelMap modelMap) {

		return "item";
	}
}
