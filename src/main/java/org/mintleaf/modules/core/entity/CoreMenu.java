package org.mintleaf.modules.core.entity;

import org.beetl.sql.core.annotatoin.Table;

import java.util.Date;


/* 
* 
* gen by beetlsql 2018-08-20
*/
@Table(name="mintleaf_fast.core_menu")
public class CoreMenu   {
	
	/*
	主键ID
	*/
	private Integer id ;
	/*
	创建者id，0为超级管理员
	*/
	private Integer creator ;
	/*
	显示排序
	*/
	private Integer display ;
	/*
	父级的id，引用本表id字段
	*/
	private Integer parentid ;
	/*
	更新者id
	*/
	private Integer updateuser ;
	/*
	是否启用，0 禁用，1启用
	*/
	private String flag ;
	/*
	显示的图标
	*/
	private String icon ;
	/*
	类型，0 菜单，1 连接网址,2 隐藏连接
	*/
	private String menutype ;
	/*
	菜单名称
	*/
	private String name ;
	/*
	网址
	*/
	private String url ;
	/*
	创建时间
	*/
	private Date createtime ;
	/*
	更新时间
	*/
	private Date updatetime ;
	/*
权限代码
*/
	private String permission ;


	
	public CoreMenu() {
	}
	
	/**
	* 主键ID
	*@return 
	*/
	public Integer getId(){
		return  id;
	}
	/**
	* 主键ID
	*@param  id
	*/
	public void setId(Integer id ){
		this.id = id;
	}
	
	/**
	* 创建者id，0为超级管理员
	*@return 
	*/
	public Integer getCreator(){
		return  creator;
	}
	/**
	* 创建者id，0为超级管理员
	*@param  creator
	*/
	public void setCreator(Integer creator ){
		this.creator = creator;
	}
	
	/**
	* 显示排序
	*@return 
	*/
	public Integer getDisplay(){
		return  display;
	}
	/**
	* 显示排序
	*@param  display
	*/
	public void setDisplay(Integer display ){
		this.display = display;
	}
	
	/**
	* 父级的id，引用本表id字段
	*@return 
	*/
	public Integer getParentid(){
		return  parentid;
	}
	/**
	* 父级的id，引用本表id字段
	*@param  parentid
	*/
	public void setParentid(Integer parentid ){
		this.parentid = parentid;
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
	* 是否启用，0 禁用，1启用
	*@return 
	*/
	public String getFlag(){
		return  flag;
	}
	/**
	* 是否启用，0 禁用，1启用
	*@param  flag
	*/
	public void setFlag(String flag ){
		this.flag = flag;
	}
	
	/**
	* 显示的图标
	*@return 
	*/
	public String getIcon(){
		return  icon;
	}
	/**
	* 显示的图标
	*@param  icon
	*/
	public void setIcon(String icon ){
		this.icon = icon;
	}
	
	/**
	* 类型，0 菜单，1 连接网址,2 隐藏连接
	*@return 
	*/
	public String getMenutype(){
		return  menutype;
	}
	/**
	* 类型，0 菜单，1 连接网址,2 隐藏连接
	*@param  menutype
	*/
	public void setMenutype(String menutype ){
		this.menutype = menutype;
	}
	
	/**
	* 菜单名称
	*@return 
	*/
	public String getName(){
		return  name;
	}
	/**
	* 菜单名称
	*@param  name
	*/
	public void setName(String name ){
		this.name = name;
	}
	
	/**
	* 网址
	*@return 
	*/
	public String getUrl(){
		return  url;
	}
	/**
	* 网址
	*@param  url
	*/
	public void setUrl(String url ){
		this.url = url;
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
	/**
	 * 权限代码
	 *@return
	 */
	public String getPermission(){
		return  permission;
	}
	/**
	 * 权限代码
	 *@param  permission
	 */
	public void setPermission(String permission ){
		this.permission = permission;
	}
	

}
