package com.tstore.pojo;

import java.io.Serializable;
import java.util.Date;

public class ItemCategory implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;
    private Long parentId;
    private String name;
    private Integer status;
    private Integer sortOrder;
    private Boolean isParent;
    private Date created;
    private Date updated;
    private Integer groupnum;
    private String img;
    private String url;
    
    public ItemCategory() {
        super();
    }
    
    public ItemCategory(Long id,Long parentId,String name,Integer status,Integer sortOrder,Boolean isParent,Date created,Date updated,Integer groupnum,String img,String url) {
        super();
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.status = status;
        this.sortOrder = sortOrder;
        this.isParent = isParent;
        this.created = created;
        this.updated = updated;
        this.groupnum = groupnum;
        this.img = img;
        this.url = url;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
    public Boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(Boolean isParent) {
        this.isParent = isParent;
    }
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
    public Integer getGroupnum() {
        return groupnum;
    }

    public void setGroupnum(Integer groupnum) {
        this.groupnum = groupnum;
    }
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
 	
 	public String toString(Long id,Long parentId,String name,Integer status,Integer sortOrder,Boolean isParent,Date created,Date updated,Integer groupnum,String img,String url) {
        return "ItemCategory:【this.id:"+id+",this.parentId:"+parentId+",this.name:"+name+",this.status:"+status+",this.sortOrder:"+sortOrder+",this.isParent:"+isParent+",this.created:"+created+",this.updated:"+updated+",this.groupnum:"+groupnum+",this.img:"+img+",this.url:"+url+"】";
    }
    
    public static class ItemCategoryBuilder{
 	   
	    private Long id;
	    private Long parentId;
	    private String name;
	    private Integer status;
	    private Integer sortOrder;
	    private Boolean isParent;
	    private Date created;
	    private Date updated;
	    private Integer groupnum;
	    private String img;
	    private String url;

 	    public String toString(){
 	    	 return "ItemCategory.ItemCategoryBuilder:【this.id:"+id+",this.parentId:"+parentId+",this.name:"+name+",this.status:"+status+",this.sortOrder:"+sortOrder+",this.isParent:"+isParent+",this.created:"+created+",this.updated:"+updated+",this.groupnum:"+groupnum+",this.img:"+img+",this.url:"+url+"】";
 	    }

 	    public ItemCategory build(){
 	      return new ItemCategory(id,parentId,name,status,sortOrder,isParent,created,updated,groupnum,img,url);
 	    }

 	    public ItemCategoryBuilder id(Long id){
 	      this.id = id;
 	      return this;
 	    }
 	    
 	    public ItemCategoryBuilder parentId(Long parentId){
 	      this.parentId = parentId;
 	      return this;
 	    }
 	    
 	    public ItemCategoryBuilder name(String name){
 	      this.name = name;
 	      return this;
 	    }
 	    
 	    public ItemCategoryBuilder status(Integer status){
 	      this.status = status;
 	      return this;
 	    }
 	    
 	    public ItemCategoryBuilder sortOrder(Integer sortOrder){
 	      this.sortOrder = sortOrder;
 	      return this;
 	    }
 	    
 	    public ItemCategoryBuilder isParent(Boolean isParent){
 	      this.isParent = isParent;
 	      return this;
 	    }
 	    
 	    public ItemCategoryBuilder created(Date created){
 	      this.created = created;
 	      return this;
 	    }
 	    
 	    public ItemCategoryBuilder updated(Date updated){
 	      this.updated = updated;
 	      return this;
 	    }
 	    
 	    public ItemCategoryBuilder groupnum(Integer groupnum){
 	      this.groupnum = groupnum;
 	      return this;
 	    }
 	    
 	    public ItemCategoryBuilder img(String img){
 	      this.img = img;
 	      return this;
 	    }
 	    
 	    public ItemCategoryBuilder url(String url){
 	      this.url = url;
 	      return this;
 	    }
 	    
 	  }

 	  public static ItemCategory.ItemCategoryBuilder builder(){
 	    return new ItemCategory.ItemCategoryBuilder();
 	  }
}