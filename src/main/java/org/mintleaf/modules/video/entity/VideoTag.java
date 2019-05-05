package org.mintleaf.modules.video.entity;

import org.beetl.sql.core.TailBean;
import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;


/* 
* 
* gen by beetlsql 2019-04-11
*/
@Table(name="mintleaf_fast.video_tag")
public class VideoTag extends TailBean {
	@AssignID
	private Integer id ;
	private Integer videoId ;
	private Integer videoTypeId ;
	
	public VideoTag() {
	}
	
	public Integer getId(){
		return  id;
	}
	public void setId(Integer id ){
		this.id = id;
	}
	
	public Integer getVideoId(){
		return  videoId;
	}
	public void setVideoId(Integer videoId ){
		this.videoId = videoId;
	}
	
	public Integer getVideoTypeId(){
		return  videoTypeId;
	}
	public void setVideoTypeId(Integer videoTypeId ){
		this.videoTypeId = videoTypeId;
	}
	

}
