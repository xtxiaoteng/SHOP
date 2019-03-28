package com.tstore.vo;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.Mapping;

@Document(indexName = "items", type = "sitem", shards = 5, replicas = 1, indexStoreType = "fs", refreshInterval = "-1")
@Mapping(mappingPath = "sitem_mapping.json")
public class SItem {
	public static final String IK_MAX_WORD = "ik_max_word";// 细粒度切分
	public static final String IK_SMART = "ik_smart";// 最小切分

	@Id
	private String id;
	@Field(analyzer = IK_SMART, store = true, searchAnalyzer = IK_SMART)
	private String title;
	@Field(analyzer = IK_SMART, store = true, searchAnalyzer = IK_SMART)
	private String sellPoint;
	@Field(store = true, index = false)
	private String img;
	@Field(store = true, index = false)
	private Float price;
	@Field(format = DateFormat.date_time, index = false, store = true)
	private Date date;
	@Field(store = false, index = false)
	private Integer status;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSellPoint() {
		return sellPoint;
	}

	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	@Override
	public String toString() {
		return this.id+"||"+this.img+"||"+this.sellPoint+"||"+this.title;
	}
}
