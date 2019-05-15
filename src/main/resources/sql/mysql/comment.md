sample
===
* 注释

	select #use("cols")# from comment  where  #use("condition")#

cols
===
	id,video_id,content,from_user_id,time,liked,likedCount

updateSample
===
	
	id=#id#,video_id=#videoId#,content=#content#,from_user_id=#fromUserId#,time=#time#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(videoId)){
	 and video_id=#videoId#
	@}
	@if(!isEmpty(content)){
	 and content=#content#
	@}
	@if(!isEmpty(fromUserId)){
	 and from_user_id=#fromUserId#
	@}
	@if(!isEmpty(time)){
	 and time=#time#
	@}
	
deleteByIds
====
* 批量删除操作

delete from comment where id in (#join(ids)#)
	