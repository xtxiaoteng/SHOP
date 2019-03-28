/**
 * 电子商城系统平台<br/>
 * com.tstore<br/>
 * ItemCategoryserviceApplication.java<br/>
 * 创建人:xiaoteng <br/>
 * 时间：2019年1月27日-下午7:44:33 <br/>
 * 2019个人版权所有<br/>
 */
package com.tstore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;

/**
 * 
 * ItemCategoryserviceApplication<br/>
 * 创建人:xiaoteng<br/>
 * 时间：2019年1月27日-下午7:44:33 <br/>
 * 
 * @version 1.0.0<br/>
 * 
 */
@EnableDubbo
@MapperScan("com.tstore.mapper")
@SpringBootApplication
public class ContentserviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ContentserviceApplication.class, args);
	}
}
