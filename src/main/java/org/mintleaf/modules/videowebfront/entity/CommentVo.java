package org.mintleaf.modules.videowebfront.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * TODO
 *
 * @author by 15919
 * @createDate 2019/5/16 2:24
 */
@Data
public class CommentVo {
    private Integer id;
    private Integer commentId;
    private String commentName;
    private String content;
    private Date commentTime;
    private Boolean liked;
    private Integer likeCount;
    private List<SecondLvCommentVo> subComments;
}


