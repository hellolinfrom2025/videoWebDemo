getVideoCountToEchart
===
* 获得当前日期的某天前的视频总数量给图表

select title from video 
where date_sub(curdate(),interval #day# day)>=date(create_time)

getVideoCountDayToEchart
===
* 获得当前日期的视频日增长量给图表

select title from video 
where date_sub(curdate(),interval #day# day)=date(create_time)

getTagToEchart
===
* 获得视频分类的数量给图表

SELECT v.pid,COUNT(v.pid) `value` 
FROM video_tag vt,video_type v WHERE vt.video_type_id=v.id 
GROUP BY v.pid


getVideoTagToEchart
===
* 获得视频标签的数量给图表

SELECT v.`name`,v.pid,COUNT(video_type_id) `value` 
FROM video_tag vt,video_type v WHERE  vt.video_type_id=v.id 
GROUP BY vt.video_type_id ORDER BY pid

getVideoPlayTop
===
* 获得视频播放总量的前六给echart

select title,play_count from video  order by play_count desc limit 6

getVideoDownloadTop
===
* 获得视频下载总量的前六给echart

select title,download_count from video  order by download_count desc limit 6

getVideoGoodTop
===
* 获得视频点赞总量的前六给echart

select title,good_count from video  order by good_count desc limit 6

getVideoCollectTop
===
* 获得视频收藏总量的前六给echart

select title,collect_count from video  order by collect_count desc limit 6

getVideoCurrHot
===
* 获得最近一周的视频播放、下载、点赞和收藏量的前六给echart

select r.operation_type,v.title,r.video_id,v.cover,count(title) value
from operation_record r,video v
where r.video_id=v.id
  and operation_type = #type#
  and DATE_SUB(CURDATE(), INTERVAL 6 DAY) <=date(time)
group by v.title,v.id,v.cover
order by value desc limit 6

getUserCountToEchart
===
* 获得当前日期的某天前的用户总数量给图表

select name from core_user 
where date_sub(curdate(),interval #day# day)>=date(createtime)

getUserCountDayToEchart
===
* 后台
* 获得当前日期的某天前的用户日增长数量给图表

select name from core_user 
where date_sub(curdate(),interval #day# day)=date(createtime)

getUserActiveToEchart
===
* 后台
* 获得当前日期的某天前的用户操作类型的数量给图表

select id from operation_record 
where operation_type=#type# 
and date_sub(curdate(),interval #day# day)=date(time)

getOneUserActiveToEchart
===
* 前台
* 获得当前日期的某天前的用户操作类型的数量给图表

select id from operation_record 
where operation_type=#type# 
and date_sub(curdate(),interval #day# day)=date(time)
and user_id= #uid#

getUserPlayCountToEchart
===
* 前台
* 获得用户播放量最多的前六数量给图表

select o.id,v.title,o.operation_times 
from operation_record o 
LEFT JOIN video v ON o.video_id=v.id
where operation_type=1
and user_id= #uid#
order by operation_times desc limit 6

getUserVideoTypeToEchart
===
* 前台
* 获得用户（播放、下载、点赞、收藏）数量最多的视频标签给图表

SELECT tag.video_type_id,type.`name`,COUNT(*) `value`
FROM video_tag tag
LEFT JOIN video_type type ON tag.video_type_id = type.id
WHERE video_id IN (
	select o.video_id from operation_record o 
	where operation_type=#oType#
	and user_id= #uid#)
AND type.pid=#vPid#
GROUP BY tag.video_type_id
ORDER BY `value` DESC



