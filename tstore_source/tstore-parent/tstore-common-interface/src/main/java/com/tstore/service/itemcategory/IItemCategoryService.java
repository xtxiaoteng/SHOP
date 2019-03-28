package com.tstore.service.itemcategory;

import java.util.List;
import java.util.Map;

public interface IItemCategoryService {
	/* 查询分类 */
	public List<Map<String, Object>> findItemgorys();

	/* 查询二级分类 */
	public List<Map<String, Object>> findItemgorynext(List<Long> ids);
}
