package org.mintleaf.modules.core.entity;

import org.beetl.sql.core.annotatoin.Table;

import java.util.Date;


/* 
* 
* gen by beetlsql 2018-08-20
*/
@Table(name="mintleaf_fast.core_user_role")
public class CoreUserRole   {
	
	/*
	角色id
	*/
	private Integer roleid ;
	/*
	用户id
	*/
	private Integer userid ;
	/*
	创建人，0为初始化
	*/
	private Integer creator ;
	/*
	创建时间
	*/
	private Date createtime ;
	
	public CoreUserRole() {
	}
	
	/**
	* 角色id
	*@return 
	*/
	public Integer getRoleid(){
		return  roleid;
	}
	/**
	* 角色id
	*@param  roleid
	*/
	public void setRoleid(Integer roleid ){
		this.roleid = roleid;
	}
	
	/**
	* 用户id
	*@return 
	*/
	public Integer getUserid(){
		return  userid;
	}
	/**
	* 用户id
	*@param  userid
	*/
	public void setUserid(Integer userid ){
		this.userid = userid;
	}
	
	/**
	* 创建人，0为初始化
	*@return 
	*/
	public Integer getCreator(){
		return  creator;
	}
	/**
	* 创建人，0为初始化
	*@param  creator
	*/
	public void setCreator(Integer creator ){
		this.creator = creator;
	}
	
	/**
	* 创建时间
	*@return 
	*/
	public Date getCreatetime(){
		return  createtime;
	}
	/**
	* 创建时间
	*@param  createtime
	*/
	public void setCreatetime(Date createtime ){
		this.createtime = createtime;
	}
	

}
