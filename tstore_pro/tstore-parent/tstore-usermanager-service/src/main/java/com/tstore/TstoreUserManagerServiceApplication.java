/**
 * 电子商城系统平台<br/>
 * com.tstore<br/>
 * TstoreUserManagerServiceApplication.java<br/>
 * 创建人:xiaoteng <br/>
 * 时间：2019年1月26日-上午4:31:10 <br/>
 * 2019个人版权所有<br/>
 */
package com.tstore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * TstoreUserManagerServiceApplication<br/>
 * 创建人:xiaoteng<br/>
 * 时间：2019年1月26日-上午4:31:10 <br/>
 * 
 * @version 1.0.0<br/>
 * 
 */
@MapperScan("com.tstore.mapper")
@SpringBootApplication
public class TstoreUserManagerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TstoreUserManagerServiceApplication.class, args);
	}

}
