package org.mintleaf.modules.video.dao;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.mintleaf.modules.video.entity.Reply;
import org.springframework.stereotype.Component;

@Component
@SqlResource("reply")
public interface ReplyDao extends BaseMapper<Reply> {

}
