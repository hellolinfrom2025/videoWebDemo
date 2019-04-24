sample
===
* 注释

	select #use("cols")# from core_logger  where  #use("condition")#

cols
===
	id,client_ip,uri,type,method,param_data,session_id,time,returm_time,return_data,http_status_code,time_consuming

updateSample
===
	
	id=#id#,client_ip=#clientIp#,uri=#uri#,type=#type#,method=#method#,param_data=#paramData#,session_id=#sessionId#,time=#time#,returm_time=#returmTime#,return_data=#returnData#,http_status_code=#httpStatusCode#,time_consuming=#timeConsuming#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(clientIp)){
	 and client_ip=#clientIp#
	@}
	@if(!isEmpty(uri)){
	 and uri=#uri#
	@}
	@if(!isEmpty(type)){
	 and type=#type#
	@}
	@if(!isEmpty(method)){
	 and method=#method#
	@}
	@if(!isEmpty(paramData)){
	 and param_data=#paramData#
	@}
	@if(!isEmpty(sessionId)){
	 and session_id=#sessionId#
	@}
	@if(!isEmpty(time)){
	 and time=#time#
	@}
	@if(!isEmpty(returmTime)){
	 and returm_time=#returmTime#
	@}
	@if(!isEmpty(returnData)){
	 and return_data=#returnData#
	@}
	@if(!isEmpty(httpStatusCode)){
	 and http_status_code=#httpStatusCode#
	@}
	@if(!isEmpty(timeConsuming)){
	 and time_consuming=#timeConsuming#
	@}
	
deleteByIds
====
* 批量删除操作

delete from core_logger where id in (#join(ids)#)	