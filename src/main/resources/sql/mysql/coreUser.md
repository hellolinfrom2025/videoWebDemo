sample
===
* 注释

	select #use("cols")# from core_user  where  #use("condition")#

cols
===
	id,tenantid,name,psw,email,creator,createtime,flag,logintime,updateuser,updatetime

updateSample
===
	
	id=#id#,tenantid=#tenantid#,name=#name#,psw=#psw#,email=#email#,creator=#creator#,createtime=#createtime#,flag=#flag#,logintime=#logintime#,updateuser=#updateuser#,updatetime=#updatetime#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(tenantid)){
	 and tenantid=#tenantid#
	@}
	@if(!isEmpty(name)){
	 and name=#name#
	@}
	@if(!isEmpty(psw)){
	 and psw=#psw#
	@}
	@if(!isEmpty(email)){
	 and email=#email#
	@}
	@if(!isEmpty(creator)){
	 and creator=#creator#
	@}
	@if(!isEmpty(createtime)){
	 and createtime=#createtime#
	@}
	@if(!isEmpty(flag)){
	 and flag=#flag#
	@}
	@if(!isEmpty(logintime)){
	 and logintime=#logintime#
	@}
	@if(!isEmpty(updateuser)){
	 and updateuser=#updateuser#
	@}
	@if(!isEmpty(updatetime)){
	 and updatetime=#updatetime#
	@}
	
deleteByIds
====
* 批量删除操作

    delete from core_user where id in (#join(ids)#)	