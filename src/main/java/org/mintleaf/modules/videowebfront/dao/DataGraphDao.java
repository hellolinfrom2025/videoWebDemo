package org.mintleaf.modules.videowebfront.dao;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.mintleaf.modules.video.entity.Video;
import org.mintleaf.modules.video.entity.VideoTag;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@SqlResource("graph")
public interface DataGraphDao extends BaseMapper<VideoTag> {

    List<Video> getOneUserActiveToEchart( String type,  int day,int uid);
    List<VideoTag> getUserPlayCountToEchart( int uid);
    List<VideoTag> getUserVideoTypeToEchart( int oType,int uid,int vPid);
}
