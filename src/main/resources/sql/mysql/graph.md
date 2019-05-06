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