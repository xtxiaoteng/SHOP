/**
 * 电子商城系统平台<br/>
 * com.store.controller<br/>
 * IndexController.java<br/>
 * 创建人:xiaoteng <br/>
 * 时间：2019年1月28日-上午2:43:29 <br/>
 * 2019个人版权所有<br/>
 */
package com.store.web.content;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.store.service.ContentDubboService;
import com.tstore.pojo.Content;

/**
 * 
 * IndexController<br/>
 * 创建人:xiaoteng<br/>
 * 时间：2019年1月28日-上午2:43:29 <br/>
 * 
 * @version 1.0.0<br/>
 * 
 */
@RestController
public class ContentController {

	@Autowired
	private ContentDubboService conDubboService;

	@GetMapping("/findContentsBycategoryid")
	public ResponseEntity<List<Content>> findContentsBycategoryid(@RequestParam("cid") Long cid,
			@RequestParam(name = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(name = "pageSize", required = false, defaultValue = "4") Integer pageSize) {
		try {
			List<Content> contents = conDubboService.findContentsBycategoryid(cid, pageNo, pageSize);
			return ResponseEntity.status(HttpStatus.OK).body(contents);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

}
