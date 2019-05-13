
getVideoCurrHot
===
* 获得最近一周的视频播放、下载、点赞和收藏量的前六

select r.operation_type,v.title,r.video_id,v.cover,count(title) value
from operation_record r,video v
where r.video_id=v.id
  and operation_type = #type#
  and DATE_SUB(CURDATE(), INTERVAL 6 DAY) <=date(time)
group by v.title,v.id,v.cover
order by value desc limit 6

getNewUploadVideo
===
* 获得最新上传的视频的前六

select *
from video v
ORDER by v.create_time desc limit 6

getSimilarVideo
===
* 获得相似视频

SELECT video_id,COUNT(*) count
FROM video_tag t
WHERE video_type_id IN (select video_type_id
from video_tag t
WHERE video_id = #vid#)
AND video_id != #vid#
GROUP BY video_id
ORDER BY count DESC LIMIT 6