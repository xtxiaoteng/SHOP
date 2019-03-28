package com.tstore.service.item;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tstore.mapper.ItemMapper;
import com.tstore.pojo.Item;
import com.tstore.vo.Params;


/**
 * 
 * todo:用户实体Service实现类
 * ItemServiceImpl<br/>
 * 作者:xiaoteng<br/>
 * 创建时间：2019年03月04日 22:24:06 <br/>
 * @version 1.0.0<br/>
 *
 */
@Service
@Component
public class ItemServiceImpl implements IItemService  {


	@Autowired
	private ItemMapper itemMapper;
	
	
	
	@Override
	public Item saveItem(Item item) {
		int count = itemMapper.saveItem(item);
		return count >0 ? item : null;
	}
	@Override
	public int updateItem(Item item) {
		return itemMapper.updateItem(item);
	}

	
	@Override
	public int deleteItemById(Long id) {
		return itemMapper.deleteItemById(id);
	}

	
	@Override
	public Item getItemById(Long id) {
		Item item = itemMapper.getItemById(id);
		return item;
	}
	@Override
	public Map<String, Object> queryItemAllPage(Params params){
		PageHelper.startPage(params.getPageNo(), params.getPageSize());
		List<Item> items = itemMapper.queryItemAll(params);
		PageInfo<Item> pageInfo = new PageInfo<>(items);
		Map<String, Object> map = new HashMap<>();
		map.put("data",pageInfo);
		return map;
	}

	@Override
	public List<Item> queryItemAll(Params params) {
		// TODO Auto-generated method stub
		return itemMapper.queryItemAll(params);
	}
	@Override
	public int updateItemUrl(Long id, String url) {
		return itemMapper.updateItemUrl(id,url);
	}
	
}
