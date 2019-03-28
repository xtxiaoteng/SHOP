package com.tstore;

import java.io.IOException;

import javax.annotation.Resource;

import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TmallSearchEnginFrontApplicationTestsOld {

   @Autowired
   private TransportClient transportClient;
	
   
   @Before
   public void before() {
	   System.out.println(transportClient);
   }
	
	//创建映射--创建索引文档中属性的规范
	@Test
    public void createMapping() throws  Exception{
    	
        //2.创建映射 
		transportClient.admin().indices().prepareCreate("blogs").get();

        //定义文档字段的映射规范 mapping
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject()
                .startObject("blog") // type 表
                .startObject("properties") // store 的含义：如果是yes，返回是有内容，如果为no,那么返回是null,仅仅为搜索服务
                .startObject("id").field("type","integer").field("store","yes").endObject() //analyzer  ik_smart 和 ik_max_word
                .startObject("title").field("type","string").field("store","yes").field("analyzer","ik_max_word").endObject()
                .startObject("status").field("type","integer").field("store","no").endObject()
                .startObject("content").field("type","string").field("store","yes").field("analyzer","ik_smart").endObject()
                .endObject()
                .endObject()
                .endObject();

        //将映射关联到索引
        PutMappingRequest mappingRequest = Requests.putMappingRequest("blogs").type("blog").source(builder);
        transportClient.admin().indices().putMapping(mappingRequest).get();
        //3：关闭
        transportClient.close();
    }
	
	@Test
	public void createIndex() throws IOException {
		
        // 2: 准备文档对象
        XContentBuilder contentBuilder = XContentFactory.jsonBuilder().startObject()
                .field("id",1)
                .field("title","elasticsearch是一个基于lucene的搜索服务 php javascript")
                .field("content","ElasticSearch是一个基于Lucene的搜索服务器。" +
                        "它提供了一个分布式多用户能力的全文搜索引擎，基于RESTful web接口。" +
                        "Elasticsearch是用Java开发的，并作为Apache许可条款下的开放源码发布，" +
                        "是当前流行的企业级搜索引擎。设计用于云计算中，能够达到实时搜索，稳定，" +
                        "可靠，快速，安装使用方便。")
                .endObject();

        // 3：索引化
        IndexResponse response =  transportClient.prepareIndex("blogs","blog","doc1")
                .setSource(contentBuilder).get();
        System.out.println("索引状态是：" + response.status());
        // 4：释放资源
        transportClient.close();
	}


}
