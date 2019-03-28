package com.tstore.service.itemcategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.tstore.mapper.ItemCategoryMapper;

/**
 * todo:用户实体Service实现类 ItemCategoryServiceImpl<br/>
 * 作者:xiaoteng<br/>
 * 创建时间：2019年01月27日 20:52:19 <br/>
 * @version 1.0.0<br/>
 *
 */
@Service
@Component
public class ItemCategoryServiceImpl implements IItemCategoryService {

	@Autowired
	private ItemCategoryMapper itemCategoryMapper;

	@Override
	public List<Map<String, Object>> findItemgorys() {
		List<Map<String, Object>> roots = itemCategoryMapper.findItemgorys();
		for (Map<String, Object> map : roots) {
			String ids = (String) map.get("ids");
			/* 查询二级分类 */
			List<Map<String, Object>> items = itemCategoryMapper.findItemgorynext(getRootIds(ids.split(",")));
			map.put("items", items);
		}
		return roots;
	}

	@Override
	public List<Map<String, Object>> findItemgorynext(List<Long> ids) {
		return itemCategoryMapper.findItemgorynext(ids);
	}
	
	/**
	 * 
	 * (获取所有的跟节点)<br/>
	 * 方法名：getRootIds<br/>
	 * 创建人：xiaoteng <br/>
	 * 时间：2019年1月28日-上午1:46:24 <br/>
	 * 手机:18820148139<br/>
	 * 
	 * @param ids
	 * @return List<Long><br/>
	 * @exception <br/>
	 * @since 1.0.0<br/>
	 */
	public List<Long> getRootIds(String[] ids) {
		List<Long> rootids = new ArrayList<>();
		for (String id : ids) {
			rootids.add(Long.parseLong(id));
		}
		return rootids;
	}

}
