package org.mintleaf.modules.videowebfront.entity;
import org.beetl.sql.core.annotatoin.Table;

import java.util.Date;


/* 
* 
* gen by beetlsql 2019-05-15
*/
@Table(name="mintleaf_fast.operation_record")
public class OperationRecord   {
	
	/*
	操作记录表主键
	*/
	private Integer id ;
	/*
	操作次数
	*/
	private Integer operationTimes ;
	/*
	用户ID
	*/
	private Integer userId ;
	/*
	视频ID
	*/
	private Integer videoId ;
	/*
	操作类型：1-播放、2-下载、3-收藏、4-点赞
	*/
	private String operationType ;
	/*
	操作时间
	*/
	private Date time ;
	
	public OperationRecord() {
	}
	
	/**
	* 操作记录表主键
	*@return 
	*/
	public Integer getId(){
		return  id;
	}
	/**
	* 操作记录表主键
	*@param  id
	*/
	public void setId(Integer id ){
		this.id = id;
	}
	
	/**
	* 操作次数
	*@return 
	*/
	public Integer getOperationTimes(){
		return  operationTimes;
	}
	/**
	* 操作次数
	*@param  operationTimes
	*/
	public void setOperationTimes(Integer operationTimes ){
		this.operationTimes = operationTimes;
	}
	
	/**
	* 用户ID
	*@return 
	*/
	public Integer getUserId(){
		return  userId;
	}
	/**
	* 用户ID
	*@param  userId
	*/
	public void setUserId(Integer userId ){
		this.userId = userId;
	}
	
	/**
	* 视频ID
	*@return 
	*/
	public Integer getVideoId(){
		return  videoId;
	}
	/**
	* 视频ID
	*@param  videoId
	*/
	public void setVideoId(Integer videoId ){
		this.videoId = videoId;
	}
	
	/**
	* 操作类型：1-播放、2-下载、3-收藏、4-点赞
	*@return 
	*/
	public String getOperationType(){
		return  operationType;
	}
	/**
	* 操作类型：1-播放、2-下载、3-收藏、4-点赞
	*@param  operationType
	*/
	public void setOperationType(String operationType ){
		this.operationType = operationType;
	}
	
	/**
	* 操作时间
	*@return 
	*/
	public Date getTime(){
		return  time;
	}
	/**
	* 操作时间
	*@param  time
	*/
	public void setTime(Date time ){
		this.time = time;
	}
	

}
