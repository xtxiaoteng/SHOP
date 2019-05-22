package com.tstore.service;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import com.tstore.repository.ItemRepository;
import com.tstore.vo.SItem;


@Service
public class SItemServiceImpl implements SItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    
    //@Autowired
    //private TransportClient transportClient;

    @Override
    public long count() {
        return itemRepository.count();
    }

    @Override
    public SItem save(SItem SItem) {
        return itemRepository.save(SItem);
    }

    @Override
    public void delete(SItem SItem) {
        itemRepository.delete(SItem);
//        itemRepository.deleteById(SItem.getSkuId());
    }

    @Override
    public Iterable<SItem> getAll() {
        return itemRepository.findAll();
    }

    @Override
    public List<SItem> getByName(String name) {
        List<SItem> list = new ArrayList<>();
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("title", name);
        Iterable<SItem> iterable = itemRepository.search(matchQueryBuilder);
        iterable.forEach(e->list.add(e));
        return list;
    }
 
  /*高亮分页的 有问题 有待解决*/
    @Override
    public AggregatedPage<SItem>  pageQuery(Integer pageNo, Integer pageSize, String keyword) {

        Sort sort = Sort.by(Sort.Order.desc("price"));
    	PageRequest pageable = PageRequest.of(pageNo, pageSize,sort);

        String preTag = "<i class='skeyon'>";//google的色值
        String postTag = "</i>";
       
        
        SearchQuery searchQuery = new NativeSearchQueryBuilder().
        		withQuery(QueryBuilders.multiMatchQuery(keyword,"title","sellPoint"))
        		.withHighlightFields(
                		new HighlightBuilder.Field("title").preTags(preTag).postTags(postTag),
                        new HighlightBuilder.Field("sellPoint").preTags(preTag).postTags(postTag))
        		.withPageable(pageable).build();
        
        // 高亮字段
        AggregatedPage<SItem> ideas = elasticsearchTemplate.queryForPage(searchQuery, SItem.class, new SearchResultMapper() {
	
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
                long total = response.getHits().totalHits;
                float score = response.getHits().getMaxScore();
                List<SItem> sItems = new ArrayList<>();
                for (SearchHit searchHit : response.getHits()) {
                    if (response.getHits().getHits().length <= 0) {
                        return null;
                    }
                    
                    SItem sitem = new SItem();
                    //name or memoe
              
                    HighlightField title = searchHit.getHighlightFields().get("title");
                    if (title != null) {
                    	sitem.setTitle(title.fragments()[0].toString());
                    }else {
                    	sitem.setTitle(String.valueOf(searchHit.getSourceAsMap().get("title")));
                    }
                    
                    HighlightField ideaContent = searchHit.getHighlightFields().get("sellPoint");
                    if (ideaContent != null) {
                    	sitem.setSellPoint(ideaContent.fragments()[0].toString());
                    }else {
                    	sitem.setSellPoint(String.valueOf(searchHit.getSourceAsMap().get("sellPoint")));
                    }
                    sitem.setPrice(Float.parseFloat(String.valueOf(searchHit.getSourceAsMap().get("price"))));
                 
                    sItems.add(sitem);
                }
                
               return new AggregatedPageImpl((List<T>) sItems,pageable,total,score);
            }
        });
        
        
        return ideas;
    }
 
      
//    @Override
//    public Page<SItem> pageQuery(Integer pageNo, Integer pageSize, String kw) {
//        SearchQuery searchQuery = new NativeSearchQueryBuilder()
//                //.withQuery(QueryBuilders.multiMatchQuery(kw,"title","sellPoint"))
//        		.withQuery(QueryBuilders.termQuery("title",kw))
//                .withPageable(PageRequest.of(pageNo, pageSize))
//                .build();
//        return itemRepository.search(searchQuery);
//    }


}