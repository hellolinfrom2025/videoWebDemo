package org.mintleaf.modules.video.entity;

import org.beetl.sql.core.TailBean;
import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;
import org.beetl.sql.core.orm.OrmCondition;
import org.beetl.sql.core.orm.OrmQuery;
import org.mintleaf.modules.core.entity.CoreUser;
import org.mintleaf.modules.videowebfront.entity.CommentVo;

import java.util.Date;
import java.util.List;


/*
 *
 * gen by beetlsql 2019-04-23
 */
@OrmQuery(
        value = {
                @OrmCondition(target = CoreUser.class, attr = "fromUserId", targetAttr = "id", type = OrmQuery.Type.ONE, lazy = false),
                @OrmCondition(target = Video.class, attr = "videoId", targetAttr = "id", type = OrmQuery.Type.ONE),
        }
)
@Table(name = "mintleaf_fast.comment")
public class Comment extends TailBean {

    /*
    主键
    */
    @AssignID
    private Integer id;
    /*
    评论用户的ID
    */
    private Integer fromUserId;
    /*
    视频ID
    */
    private Integer videoId;
    /*
    评论内容
    */
    private String content;
    private Date time;
    private Boolean liked;
    private Integer likeCount;
    //VO字段
    private List<CommentVo> commentVos;
    private String videoTitle;
    private String videoDesc;
    private String userName;

    public Comment() {
    }

    /**
     * 主键
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 评论用户的ID
     *
     * @return
     */
    public Integer getFromUserId() {
        return fromUserId;
    }

    /**
     * 评论用户的ID
     *
     * @param fromUserId
     */
    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    /**
     * 视频ID
     *
     * @return
     */
    public Integer getVideoId() {
        return videoId;
    }

    /**
     * 视频ID
     *
     * @param videoId
     */
    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    /**
     * 评论内容
     *
     * @return
     */
    public String getContent() {
        return content;
    }

    /**
     * 评论内容
     *
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoDesc() {
        return videoDesc;
    }

    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<CommentVo> getCommentVos() { return commentVos; }

    public void setCommentVos(List<CommentVo> commentVos) { this.commentVos = commentVos; }

    public Boolean getLiked() { return liked; }

    public void setLiked(Boolean liked) { this.liked = liked; }

    public Integer getLikeCount() { return likeCount; }

    public void setLikeCount(Integer likeCount) { this.likeCount = likeCount; }
}
