package com.tstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tstore.pojo.Item;
import com.tstore.service.SearchRelationService;

@RestController
public class ItemController {

	@Autowired
	private SearchRelationService relationService;

	@GetMapping("/getItemById/{id}")
	public Item getItemById(@PathVariable("id") Long id) {
		return relationService.getItemById(id);
	}
	@GetMapping("/dquery")
	public List<Item> queryItemAll(){
		return relationService.queryItemAll();
	}
}
