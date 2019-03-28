package com.tstore.service.content;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tstore.mapper.content.ContentMapper;
import com.tstore.pojo.Content;

/**
 * 
 * todo:内容管理Service实现类
 * ContentServiceImpl<br/>
 * 作者:xiaoteng<br/>
 * 创建时间：2019年03月14日 00:27:13 <br/>
 * @version 1.0.0<br/>
 *
 */
@Service
@Component
public class ContentServiceImpl implements IContentService  {
     
	@Autowired
	private ContentMapper contentMapper;

	@Override
	public List<Content> findContentsByCategoryid(Long categoryId,Integer pageNo,Integer pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<Content>contents =  contentMapper.findContentsByCategoryid(categoryId,pageNo,pageSize);
		PageInfo<Content> pageInfo = new PageInfo<>(contents);
		return pageInfo.getList();
	}
	
	
	
	
	
	
}
