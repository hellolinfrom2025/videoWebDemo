package org.mintleaf.modules.video.dao;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.mintleaf.modules.video.entity.Video;
import org.springframework.stereotype.Component;

@Component
@SqlResource("video")
public interface VideoDao extends BaseMapper<Video> {
    int deleteByIds(@Param("ids") String[] ids);
}
