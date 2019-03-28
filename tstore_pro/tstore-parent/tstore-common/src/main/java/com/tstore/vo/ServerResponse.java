package com.tstore.vo;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;;

/**
 * 
 * { data: status/code: msg:
 * 
 * }
 * 
 * 统一返回值
 * 
 * @author Administrator
 * @param
 */

//将该标记放在属性上，如果该属性为NULL则不参与序列化 
//如果放在类上边,那对这个类的全部属性起作用 
//Include.Include.ALWAYS 默认 
//Include.NON_DEFAULT 属性为默认值不序列化 
//Include.NON_EMPTY 属性为 空（“”） 或者为 NULL 都不序列化 
//Include.NON_NULL 属性为NULL 不序列化 
//@JsonInclude(Include.NON_NULL)
public class ServerResponse {

	private int status;
	private String msg;
	private String field;
	private Object data;

	// 私有构造函数
	private ServerResponse(int status) {
		this.status = status;
	}

	// 私有构造函数
	private ServerResponse(int status, Object data) {
		this.status = status;
		this.data = data;
	}
	

	// 私有构造函数
	private ServerResponse(int status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	// 私有构造函数
	private ServerResponse(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}
	
	// 验证私有构造函数
	private ServerResponse(int status,String field, String msg,Object data) {
		this.status = status;
		this.field = field;
		this.msg = msg;
		this.data = data;
	}

	// 使之不在json序列化结果当中
	@JsonIgnore
	public boolean isSuccess() {
		return this.status == ResponseCode.SUCCESS.getCode();
	}

	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	public Date getDate() {
		return new Date();
	}

	public int getStatus() {
		return status;
	}

	public Object getData() {
		return data;
	}

	public String getMsg() {
		return msg;
	}

	public String getField() {
		return field;
	}

	// 正确返回
	public static ServerResponse createBySuccess() {
		return new ServerResponse(ResponseCode.SUCCESS.getCode());
	}

	public static ServerResponse createBySuccessMessage(String msg) {
		return new ServerResponse(ResponseCode.SUCCESS.getCode(), msg);
	}

	public static ServerResponse createBySuccess(Object data) {
		return new ServerResponse(ResponseCode.SUCCESS.getCode(), data);
	}

	public static ServerResponse createBySuccess(String msg, Object data) {
		return new ServerResponse(ResponseCode.SUCCESS.getCode(), msg, data);
	}

	public static ServerResponse createBySuccess(ResponseCode responseCode) {
		return new ServerResponse(responseCode.getCode(), responseCode.getDesc());
	}

	public static ServerResponse createBySuccess(ResponseCode responseCode, Object data) {
		return new ServerResponse(responseCode.getCode(), responseCode.getDesc(), data);
	}

	// error返回
	public static ServerResponse createByError() {
		return new ServerResponse(500, ResponseCode.ERROR.getDesc());
	}

	public static ServerResponse createByErrorMessage(String errorMessage) {
		return new ServerResponse(ResponseCode.ERROR.getCode(), errorMessage);
	}

	public static ServerResponse createByErrorCodeMessage(int errorCode, String errorMessage) {
		return new ServerResponse(errorCode, errorMessage);
	}

	public static ServerResponse createByErrorCodeMessage(ResponseCode responseCode) {
		return new ServerResponse(responseCode.getCode(), responseCode.getDesc());
	}
	
	public static ServerResponse createByErrorValidator(int code,String field,String message) {
		return new ServerResponse(code, field,message,null);
	}
	
	public static ServerResponse createByErrorValidator(int code,String field,String message,Object data) {
		return new ServerResponse(code, field,message,data);
	}

}
