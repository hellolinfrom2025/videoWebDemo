sample
===
* 注释

	select #use("cols")# from core_role  where  #use("condition")#

cols
===
	id,name,createtime,creator,description,updateuser,updatetime

updateSample
===
	
	id=#id#,name=#name#,createtime=#createtime#,creator=#creator#,description=#description#,updateuser=#updateuser#,updatetime=#updatetime#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(name)){
	 and name=#name#
	@}
	@if(!isEmpty(createtime)){
	 and createtime=#createtime#
	@}
	@if(!isEmpty(creator)){
	 and creator=#creator#
	@}
	@if(!isEmpty(description)){
	 and description=#description#
	@}
	@if(!isEmpty(updateuser)){
	 and updateuser=#updateuser#
	@}
	@if(!isEmpty(updatetime)){
	 and updatetime=#updatetime#
	@}
	
findRoleByIdSample
===	
* 查询用户角色

    select r.* FROM core_user u,core_role r,core_user_role ur WHERE u.id=ur.userid AND r.id=ur.roleid AND u.id=#id#
    
	
deleteByIds
====
* 批量删除操作

    delete from core_role where id in (#join(ids)#)	