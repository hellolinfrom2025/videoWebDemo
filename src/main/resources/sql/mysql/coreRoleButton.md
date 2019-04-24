sample
===
* 注释

	select #use("cols")# from core_role_button  where  #use("condition")#

cols
===
	roleid,buttonid,flag,createtime

updateSample
===
	
	roleid=#roleid#,buttonid=#buttonid#,flag=#flag#,createtime=#createtime#

condition
===

	1 = 1  
	@if(!isEmpty(roleid)){
	 and roleid=#roleid#
	@}
	@if(!isEmpty(buttonid)){
	 and buttonid=#buttonid#
	@}
	@if(!isEmpty(flag)){
	 and flag=#flag#
	@}
	@if(!isEmpty(createtime)){
	 and createtime=#createtime#
	@}
	
deleteSample
===
* 根据单个用户id删除多条数据
delete from core_role_button where roleid=#roleid#	