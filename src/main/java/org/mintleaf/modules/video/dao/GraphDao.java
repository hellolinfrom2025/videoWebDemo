package org.mintleaf.modules.video.dao;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.mintleaf.modules.video.entity.Video;
import org.mintleaf.modules.video.entity.VideoTag;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@SqlResource("graph")
public interface GraphDao extends BaseMapper<VideoTag> {
    List<Video> getVideoCountToEchart(@Param("day")int day);
    List<Video> getVideoCountDayToEchart(@Param("day")int day);
    List<VideoTag> getTagToEchart();
    List<VideoTag> getVideoTagToEchart();
    List<Video> getVideoPlayTop();
    List<Video> getVideoDownloadTop();
    List<Video> getVideoGoodTop();
    List<Video> getVideoCollectTop();
    List<VideoTag> getVideoCurrHot(@Param("type")String type);
    List<Video> getUserCountToEchart(@Param("day")int day);
    List<Video> getUserCountDayToEchart(@Param("day")int day);
    List<Video> getUserActiveToEchart(@Param("type")String type,@Param("day")int day);
}
