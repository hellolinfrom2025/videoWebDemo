package org.mintleaf.modules.core.entity;

import org.beetl.sql.core.annotatoin.Table;

import java.util.Date;


/* 
* 
* gen by beetlsql 2018-09-30
*/
@Table(name="mintleaf_fast.core_logger")
public class CoreLogger   {
	
	private Integer id ;
	/*
	请求耗时（毫秒单位）
	*/
	private Integer timeConsuming ;
	/*
	客户端请求IP地址
	*/
	private String clientIp ;
	/*
	请求时httpStatusCode代码，如：200,400,404等
	*/
	private String httpStatusCode ;
	/*
	请求方式method,post,get等
	*/
	private String method ;
	/*
	请求参数内容,json
	*/
	private String paramData ;
	/*
	接口返回时间
	*/
	private String returmTime ;
	/*
	接口返回数据json
	*/
	private String returnData ;
	/*
	请求接口唯一session标识
	*/
	private String sessionId ;
	/*
	终端请求方式,普通请求,ajax请求
	*/
	private String type ;
	/*
	日志请求地址
	*/
	private String uri ;
	/*
	请求时间
	*/
	private Date time ;
	
	public CoreLogger() {
	}
	
	public Integer getId(){
		return  id;
	}
	public void setId(Integer id ){
		this.id = id;
	}
	
	/**
	* 请求耗时（毫秒单位）
	*@return 
	*/
	public Integer getTimeConsuming(){
		return  timeConsuming;
	}
	/**
	* 请求耗时（毫秒单位）
	*@param  timeConsuming
	*/
	public void setTimeConsuming(Integer timeConsuming ){
		this.timeConsuming = timeConsuming;
	}
	
	/**
	* 客户端请求IP地址
	*@return 
	*/
	public String getClientIp(){
		return  clientIp;
	}
	/**
	* 客户端请求IP地址
	*@param  clientIp
	*/
	public void setClientIp(String clientIp ){
		this.clientIp = clientIp;
	}
	
	/**
	* 请求时httpStatusCode代码，如：200,400,404等
	*@return 
	*/
	public String getHttpStatusCode(){
		return  httpStatusCode;
	}
	/**
	* 请求时httpStatusCode代码，如：200,400,404等
	*@param  httpStatusCode
	*/
	public void setHttpStatusCode(String httpStatusCode ){
		this.httpStatusCode = httpStatusCode;
	}
	
	/**
	* 请求方式method,post,get等
	*@return 
	*/
	public String getMethod(){
		return  method;
	}
	/**
	* 请求方式method,post,get等
	*@param  method
	*/
	public void setMethod(String method ){
		this.method = method;
	}
	
	/**
	* 请求参数内容,json
	*@return 
	*/
	public String getParamData(){
		return  paramData;
	}
	/**
	* 请求参数内容,json
	*@param  paramData
	*/
	public void setParamData(String paramData ){
		this.paramData = paramData;
	}
	
	/**
	* 接口返回时间
	*@return 
	*/
	public String getReturmTime(){
		return  returmTime;
	}
	/**
	* 接口返回时间
	*@param  returmTime
	*/
	public void setReturmTime(String returmTime ){
		this.returmTime = returmTime;
	}
	
	/**
	* 接口返回数据json
	*@return 
	*/
	public String getReturnData(){
		return  returnData;
	}
	/**
	* 接口返回数据json
	*@param  returnData
	*/
	public void setReturnData(String returnData ){
		this.returnData = returnData;
	}
	
	/**
	* 请求接口唯一session标识
	*@return 
	*/
	public String getSessionId(){
		return  sessionId;
	}
	/**
	* 请求接口唯一session标识
	*@param  sessionId
	*/
	public void setSessionId(String sessionId ){
		this.sessionId = sessionId;
	}
	
	/**
	* 终端请求方式,普通请求,ajax请求
	*@return 
	*/
	public String getType(){
		return  type;
	}
	/**
	* 终端请求方式,普通请求,ajax请求
	*@param  type
	*/
	public void setType(String type ){
		this.type = type;
	}
	
	/**
	* 日志请求地址
	*@return 
	*/
	public String getUri(){
		return  uri;
	}
	/**
	* 日志请求地址
	*@param  uri
	*/
	public void setUri(String uri ){
		this.uri = uri;
	}
	
	/**
	* 请求时间
	*@return 
	*/
	public Date getTime(){
		return  time;
	}
	/**
	* 请求时间
	*@param  time
	*/
	public void setTime(Date time ){
		this.time = time;
	}
	

}
