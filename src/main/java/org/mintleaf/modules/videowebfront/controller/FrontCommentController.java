package org.mintleaf.modules.videowebfront.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.beetl.sql.core.engine.PageQuery;
import org.mintleaf.modules.core.dao.CoreUserDao;
import org.mintleaf.modules.core.entity.CoreUser;
import org.mintleaf.modules.video.dao.CommentDao;
import org.mintleaf.modules.video.dao.ReplyDao;
import org.mintleaf.modules.video.dao.VideoDao;
import org.mintleaf.modules.video.entity.Comment;
import org.mintleaf.modules.video.entity.Reply;
import org.mintleaf.modules.videowebfront.dao.RecommendedDao;
import org.mintleaf.modules.videowebfront.entity.CommentVo;
import org.mintleaf.modules.videowebfront.entity.SecondLvCommentVo;
import org.mintleaf.modules.videowebfront.services.PlayService;
import org.mintleaf.vo.PageFrame;
import org.mintleaf.vo.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.mintleaf.vo.ResultMsg.ok;

/**
 * 前台评论管理主控制器
 *
 * @Author: linql
 * @Date: 2018/8/15 8:24
 * @Version 1.0
 */
@Api(tags = "前台评论管理主控制器", description = "描述")
@Controller
@RequestMapping("videoWebFront/comment")
public class FrontCommentController {

    @Autowired
    CoreUserDao userDao;
    @Autowired
    VideoDao videoDao;
    @Autowired
    RecommendedDao recommendedDao;
    @Autowired
    PlayService playService;
    @Autowired
    HttpSession session;
    @Autowired
    CommentDao commentDao;
    @Autowired
    ReplyDao replyDao;


    /**
     * 获得视频的评论和回复
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "分页获得视频的评论和回复", notes = "描述")
    @RequestMapping(value = "getComments.page", method = {RequestMethod.GET})
    public ResultMsg getComments(Integer vid,Integer pageNo, Integer pageSize) {
        List<CommentVo> commentVos = new ArrayList<>();
        Comment c = new Comment();
        c.setVideoId(vid);
        PageQuery<Comment> pageQuery = new PageQuery<>();
        pageQuery.setPageSize(Long.valueOf(pageSize));
        pageQuery.setPageNumber(Long.valueOf(pageNo));
        pageQuery.setParas(c);
        pageQuery.setOrderBy("time desc");
        commentDao.templatePage(pageQuery);
        List<Comment> comms = pageQuery.getList();
        for (Comment comment : comms) {
            CoreUser user = (CoreUser) comment.getTails().get("coreUser");
            CommentVo commentVo = new CommentVo();
            commentVo.setId(comment.getId());
            commentVo.setCommentName(user.getName());
            commentVo.setCommentId(user.getId());
            commentVo.setCommentTime(comment.getTime());
            commentVo.setLiked(comment.getLiked());
            commentVo.setLikeCount(comment.getLikeCount());
            commentVo.setContent(comment.getContent());
            List<SecondLvCommentVo> subComments = new ArrayList<>();
            Reply r = new Reply();
//            r.setCommentId(comment.getId());
            List<Reply> replies = replyDao.findByCommentId(comment.getId());
            for (Reply rp : replies) {
                SecondLvCommentVo secCommVo = new SecondLvCommentVo();
                CoreUser u = (CoreUser) rp.getTails().get("coreUser");
                secCommVo.setId(rp.getId());
                secCommVo.setReplyName(u.getName());
                CoreUser toUserName = userDao.single(rp.getToUserId());
                secCommVo.setRepliedName(toUserName.getName());
                secCommVo.setContent(rp.getContent());
                secCommVo.setCommentTime(rp.getTime());
                secCommVo.setLiked(rp.getLiked());
                secCommVo.setLikeCount(rp.getLikeCount());
                subComments.add(secCommVo);
            }
            commentVo.setSubComments(subComments);
            commentVos.add(commentVo);
        }
        Comment comments = new Comment();
        comments.setCommentVos(commentVos);
        long totalPage = pageQuery.getTotalPage();
        long totalRow = pageQuery.getTotalRow();
        PageFrame pageFrame = new PageFrame();
        pageFrame.setList(commentVos);
        pageFrame.setPageNum(Long.valueOf(pageNo));
        pageFrame.setPageSize(Long.valueOf(pageSize));
        pageFrame.setPages(totalPage);
        pageFrame.setTotal(totalRow);
        ResultMsg result = new ResultMsg();
        result.setData(pageFrame);
        return result;
    }

    /**
     * 添加回复
     * @param r 传入commentId、content、toUserId
     * @return :
     * @author : 林清流
     * @time : 2019/5/19 23:53
     */
    @ResponseBody
    @ApiOperation(value = "添加回复", notes = "描述")
    @RequestMapping(value = "addReply.do", method = {RequestMethod.POST})
    public ResultMsg addReply(@RequestBody Reply r) {
        Reply reply = new Reply();
        int replayId = new Random().nextInt();
        reply.setId(replayId);
        reply.setCommentId(r.getCommentId());
        reply.setContent(r.getContent());
        CoreUser user = (CoreUser) session.getAttribute("user");
        reply.setFromUserId(user.getId());
        reply.setToUserId(r.getToUserId());
        reply.setTime(new Date());
        replyDao.insert(reply);
        return ok();
    }

    /**
     * 添加评论
     * @param c 传入videoId、content
     * @return :
     * @author : 林清流
     * @time : 2019/5/19 23:53
     */
    @ResponseBody
    @ApiOperation(value = "添加回复", notes = "描述")
    @RequestMapping(value = "addComment.do", method = {RequestMethod.POST})
    public ResultMsg addComment( Comment c) {
        Comment comment = new Comment();
        int commentId = new Random().nextInt();
        comment.setId(commentId);
        comment.setVideoId(c.getVideoId());
        comment.setContent(c.getContent());
        CoreUser user = (CoreUser) session.getAttribute("user");
        comment.setFromUserId(user.getId());
        comment.setTime(new Date());
        comment.setLiked(false);
        comment.setLikeCount(0);
        commentDao.insert(comment);
        return ok();
    }
}
