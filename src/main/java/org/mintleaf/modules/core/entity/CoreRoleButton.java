package org.mintleaf.modules.core.entity;

import org.beetl.sql.core.annotatoin.Table;

import java.util.Date;


/* 
* 
* gen by beetlsql 2018-09-03
*/
@Table(name="mintleaf_fast.core_role_button")
public class CoreRoleButton   {
	
	/*
	按钮id
	*/
	private Integer buttonid ;
	/*
	角色id
	*/
	private Integer roleid ;
	/*
	1为有权限，0为没有权限（防止以后会出现角色有权限但是个人没有权限的情况）
	*/
	private Integer flag ;
	/*
	创建时间
	*/
	private Date createtime ;
	
	public CoreRoleButton() {
	}
	
	/**
	* 按钮id
	*@return 
	*/
	public Integer getButtonid(){
		return  buttonid;
	}
	/**
	* 按钮id
	*@param  buttonid
	*/
	public void setButtonid(Integer buttonid ){
		this.buttonid = buttonid;
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
	* 1为有权限，0为没有权限（防止以后会出现角色有权限但是个人没有权限的情况）
	*@return 
	*/
	public Integer getFlag(){
		return  flag;
	}
	/**
	* 1为有权限，0为没有权限（防止以后会出现角色有权限但是个人没有权限的情况）
	*@param  flag
	*/
	public void setFlag(Integer flag ){
		this.flag = flag;
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
