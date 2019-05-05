package org.mintleaf.modules.video.dao;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.mintleaf.modules.video.entity.Comment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@SqlResource("comment")
public interface CommentDao extends BaseMapper<Comment> {
    /**
     * 获得评论
     * @return
     */
    List<Comment> getComment();

    /**
     * 根据ID批量删除评论
     * @param ids
     * @return
     */
    int deleteByIds(@Param("ids") String[] ids);
}
