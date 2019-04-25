package org.mintleaf.modules.video.entity;

import org.beetl.sql.core.TailBean;
import org.beetl.sql.core.annotatoin.Table;
import org.beetl.sql.core.orm.OrmCondition;
import org.beetl.sql.core.orm.OrmQuery;
import org.mintleaf.modules.core.entity.CoreUser;

import java.util.Date;


/*
 *
 * gen by beetlsql 2019-04-25
 */
@OrmQuery(
        value = {
                @OrmCondition(target = Comment.class, attr = "commentId", targetAttr = "id", type = OrmQuery.Type.ONE),
                @OrmCondition(target = CoreUser.class, attr = "fromUserId", targetAttr = "id", type = OrmQuery.Type.ONE),
        }
)
@Table(name = "mintleaf_fast.reply")
public class Reply extends TailBean {

    /*
    主键
    */
    private Integer id;
    /*
    评论ID
    */
    private Integer commentId;
    /*
    回复用户ID
    */
    private Integer fromUserId;
    /*
    回复目标ID
    */
    private Integer replyId;
    /*
    目标用户ID
    */
    private Integer toUserId;
    /*
    回复内容
    */
    private String content;
    /*
    回复类型
    */
    private String replyType;
    private Date time;
    //VO字段
    private String commentCont;
    private String fromUserName;
    private String toUserName;


    public Reply() {
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
     * 评论ID
     *
     * @return
     */
    public Integer getCommentId() {
        return commentId;
    }

    /**
     * 评论ID
     *
     * @param commentId
     */
    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    /**
     * 回复用户ID
     *
     * @return
     */
    public Integer getFromUserId() {
        return fromUserId;
    }

    /**
     * 回复用户ID
     *
     * @param fromUserId
     */
    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    /**
     * 回复目标ID
     *
     * @return
     */
    public Integer getReplyId() {
        return replyId;
    }

    /**
     * 回复目标ID
     *
     * @param replyId
     */
    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    /**
     * 目标用户ID
     *
     * @return
     */
    public Integer getToUserId() {
        return toUserId;
    }

    /**
     * 目标用户ID
     *
     * @param toUserId
     */
    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    /**
     * 回复内容
     *
     * @return
     */
    public String getContent() {
        return content;
    }

    /**
     * 回复内容
     *
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 回复类型
     *
     * @return
     */
    public String getReplyType() {
        return replyType;
    }

    /**
     * 回复类型
     *
     * @param replyType
     */
    public void setReplyType(String replyType) {
        this.replyType = replyType;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getCommentCont() {
        return commentCont;
    }

    public void setCommentCont(String commentCont) {
        this.commentCont = commentCont;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }
}
