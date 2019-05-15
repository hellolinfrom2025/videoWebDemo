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
	
	