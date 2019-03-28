package com.tstore.pojo;

import java.util.Date;
import java.io.Serializable;

public class Content  implements Serializable{
	
    private Long id;
    private Long categoryId;
    private String title;
    private String subTitle;
    private String titleDesc;
    private String url;
    private String pic;
    private String pic2;
    private String content;
    private Date created;
    private Date updated;
    private Integer sortorder;
    private Integer status;
    
    public Content() {
        super();
    }
    
    public Content(Long id,Long categoryId,String title,String subTitle,String titleDesc,String url,String pic,String pic2,String content,Date created,Date updated,Integer sortorder,Integer status) {
        super();
        this.id = id;
        this.categoryId = categoryId;
        this.title = title;
        this.subTitle = subTitle;
        this.titleDesc = titleDesc;
        this.url = url;
        this.pic = pic;
        this.pic2 = pic2;
        this.content = content;
        this.created = created;
        this.updated = updated;
        this.sortorder = sortorder;
        this.status = status;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
    public String getTitleDesc() {
        return titleDesc;
    }

    public void setTitleDesc(String titleDesc) {
        this.titleDesc = titleDesc;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
    public String getPic2() {
        return pic2;
    }

    public void setPic2(String pic2) {
        this.pic2 = pic2;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
 	
    public Integer getSortorder() {
    	return sortorder;
    }
    
    public void setSortorder(Integer sortorder) {
    	this.sortorder = sortorder;
    }
    
    
    
 	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String toString(Long id,Long categoryId,String title,String subTitle,String titleDesc,String url,String pic,String pic2,String content,Date created,Date updated) {
        return "Content:【this.id:"+id+",this.categoryId:"+categoryId+",this.title:"+title+",this.subTitle:"+subTitle+",this.titleDesc:"+titleDesc+",this.url:"+url+",this.pic:"+pic+",this.pic2:"+pic2+",this.content:"+content+",this.created:"+created+",this.updated:"+updated+"】";
    }
 	
    


	public static class ContentBuilder{
 	   
	    private Long id;
	    private Long categoryId;
	    private String title;
	    private String subTitle;
	    private String titleDesc;
	    private String url;
	    private String pic;
	    private String pic2;
	    private String content;
	    private Date created;
	    private Date updated;
	    private Integer sortorder;
	    private Integer status;

 	    public String toString(){
 	    	 return "Content.ContentBuilder:【this.id:"+id+",this.categoryId:"+categoryId+",this.title:"+title+",this.subTitle:"+subTitle+",this.titleDesc:"+titleDesc+",this.url:"+url+",this.pic:"+pic+",this.pic2:"+pic2+",this.content:"+content+",this.created:"+created+",this.updated:"+updated+"】";
 	    }

 	    public Content build(){
 	      return new Content(id,categoryId,title,subTitle,titleDesc,url,pic,pic2,content,created,updated,sortorder,status);
 	    }

 	    public ContentBuilder id(Long id){
 	      this.id = id;
 	      return this;
 	    }
 	    
 	    public ContentBuilder categoryId(Long categoryId){
 	      this.categoryId = categoryId;
 	      return this;
 	    }
 	    
 	    public ContentBuilder title(String title){
 	      this.title = title;
 	      return this;
 	    }
 	    
 	    public ContentBuilder subTitle(String subTitle){
 	      this.subTitle = subTitle;
 	      return this;
 	    }
 	    
 	    public ContentBuilder titleDesc(String titleDesc){
 	      this.titleDesc = titleDesc;
 	      return this;
 	    }
 	    
 	    public ContentBuilder url(String url){
 	      this.url = url;
 	      return this;
 	    }
 	    
 	    public ContentBuilder pic(String pic){
 	      this.pic = pic;
 	      return this;
 	    }
 	    
 	    public ContentBuilder pic2(String pic2){
 	      this.pic2 = pic2;
 	      return this;
 	    }
 	    
 	    public ContentBuilder content(String content){
 	      this.content = content;
 	      return this;
 	    }
 	    
 	    public ContentBuilder created(Date created){
 	      this.created = created;
 	      return this;
 	    }
 	    
 	    public ContentBuilder updated(Date updated){
 	      this.updated = updated;
 	      return this;
 	    }
 	    
 	    
 	   public ContentBuilder sortorder(Integer sortorder){
  	      this.sortorder = sortorder;
  	      return this;
  	    }
 	   
 	  public ContentBuilder status(Integer status){
  	      this.status = status;
  	      return this;
  	    }
 	    
 	  }

 	  public static Content.ContentBuilder builder(){
 	    return new Content.ContentBuilder();
 	  }
}