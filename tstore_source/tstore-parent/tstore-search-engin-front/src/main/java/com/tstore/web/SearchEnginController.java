package com.tstore.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tstore.service.SItemService;
import com.tstore.service.SearchRelationService;
import com.tstore.vo.SItem;

@Controller
public class SearchEnginController {

	@Autowired
	private SearchRelationService searchRelationService;
	
	@Autowired
	private SItemService sItemService;

	@GetMapping("/tosearch")
	public String search() {
		return "search";
	}
	
	@GetMapping("/search")
	@ResponseBody
	public Page<SItem> search(@RequestParam(name="q",required=false)String keyword) {
		Page<SItem> pages = sItemService.pageQuery(0, 10,keyword);
		return pages;
	}
	
	
	/*测试查询出的数据 查询所有索引化后的数据*/
	@GetMapping("/test")
	@ResponseBody
    public List<String> testS() {
		Iterable<SItem> iterable =  sItemService.getAll();
		Iterator<SItem> s = iterable.iterator();
		List<String> istr = new ArrayList<String>();
		while(s.hasNext()) {
			  SItem sItem =  s.next();
			  istr.add(sItem.toString()+"-------------");
		}
    	return istr;
    }
    
	/**
	 * 数据库商品信息的索引化
	 * @return
	 */
	@GetMapping("/createindex")
    @ResponseBody
	public String createIndex() {
		searchRelationService.crerateIndex(null);
		return "create success";
	}

}
