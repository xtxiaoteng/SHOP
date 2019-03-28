package com.tstore.service.content;

import java.util.List;
import com.tstore.pojo.Content;

public interface IContentService {
	
	public List<Content> findContentsByCategoryid(Long categoryId,Integer pageNo,Integer pageSize);
	
	
}
