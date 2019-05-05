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