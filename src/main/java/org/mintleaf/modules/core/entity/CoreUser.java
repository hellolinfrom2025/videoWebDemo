package org.mintleaf.modules.core.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.beetl.sql.core.annotatoin.Table;

import java.util.Date;


/* 
* 
* gen by beetlsql 2018-08-20
*/
@Table(name="mintleaf_fast.core_user")
@ApiModel(description = "用户对象")
public class CoreUser   {
	
	/*
	用户表主键
	*/
	@ApiModelProperty("用户表主键")
	private Integer id ;
	/*
	创建人，0为初始化
	*/
	@ApiModelProperty("创建人")
	private Integer creator ;
	/*
	用户状态，1启用，0禁用
	*/
	@ApiModelProperty("用户状态")
	private Integer flag ;
	/*
	租户id，0为系统用户
	*/
	@ApiModelProperty("租户id")
	private Integer tenantid ;
	/*
	更新者id
	*/
	@ApiModelProperty("更新者id")
	private Integer updateuser ;
	/*
	用户邮箱
	*/
	@ApiModelProperty("用户邮箱")
	private String email ;
	/*
	用户名
	*/
	@ApiModelProperty("用户名")
	private String name ;
	/*
	用户密码MD5加密
	*/
	@ApiModelProperty("用户密码")
	private String psw ;
	/*
	创建时间
	*/
	@ApiModelProperty("创建时间")
	private Date createtime ;
	/*
	最后登录时间
	*/
	@ApiModelProperty("最后登录时间")
	private Date logintime ;
	/*
	更新时间
	*/
	@ApiModelProperty("更新时间")
	private Date updatetime ;
	/*
    vo字段设置角色
    */
	@ApiModelProperty("vo字段设置角色")
	private String role;
	
	public CoreUser() {
	}
	
	/**
	* 用户表主键
	*@return 
	*/
	public Integer getId(){
		return  id;
	}
	/**
	* 用户表主键
	*@param  id
	*/
	public void setId(Integer id ){
		this.id = id;
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
	* 用户状态，1启用，0禁用
	*@return 
	*/
	public Integer getFlag(){
		return  flag;
	}
	/**
	* 用户状态，1启用，0禁用
	*@param  flag
	*/
	public void setFlag(Integer flag ){
		this.flag = flag;
	}
	
	/**
	* 租户id，0为系统用户
	*@return 
	*/
	public Integer getTenantid(){
		return  tenantid;
	}
	/**
	* 租户id，0为系统用户
	*@param  tenantid
	*/
	public void setTenantid(Integer tenantid ){
		this.tenantid = tenantid;
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
	* 用户邮箱
	*@return 
	*/
	public String getEmail(){
		return  email;
	}
	/**
	* 用户邮箱
	*@param  email
	*/
	public void setEmail(String email ){
		this.email = email;
	}
	
	/**
	* 用户名
	*@return 
	*/
	public String getName(){
		return  name;
	}
	/**
	* 用户名
	*@param  name
	*/
	public void setName(String name ){
		this.name = name;
	}
	
	/**
	* 用户密码MD5加密
	*@return 
	*/
	public String getPsw(){
		return  psw;
	}
	/**
	* 用户密码MD5加密
	*@param  psw
	*/
	public void setPsw(String psw ){
		this.psw = psw;
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
	* 最后登录时间
	*@return 
	*/
	public Date getLogintime(){
		return  logintime;
	}
	/**
	* 最后登录时间
	*@param  logintime
	*/
	public void setLogintime(Date logintime ){
		this.logintime = logintime;
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


	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
