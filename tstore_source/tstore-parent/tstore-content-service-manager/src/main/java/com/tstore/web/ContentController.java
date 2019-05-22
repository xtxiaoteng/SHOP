package com.tstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tstore.pojo.Content;
import com.tstore.service.content.IContentService;

@RestController
public class ContentController {
	
	@Autowired
	private IContentService contentService;
	
	@GetMapping("/findContentsByCategoryid/{categroyId}")
	public List<Content> findContentsByCategoryid(@PathVariable("categroyId")Long categroyId){
		return contentService.findContentsByCategoryid(categroyId, 0,3);
	}

	@GetMapping("/test")
	public String test(){
		return  "test";
	}

}
