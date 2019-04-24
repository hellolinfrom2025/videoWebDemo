package org.mintleaf.modules.core.entity;

import org.beetl.sql.core.annotatoin.Table;

import java.util.Date;


/* 
* 
* gen by beetlsql 2018-08-20
*/
@Table(name="mintleaf_fast.core_role")
public class CoreRole   {
	
	/*
	角色表主键
	*/
	private Integer id ;
	/*
	用户id，0为角色，有关联时则为用户的单独权限
	*/
	private Integer creator ;
	/*
	更新者id
	*/
	private Integer updateuser ;
	/*
	角色描述
	*/
	private String description ;
	/*
	角色名称
	*/
	private String name ;
	/*
	创建时间
	*/
	private Date createtime ;
	/*
	更新时间
	*/
	private Date updatetime ;
	
	public CoreRole() {
	}
	
	/**
	* 角色表主键
	*@return 
	*/
	public Integer getId(){
		return  id;
	}
	/**
	* 角色表主键
	*@param  id
	*/
	public void setId(Integer id ){
		this.id = id;
	}
	
	/**
	* 用户id，0为角色，有关联时则为用户的单独权限
	*@return 
	*/
	public Integer getCreator(){
		return  creator;
	}
	/**
	* 用户id，0为角色，有关联时则为用户的单独权限
	*@param  creator
	*/
	public void setCreator(Integer creator ){
		this.creator = creator;
	}
	
	/**
	* 更新者id
	*@return 
	*/
	public Integer getUpdateuser(){
		return  updateuser;
	}
	/**
	* 更新者id
	*@param  updateuser
	*/
	public void setUpdateuser(Integer updateuser ){
		this.updateuser = updateuser;
	}
	
	/**
	* 角色描述
	*@return 
	*/
	public String getDescription(){
		return  description;
	}
	/**
	* 角色描述
	*@param  description
	*/
	public void setDescription(String description ){
		this.description = description;
	}
	
	/**
	* 角色名称
	*@return 
	*/
	public String getName(){
		return  name;
	}
	/**
	* 角色名称
	*@param  name
	*/
	public void setName(String name ){
		this.name = name;
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
	
	/**
	* 更新时间
	*@return 
	*/
	public Date getUpdatetime(){
		return  updatetime;
	}
	/**
	* 更新时间
	*@param  updatetime
	*/
	public void setUpdatetime(Date updatetime ){
		this.updatetime = updatetime;
	}
	

}
