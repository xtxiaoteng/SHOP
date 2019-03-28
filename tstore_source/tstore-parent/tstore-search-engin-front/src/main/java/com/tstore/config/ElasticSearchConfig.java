package com.tstore.config;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author Conn
 * @Date 2018/10/15
 */
@SpringBootConfiguration
//自定义加载属性配置文件 : 
@PropertySource(value = "classpath:config/elasticsearch.properties")
public class ElasticSearchConfig {
	private static final Logger logger = LoggerFactory.getLogger(ElasticSearchConfig.class);

	@Value("${spring.es.host}")
	private String hostName;

	@Value("${spring.es.port}")
	private Integer transport;

	@Value("${spring.es.cluster.nodes}")
	private static String clusternodes = "elasticsearch";

	@Bean(name = "getTransportClient")
	public TransportClient getTransportClient() {
		logger.info("ElasticSearch初始化开始");
		TransportClient transportClient = null;
		try {
			TransportAddress transportAddress = new TransportAddress(InetAddress.getByName("127.0.0.1"),
					Integer.valueOf("9300"));
			// 配置信息
			Settings es = Settings.builder().put("cluster.name", "elasticsearch").build();
			// 配置信息Settings自定义
			transportClient = new PreBuiltTransportClient(es);
			transportClient.addTransportAddress(transportAddress);

		} catch (UnknownHostException e) {
			logger.error("ES创建错误", e);
		}

		return transportClient;
	}

//    public static void main(String[] args)  throws Exception{
//    	TransportClient  transportClient= transportClient() ;
//    	//2.创建映射 
//		transportClient.admin().indices().prepareCreate("blogs").get();
//
//        //定义文档字段的映射规范 mapping
//        XContentBuilder builder = XContentFactory.jsonBuilder();
//        builder.startObject()
//                .startObject("blog") // type 表
//                .startObject("properties") // store 的含义：如果是yes，返回是有内容，如果为no,那么返回是null,仅仅为搜索服务
//                .startObject("id").field("type","integer").field("store","yes").endObject() //analyzer  ik_smart 和 ik_max_word
//                .startObject("title").field("type","string").field("store","yes").field("analyzer","ik_max_word").endObject()
//                .startObject("status").field("type","integer").field("store","no").endObject()
//                .startObject("content").field("type","string").field("store","yes").field("analyzer","ik_smart").endObject()
//                .endObject()
//                .endObject()
//                .endObject();
//
//        //将映射关联到索引
//        PutMappingRequest mappingRequest = Requests.putMappingRequest("blogs").type("blog").source(builder);
//        transportClient.admin().indices().putMapping(mappingRequest).get();
//        //3：关闭
//        transportClient.close();
//	}
}