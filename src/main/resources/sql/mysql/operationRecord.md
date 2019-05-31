sample
===
* 注释

	select #use("cols")# from operation_record  where  #use("condition")#

cols
===
	id,user_id,video_id,operation_type,operation_times,time

updateSample
===
	
	id=#id#,user_id=#userId#,video_id=#videoId#,operation_type=#operationType#,operation_times=#operationTimes#,time=#time#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(userId)){
	 and user_id=#userId#
	@}
	@if(!isEmpty(videoId)){
	 and video_id=#videoId#
	@}
	@if(!isEmpty(operationType)){
	 and operation_type=#operationType#
	@}
	@if(!isEmpty(operationTimes)){
	 and operation_times=#operationTimes#
	@}
	@if(!isEmpty(time)){
	 and time=#time#
	@}
	
getCollectVidByUId
===
* 获取用户收藏的视频

SELECT v.id,v.title,r.time 
FROM operation_record r 
LEFT JOIN video v ON r.video_id = v.id 
WHERE r.user_id = #uid#
AND r.operation_type=3