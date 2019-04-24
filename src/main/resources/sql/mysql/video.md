sample
===
* 注释

	select #use("cols")# from video  where  #use("condition")#

cols
===
	id,cover,title,desc,url,play_count,good_count,download_count,collect_count,grade,remark,create_person,create_time,update_time

updateSample
===
	
	id=#id#,cover=#cover#,title=#title#,desc=#desc#,url=#url#,video_tag_id=#videoTagId#,play_count=#playCount#,good_count=#goodCount#,download_count=#downloadCount#,collect_count=#collectCount#,grade=#grade#,remark=#remark#,create_person=#createPerson#,create_time=#createTime#,update_time=#updateTime#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(cover)){
	 and cover=#cover#
	@}
	@if(!isEmpty(title)){
	 and title=#title#
	@}
	@if(!isEmpty(desc)){
	 and desc=#desc#
	@}
	@if(!isEmpty(url)){
	 and url=#url#
	@}
	@if(!isEmpty(playCount)){
	 and play_count=#playCount#
	@}
	@if(!isEmpty(goodCount)){
	 and good_count=#goodCount#
	@}
	@if(!isEmpty(downloadCount)){
	 and download_count=#downloadCount#
	@}
	@if(!isEmpty(collectCount)){
	 and collect_count=#collectCount#
	@}
	@if(!isEmpty(grade)){
	 and grade=#grade#
	@}
	@if(!isEmpty(remark)){
	 and remark=#remark#
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
	
deleteByIds
====
* 批量删除操作

delete from video where id in (#join(ids)#)

findById
===
SELECT #use("cols")#,t.area,t.category,t.`year` FROM video v LEFT JOIN video_tag  t ON v.video_tag_id=t.id