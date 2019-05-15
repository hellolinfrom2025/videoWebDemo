package org.mintleaf.modules.videowebfront.entity;

import lombok.Data;

import java.util.Date;

/**
 * TODO
 *
 * @author by 15919
 * @createDate 2019/5/16 2:24
 */
@Data
public class SecondLvCommentVo {
    private Integer id;
    private String replyName;
    private String repliedName;
    private String content;
    private Date commentTime;
    private Boolean liked;
    private Integer likeCount;
}


