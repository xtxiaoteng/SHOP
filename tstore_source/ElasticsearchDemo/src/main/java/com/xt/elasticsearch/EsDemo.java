package com.xt.elasticsearch;

import java.net.InetAddress;
import java.util.Iterator;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;

public class EsDemo {

	/* 创建将数据索引化 */
	@Test
	public void createIndex() throws Exception {

		// 1: 创建客户端连接
		TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
		// 2：操作 
		// 2.1 创建文档
		XContentBuilder builder = XContentFactory.jsonBuilder();
		builder.startObject().field("id",1)
			        .field("title", "elasticsearch是一个基于lucene的搜索服务")
					.field("content","ElasticSearch是一个基于Lucene的搜索服务器")
					.endObject(); 
		// 2.2 创建索引
		client.prepareIndex("blog", "article", "1").setSource(builder).get();
		// 3：关闭
		client.close();
	}

	// 字符串查询全部
	@Test
	public void searchByKeyword() throws Exception {
		// 1:创建客户端
		// 1: 创建客户端连接
		TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));

		// QueryBuilder queryBuilder = QueryBuilders.queryStringQuery("服务");
		// QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
		// QueryBuilder queryBuilder = QueryBuilders.wildcardQuery("title","服");//搜索结果为0
		// 是分词的问题
		//QueryBuilder queryBuilder = QueryBuilders.matchQuery("title", "服务");
		QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery("服务", "title");
		//QueryBuilder queryBuilder = QueryBuilders.termQuery("title","搜");
		SearchResponse response = client.prepareSearch("blog").setTypes("article").setQuery(queryBuilder).get();
		// 3:显示查询结果
		SearchHits searchHits = response.getHits();
		System.out.println("命中数是：" + searchHits.totalHits);
		// 查询展示的结果
		Iterator<SearchHit> iterator = searchHits.iterator();
		while (iterator.hasNext()) {
			SearchHit searchHit = iterator.next();
			System.out.println("查询结果是：" + searchHit.getSourceAsString());
			System.out.println("id：" + searchHit.getSource().get("id"));
			System.out.println("title：" + searchHit.getSource().get("title"));
			System.out.println("content：" + searchHit.getSource().get("content"));
		}
		// 4:关闭资源
		client.close();
	}
}
