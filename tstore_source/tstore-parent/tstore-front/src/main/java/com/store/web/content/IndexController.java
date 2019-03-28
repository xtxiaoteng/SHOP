/**
 * 电子商城系统平台<br/>
 * com.store.controller<br/>
 * IndexController.java<br/>
 * 创建人:xiaoteng <br/>
 * 时间：2019年1月28日-上午2:43:29 <br/>
 * 2019个人版权所有<br/>
 */
package com.store.web.content;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 
 * IndexController<br/>
 * 创建人:xiaoteng<br/>
 * 时间：2019年1月28日-上午2:43:29 <br/>
 * @version 1.0.0<br/>
 * 
 */
@Controller
public class IndexController {
     
    @GetMapping("index")
	public String index() {
      return "index";
	}
    
    
}
