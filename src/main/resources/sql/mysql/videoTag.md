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
