package com.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.store.redis.BannerKey;
import com.store.redis.RedisService;
import com.tstore.pojo.Content;
import com.tstore.service.content.IContentService;
import com.tstore.util.JsonUtil;
import com.tstore.util.TmStringUtils;

@Service
public class ContentDubboService {
    
	 @Reference
	 private IContentService contentService;
	 
	 /*对应的封装的jedis工具类  */
	 @Autowired
	 private RedisService redisService;
	 
	 
	 public List<Content> findContentsBycategoryid(Long categoryId,Integer pageNo,Integer pageSize){
		 if(categoryId==null)return null;
		 List<Content>contents = null;
		 String key =BannerKey.banner.getPrefix()+":"+categoryId;
		 String redisBanners = redisService.get(key);
		 if(TmStringUtils.isNotEmpty(redisBanners)) {
			 System.out.println("----------进去缓存查询数据");
			 contents = JsonUtil.string2Obj(redisBanners, List.class, Content.class);
		 }else {
			 System.out.println("查询数据库并且放入到缓存 ");
			 contents = contentService.findContentsByCategoryid(categoryId, pageNo, pageSize);
			 /*不设置过期时间*/
			// redisService.set(key, JsonUtil.obj2String(contents));
			 /*设置过期时间*/
			 redisService.setEx(key, JsonUtil.obj2String(contents), BannerKey.banner.expireSeconds());
		 }
		 return contents;
	 }
	
	
}
