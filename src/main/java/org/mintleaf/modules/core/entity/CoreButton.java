package org.mintleaf.modules.core.entity;

import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;


/*
 *
 * gen by beetlsql 2018-09-03
 */
@Table(name="mintleaf_fast.core_button")
public class CoreButton   {
	@AssignID
	private Integer id ;
	/*
	菜单id
	*/
	private Integer menuid ;
	/*
	名称
	*/
	private String name ;
	/*
	权限代码
	*/
	private String permission ;

	public CoreButton() {
	}

	public Integer getId(){
		return  id;
	}
	public void setId(Integer id ){
		this.id = id;
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
	 * 名称
	 *@return
	 */
	public String getName(){
		return  name;
	}
	/**
	 * 名称
	 *@param  name
	 */
	public void setName(String name ){
		this.name = name;
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
