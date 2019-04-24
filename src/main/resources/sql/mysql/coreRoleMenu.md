sample
===
* 注释

	select #use("cols")# from core_role_menu  where  #use("condition")#

cols
===
	roleid,menuid,flag,creator,createtime

updateSample
===
	
	roleid=#roleid#,menuid=#menuid#,flag=#flag#,creator=#creator#,createtime=#createtime#

condition
===

	1 = 1  
	@if(!isEmpty(roleid)){
	 and roleid=#roleid#
	@}
	@if(!isEmpty(menuid)){
	 and menuid=#menuid#
	@}
	@if(!isEmpty(flag)){
	 and flag=#flag#
	@}
	@if(!isEmpty(creator)){
	 and creator=#creator#
	@}
	@if(!isEmpty(createtime)){
	 and createtime=#createtime#
	@}
	
deleteSample
===
* 根据单个用户id删除多条数据
delete from core_role_menu where roleid=#roleid#
	