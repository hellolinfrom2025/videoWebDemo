package org.mintleaf.modules.video.entity;

import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;

import java.util.Date;


/* 
* 
* gen by beetlsql 2019-04-07
*/
@Table(name="mintleaf_fast.video_type")
public class VideoType   {
	@AssignID
	private Integer id ;
	private Integer pid ;
	private String createPerson ;
	private String name ;
	private Date createTime ;
	private Date updateTime ;
	
	public VideoType() {
	}
	
	public Integer getId(){
		return  id;
	}
	public void setId(Integer id ){
		this.id = id;
	}
	
	public Integer getPid(){
		return  pid;
	}
	public void setPid(Integer pid ){
		this.pid = pid;
	}
	
	public String getCreatePerson(){
		return  createPerson;
	}
	public void setCreatePerson(String createPerson ){
		this.createPerson = createPerson;
	}
	
	public String getName(){
		return  name;
	}
	public void setName(String name ){
		this.name = name;
	}
	
	public Date getCreateTime(){
		return  createTime;
	}
	public void setCreateTime(Date createTime ){
		this.createTime = createTime;
	}
	
	public Date getUpdateTime(){
		return  updateTime;
	}
	public void setUpdateTime(Date updateTime ){
		this.updateTime = updateTime;
	}
	

}
