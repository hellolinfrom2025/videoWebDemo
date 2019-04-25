sample
===
* 注释

	select #use("cols")# from core_menu  where  #use("condition")#

cols
===
	id,name,url,icon,menutype,display,parentid,creator,createtime,updateuser,updatetime,flag,permission

updateSample
===
	
	id=#id#,name=#name#,url=#url#,icon=#icon#,menutype=#menutype#,display=#display#,parentid=#parentid#,creator=#creator#,createtime=#createtime#,updateuser=#updateuser#,updatetime=#updatetime#,flag=#flag#,permission=#permission#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(name)){
	 and name=#name#
	@}
	@if(!isEmpty(url)){
	 and url=#url#
	@}
	@if(!isEmpty(icon)){
	 and icon=#icon#
	@}
	@if(!isEmpty(menutype)){
	 and menutype=#menutype#
	@}
	@if(!isEmpty(display)){
	 and display=#display#
	@}
	@if(!isEmpty(parentid)){
	 and parentid=#parentid#
	@}
	@if(!isEmpty(creator)){
	 and creator=#creator#
	@}
	@if(!isEmpty(createtime)){
	 and createtime=#createtime#
	@}
	@if(!isEmpty(updateuser)){
	 and updateuser=#updateuser#
	@}
	@if(!isEmpty(updatetime)){
	 and updatetime=#updatetime#
	@}
	@if(!isEmpty(flag)){
	 and flag=#flag#
	@}
	@if(!isEmpty(permission)){
	 and permission=#permission#
	@}
	
findMenuByNameSample
===
* 查询用户菜单数据

SELECT DISTINCT
	m.*
FROM
	core_menu m,
	core_user u,
	core_role_menu rm,
	core_user_role ur
WHERE
	m.id = rm.menuid
AND ur.roleid = rm.roleid
AND ur.userid = u.id
AND u.name = #name# 
ORDER BY parentid , display 


findMenuByRoleSample
===
* 根据角色名查询菜单权限

SELECT DISTINCT
	m.*
FROM
core_role r,
	core_menu m,
	core_role_menu rm
WHERE
rm.menuid=m.id
AND 
rm.roleid=r.id
AND 
r.name=#name#





selectPage
===
* 查询分页数据

select
@pageTag(){
m.*
@}
from core_menu m  where  #use("condition")#


deleteByIds
====
* 批量删除操作

delete from core_menu where id in (#join(ids)#)

