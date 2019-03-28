package com.tstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tstore.pojo.Item;
import com.tstore.service.item.IItemService;
import com.tstore.vo.Params;
import com.tstore.vo.SItem;

@Service
public class SearchRelationService {

	
	@Reference(check=false)
	private IItemService itemService;
	
	@Autowired
	private SItemService sitemService;

	public Item getItemById(Long id) {
		return itemService.getItemById(id);
	}
	
	public List<Item> queryItemAll(){
		return itemService.queryItemAll(null);
	}
	
	
	public void crerateIndex(Params params) {
		List<Item>  items =  itemService.queryItemAll(null);
		for (Item item : items) {
		   SItem sItem = new SItem();
		   sItem.setId(item.getId().toString());
		   sItem.setTitle(item.getTitle());
		   sItem.setDate(item.getCreateTime());
		   sItem.setPrice(item.getPrice());
		   sItem.setSellPoint(item.getSellPoint());
		   sItem.setStatus(item.getStatus());;
		   sItem.setImg(item.getImage());
		   sitemService.save(sItem);
		}
	}
	
	

   
}
