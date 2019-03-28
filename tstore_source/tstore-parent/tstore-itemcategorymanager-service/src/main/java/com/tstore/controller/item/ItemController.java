package com.tstore.controller.item;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tstore.pojo.Item;
import com.tstore.service.item.IItemService;

/**
 * 
 * todo:用户实体Controller控制器类
 * ItemController<br/>
 * 作者:xiaoteng<br/>
 * 创建时间：2019年03月04日 22:24:06 <br/>
 * @version 1.0.0<br/>
 *
 */
@Controller
public class ItemController {
	

	@Autowired
	private IItemService itemService;
	
	@GetMapping("/item")
	public String itemindex() {
		return "item/index";
	}


	@GetMapping("/item/{id}")
	public String itemdetail(@PathVariable("id") Long id,ModelMap map) {
		map.addAttribute("id", id);
		return "item/detail";
	}
	
	

	@ResponseBody
	@GetMapping("/finditems")
	public List<Item> template() {
		return itemService.queryItemAll(null);
	}
	
	

	@ResponseBody
	@GetMapping("/item/get/{id}")
	public Item getItem(@PathVariable("id") Long id) {
		return itemService.getItemById(id);
	}
	
	
	@ResponseBody
	@PostMapping("/item/save")
	public Item saveItem( Item item) {
		return itemService.saveItem(item);
	}
	

	@ResponseBody
	@PostMapping("/item/update")
	public int updateItem(Item item) {
		return itemService.updateItem(item);
	}
	
	

	@ResponseBody
	@PostMapping("/item/delete/{id}")
	public int deleteItem(@PathVariable("id") Long id) {
		return itemService.deleteItemById(id);
	}
	
}
