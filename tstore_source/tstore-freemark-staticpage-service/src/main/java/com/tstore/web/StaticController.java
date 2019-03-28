package com.tstore.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tstore.service.StaticService;

@RestController
@RequestMapping("/static")
public class StaticController {
	@Autowired
	private StaticService staticService;
	/*通过商品id进行静态化11*/
	@GetMapping("/item/{id}")
	public String staticItem(@PathVariable("id") Long id, HttpServletRequest request) {
		staticService.staticItem(id, request);
		return "success";
	}

}
