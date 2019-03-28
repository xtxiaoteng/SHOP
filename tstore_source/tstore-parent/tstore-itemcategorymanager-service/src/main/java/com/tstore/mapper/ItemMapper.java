package com.tstore.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.tstore.pojo.Item;
import com.tstore.vo.Params;

/**
 * 
 * todo:用户实体
 * Item<br/>
 * 创建人:xiaoteng<br/>
 * 时间：2019年03月04日 22:24:06 <br/>
 * @version 1.0.0<br/>
 *
 */
public interface ItemMapper {
	//添加
		public int saveItem(Item item);
		//修改
		public int updateItem(Item item);
		//删除
		public int deleteItemById(@Param("id")Long id);
		//查询单个
		public Item getItemById(@Param("id")Long id);
		//查询所有
		public List<Item> queryItemAll(Params params);
		//分页查询 
		public Map<String, Object> queryItemAllPage(Params params);
		
		// 更新静态化URL地址
		public int updateItemUrl(@Param("id")Long id,@Param("url")String url);
		
		
}