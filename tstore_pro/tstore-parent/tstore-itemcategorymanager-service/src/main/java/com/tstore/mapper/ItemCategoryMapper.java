package com.tstore.mapper;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.ibatis.annotations.Param;

import com.tstore.vo.ItemCategoryVo;
import com.tstore.pojo.ItemCategory;

/**
 * 
 * todo:用户实体
 * ItemCategory<br/>
 * 创建人:xiaoteng<br/>
 * 时间：2019年01月27日 20:52:19 <br/>
 * @version 1.0.0<br/>
 *
 */
public interface ItemCategoryMapper {
	
	/*查询分类*/
	public List<Map<String,Object>>  findItemgorys();
	/*查询二级分类*/
	public List<Map<String, Object>> findItemgorynext(@Param("ids")List<Long> ids);
	
	
}