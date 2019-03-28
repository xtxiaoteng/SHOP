package com.store.web.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.redis.RedisService;

@RestController
public class RedisController {
	
	    
	  @Autowired
	  private RedisService redisService;
	  
	  @GetMapping("/set/string")
	  public String set() {
		  redisService.set("username3","xiaoxiao3");
		  return "success";
	  }

}
