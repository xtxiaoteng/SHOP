/**
 * 电子商城系统平台<br/>
 * com.store<br/>
 * TstoreFrontServiceApplication.java<br/>
 * 创建人:xiaoteng <br/>
 * 时间：2019年1月28日-上午2:58:20 <br/>
 * 2019个人版权所有<br/>
 */
package com.tstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;

@EnableDubbo
@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.tstore.repository")
public class TstoreSearchEnginFrontApplication {

    public static void main(String[] args) {
		SpringApplication.run(TstoreSearchEnginFrontApplication.class, args);
	}
}
