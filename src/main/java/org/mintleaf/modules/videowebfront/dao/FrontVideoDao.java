package org.mintleaf.modules.videowebfront.dao;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;
import org.mintleaf.modules.video.entity.Video;
import org.springframework.stereotype.Component;

@Component
@SqlResource("video")
public interface FrontVideoDao extends BaseMapper<Video> {

    public PageQuery findVdoPageByCate(int pageNumber, int pageSize, String[] typeIds,
                                       String[] countryIds, String[] yearIds, int order);
}
