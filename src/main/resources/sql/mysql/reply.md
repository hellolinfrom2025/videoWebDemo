sample
===
* 注释

	select #use("cols")# from reply  where  #use("condition")#

cols
===
	id,comment_id,reply_id,reply_type,content,from_user_id,to_user_id,time

updateSample
===
	
	id=#id#,comment_id=#commentId#,reply_id=#replyId#,reply_type=#replyType#,content=#content#,from_user_id=#fromUserId#,to_user_id=#toUserId#,time=#time#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(commentId)){
	 and comment_id=#commentId#
	@}
	@if(!isEmpty(replyId)){
	 and reply_id=#replyId#
	@}
	@if(!isEmpty(replyType)){
	 and reply_type=#replyType#
	@}
	@if(!isEmpty(content)){
	 and content=#content#
	@}
	@if(!isEmpty(fromUserId)){
	 and from_user_id=#fromUserId#
	@}
	@if(!isEmpty(toUserId)){
	 and to_user_id=#toUserId#
	@}
	@if(!isEmpty(time)){
	 and time=#time#
	@}
	
deleteByCommId
===
* 根据评论ID删除回复

    delete from reply where comment_id = #commentId#
    
deleteByCommIds
====
* 批量删除操作

delete from reply where comment_id in (#join(ids)#)	