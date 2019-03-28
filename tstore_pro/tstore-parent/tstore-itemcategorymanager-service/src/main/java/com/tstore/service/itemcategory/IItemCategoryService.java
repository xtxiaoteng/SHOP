package com.tstore.service.itemcategory;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface IItemCategoryService {
	/* 查询分类 */
	public List<Map<String, Object>> findItemgorys();

	/* 查询二级分类 */
	public List<Map<String, Object>> findItemgorynext(@Param("ids") List<Long> ids);
}
