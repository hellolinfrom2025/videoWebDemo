sample
===
* 注释

    select #use("cols")# from video_tag  where  #use("condition")#

cols
===
	id,video_id,video_type_id

updateSample
===
	
	id=#id#,video_id=#videoId#,video_type_id=#videoTypeId#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(videoId)){
	 and video_id=#videoId#
	@}
	@if(!isEmpty(videoTypeId)){
	 and video_type_id=#videoTypeId#
	@}
	
deleteByVideoId
===
delete from video_tag  where video_id = #id# 

getTagToEchart
===
* 获得视频分类的数量给图表

SELECT v.pid,COUNT(v.pid) `value` 
FROM video_tag vt,video_type v WHERE vt.video_type_id=v.id 
GROUP BY v.pid


getVideoTagToEchart
===
* 获得视频标签的数量给图表

SELECT v.`name`,v.pid,COUNT(video_type_id) `value` 
FROM video_tag vt,video_type v WHERE  vt.video_type_id=v.id 
GROUP BY vt.video_type_id ORDER BY pid