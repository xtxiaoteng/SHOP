package com.tstore.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.tstore.vo.SItem;

public interface ItemRepository extends ElasticsearchRepository<SItem, String> {

}
