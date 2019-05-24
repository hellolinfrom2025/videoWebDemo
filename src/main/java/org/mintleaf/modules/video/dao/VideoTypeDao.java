package org.mintleaf.modules.video.dao;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.mintleaf.modules.video.entity.VideoType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@SqlResource("videoType")
public interface VideoTypeDao extends BaseMapper<VideoType> {
    List<VideoType> findChildTypeByPid(@Param("pid") Integer pid);
    List<VideoType> findByNameAndPid(String name,int pid);
}
