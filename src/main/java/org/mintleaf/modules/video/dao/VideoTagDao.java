package org.mintleaf.modules.video.dao;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.mintleaf.modules.video.entity.VideoTag;
import org.springframework.stereotype.Component;

@Component
@SqlResource("videoTag")
public interface VideoTagDao extends BaseMapper<VideoTag> {
    Integer deleteByVideoId(@Param("id")Integer id);
}
