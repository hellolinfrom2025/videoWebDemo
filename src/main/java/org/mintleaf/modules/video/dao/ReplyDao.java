package org.mintleaf.modules.video.dao;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.mintleaf.modules.video.entity.Reply;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@SqlResource("reply")
public interface ReplyDao extends BaseMapper<Reply> {

    int deleteByCommId(@Param("commentId") Integer commentId);
    int deleteByCommIds(@Param("ids") String[] ids);
    List<Reply> findByCommentId(@Param("commentId") Integer commentId);
}
