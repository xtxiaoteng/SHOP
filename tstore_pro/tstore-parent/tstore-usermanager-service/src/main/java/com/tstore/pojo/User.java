package com.tstore.pojo;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class User {
	
	@ApiModelProperty(value="id,主键：修改的时候一定填写，保存请忽略")
    private Long id;
	@ApiModelProperty(required=true,value="用户名")
    private String username;
	@ApiModelProperty(required=true,value="密码")
    private String password;
	@ApiModelProperty(required=true,value="手机号码")
    private String phone;
	@ApiModelProperty(readOnly=true,value="邮箱")
    private String email;
    @ApiModelProperty(hidden=true)
    private Date created;
    @ApiModelProperty(hidden=true)
    private Date updated;
    private String salt;
    @ApiModelProperty(hidden=true)
    private Date lastLoginDate;
    @ApiModelProperty(hidden=true)
    private Integer loginCount;
    
    public User() {
        super();
    }
    
    public User(Long id,String username,String password,String phone,String email,Date created,Date updated,String salt,Date lastLoginDate,Integer loginCount) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.created = created;
        this.updated = updated;
        this.salt = salt;
        this.lastLoginDate = lastLoginDate;
        this.loginCount = loginCount;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }
    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }
 	
 	public String toString(Long id,String username,String password,String phone,String email,Date created,Date updated,String salt,Date lastLoginDate,Integer loginCount) {
        return "User:【this.id:"+id+",this.username:"+username+",this.password:"+password+",this.phone:"+phone+",this.email:"+email+",this.created:"+created+",this.updated:"+updated+",this.salt:"+salt+",this.lastLoginDate:"+lastLoginDate+",this.loginCount:"+loginCount+"】";
    }
    
    public static class UserBuilder{
 	   
	    private Long id;
	    private String username;
	    private String password;
	    private String phone;
	    private String email;
	    private Date created;
	    private Date updated;
	    private String salt;
	    private Date lastLoginDate;
	    private Integer loginCount;

 	    public String toString(){
 	    	 return "User.UserBuilder:【this.id:"+id+",this.username:"+username+",this.password:"+password+",this.phone:"+phone+",this.email:"+email+",this.created:"+created+",this.updated:"+updated+",this.salt:"+salt+",this.lastLoginDate:"+lastLoginDate+",this.loginCount:"+loginCount+"】";
 	    }

 	    public User build(){
 	      return new User(id,username,password,phone,email,created,updated,salt,lastLoginDate,loginCount);
 	    }

 	    public UserBuilder id(Long id){
 	      this.id = id;
 	      return this;
 	    }
 	    
 	    public UserBuilder username(String username){
 	      this.username = username;
 	      return this;
 	    }
 	    
 	    public UserBuilder password(String password){
 	      this.password = password;
 	      return this;
 	    }
 	    
 	    public UserBuilder phone(String phone){
 	      this.phone = phone;
 	      return this;
 	    }
 	    
 	    public UserBuilder email(String email){
 	      this.email = email;
 	      return this;
 	    }
 	    
 	    public UserBuilder created(Date created){
 	      this.created = created;
 	      return this;
 	    }
 	    
 	    public UserBuilder updated(Date updated){
 	      this.updated = updated;
 	      return this;
 	    }
 	    
 	    public UserBuilder salt(String salt){
 	      this.salt = salt;
 	      return this;
 	    }
 	    
 	    public UserBuilder lastLoginDate(Date lastLoginDate){
 	      this.lastLoginDate = lastLoginDate;
 	      return this;
 	    }
 	    
 	    public UserBuilder loginCount(Integer loginCount){
 	      this.loginCount = loginCount;
 	      return this;
 	    }
 	    
 	  }

 	  public static User.UserBuilder builder(){
 	    return new User.UserBuilder();
 	  }
}