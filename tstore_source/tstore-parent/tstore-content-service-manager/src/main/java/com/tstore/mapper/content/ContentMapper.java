package com.tstore.mapper.content;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tstore.pojo.Content;


/**
 * 
 * todo:内容管理
 * Content<br/>
 * 创建人:xiaoteng<br/>
 * 时间：2019年03月14日 00:27:13 <br/>
 * @version 1.0.0<br/>
 *
 */
public interface ContentMapper {
	
	public List<Content> findContentsByCategoryid(@Param("categoryId")Long categoryId,Integer pageNo,Integer pageSize);
}