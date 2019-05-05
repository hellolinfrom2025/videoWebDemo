package org.mintleaf.modules.video.dao;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.mintleaf.modules.video.entity.VideoTag;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@SqlResource("graph")
public interface GraphDao extends BaseMapper<VideoTag> {
    List<VideoTag> getTagToEchart();
    List<VideoTag> getVideoTagToEchart();
}
