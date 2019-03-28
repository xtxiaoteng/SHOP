package com.tstore;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import com.tstore.service.SItemService;
import com.tstore.vo.SItem;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TmallSearchEnginFrontApplicationTests {

	@Autowired
	private SItemService itemService;

	@Test
	public void contextLoads() {
		System.out.println(itemService.count());
	}
    
	// save 如果id存在就修改，不存在就添加。
	@Test
	public void testInsert() {
		SItem item = new SItem();
		item.setId("1501009001");
		item.setTitle("原味切片面包（10片装）22222222");
		item.setPrice(687f);
		item.setSellPoint("良品铺子");
		
		itemService.save(item); 

		item = new SItem();
		item.setId("1501009002");
		item.setTitle("原味切片面包（6片装）");
		item.setPrice(680f);
		item.setSellPoint("良品铺子");
		
		itemService.save(item);

		item = new SItem();
		item.setId("1501009004");
		item.setTitle("元气吐司850g");
		item.setPrice(320f);
		item.setSellPoint("百草味");
		
		itemService.save(item);

	}

	@Test
	public void testDelete() {
		SItem item = new SItem();
		item.setId("1501009002");
		itemService.delete(item);
	}

	@Test
	public void testGetAll() {
		Iterable<SItem> iterable = itemService.getAll();
		iterable.forEach(e -> System.out.println(e.toString()));
	}

	@Test
	public void testGetByName() {
		List<SItem> list = itemService.getByName("手机");
		System.out.println(list);
	}

	@Test
	public void testPage() {
		Page<SItem> page = itemService.pageQuery(0, 100, "手机");
		System.out.println(page.getTotalPages()); // 页数
		System.out.println(page.getTotalElements());//总数
		System.out.println(page.getSize());// 页码索引
		System.out.println(page.getContent().toString()); // 获取内容
	}

	
	
	@Autowired
	private ElasticsearchTemplate  elasticsearchTemplate;
	
	@Test
    public void test() {
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(
        		QueryBuilders.termQuery("title","双卡双待")).build();
        List<SItem> items = elasticsearchTemplate.queryForList(searchQuery, SItem.class);
        for (SItem item : items) {
            System.out.println(item.toString());
        }
        
        
    }
}
