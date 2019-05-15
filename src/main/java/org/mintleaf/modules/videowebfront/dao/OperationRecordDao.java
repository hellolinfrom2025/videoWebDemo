package org.mintleaf.modules.videowebfront.dao;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.mintleaf.modules.video.entity.Video;
import org.mintleaf.modules.video.entity.VideoTag;
import org.mintleaf.modules.videowebfront.entity.OperationRecord;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@SqlResource("operationRecord")
public interface OperationRecordDao extends BaseMapper<OperationRecord> {
    List<VideoTag> getVideoCurrHot(@Param("type") String type);
    List<Video> getNewUploadVideo();
    List<VideoTag> getSimilarVideo(@Param("vid") Integer vid);
}
