sample
===
* 注释

	select #use("cols")# from core_button  where  #use("condition")#

cols
===
	id,name,menuid,permission

updateSample
===
	
	id=#id#,name=#name#,menuid=#menuid#,permission=#permission#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(name)){
	 and name=#name#
	@}
	@if(!isEmpty(menuid)){
	 and menuid=#menuid#
	@}
	@if(!isEmpty(permission)){
	 and permission=#permission#
	@}

findButtonByRoleSample
===
* 根据角色名查询按钮权限

SELECT DISTINCT
	b.*
FROM
core_role r,
	core_button b,
	core_role_button rb
WHERE
rb.buttonid=b.id
AND 
rb.roleid=r.id
AND 
r.name=#name#
	
deleteByIds
====
* 批量删除操作

delete from core_button where id in (#join(ids)#)	