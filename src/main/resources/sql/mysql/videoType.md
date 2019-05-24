sample
===
* 注释

	select #use("cols")# from video_type  where  #use("condition")#

cols
===
	id,pid,name,create_person,create_time,update_time

updateSample
===
	
	id=#id#,pid=#pid#,name=#name#,create_person=#createPerson#,create_time=#createTime#,update_time=#updateTime#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(pid)){
	 and pid=#pid#
	@}
	@if(!isEmpty(name)){
	 and name=#name#
	@}
	@if(!isEmpty(createPerson)){
	 and create_person=#createPerson#
	@}
	@if(!isEmpty(createTime)){
	 and create_time=#createTime#
	@}
	@if(!isEmpty(updateTime)){
	 and update_time=#updateTime#
	@}
	
findChildTypeByPid
===
select #use("cols")# from video_type  where  pid=#pid#

findByNameAndPid
===
select #use("cols")# from video_type  where  pid = #pid#
@if(!isEmpty(name)){
and name = #name# 
@}
