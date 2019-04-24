sample
===
* 注释

	select #use("cols")# from core_user_role  where  #use("condition")#

cols
===
	userid,roleid,creator,createtime

updateSample
===
	
	userid=#userid#,roleid=#roleid#,creator=#creator#,createtime=#createtime#

condition
===

	1 = 1  
	@if(!isEmpty(userid)){
	 and userid=#userid#
	@}
	@if(!isEmpty(roleid)){
	 and roleid=#roleid#
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
delete from core_user_role where userid=#userid#


deleteByIds
====
* 根据多个用户id删除多条数据

    delete from core_user_role where userid in (#join(ids)#)	