package com.tstore.service.item;


import java.util.List;
import java.util.Map;

import com.tstore.pojo.Item;
import com.tstore.vo.Params;

public interface IItemService {

	// 添加
	public Item saveItem(Item item);

	// 修改
	public int updateItem(Item item);

	// 删除
	public int deleteItemById(Long id);

	// 查询单个
	public Item getItemById(Long id);

	// 查询所有
	public List<Item> queryItemAll(Params params);
	
	
	//分页查询商品
	public Map<String, Object> queryItemAllPage(Params params);
	
	public int updateItemUrl(Long id,String url);
}
