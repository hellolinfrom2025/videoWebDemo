package org.mintleaf.modules.core.entity;

import org.beetl.sql.core.annotatoin.Table;

import java.util.Date;


/* 
* 
* gen by beetlsql 2018-08-20
*/
@Table(name="mintleaf_fast.core_role_menu")
public class CoreRoleMenu   {
	
	/*
	菜单id
	*/
	private Integer menuid ;
	/*
	角色id
	*/
	private Integer roleid ;
	/*
	创建人，0为初始化
	*/
	private Integer creator ;
	/*
	1为有权限，0为没有权限（防止以后会出现角色有权限但是个人没有权限的情况）
	*/
	private Integer flag ;
	/*
	创建时间
	*/
	private Date createtime ;
	
	public CoreRoleMenu() {
	}
	
	/**
	* 菜单id
	*@return 
	*/
	public Integer getMenuid(){
		return  menuid;
	}
	/**
	* 菜单id
	*@param  menuid
	*/
	public void setMenuid(Integer menuid ){
		this.menuid = menuid;
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
