package org.mintleaf.modules.videowebfront.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mintleaf.modules.core.dao.CoreUserDao;
import org.mintleaf.modules.core.entity.CoreUser;
import org.mintleaf.modules.video.dao.CommentDao;
import org.mintleaf.modules.video.dao.ReplyDao;
import org.mintleaf.modules.video.dao.VideoDao;
import org.mintleaf.modules.video.entity.Comment;
import org.mintleaf.modules.video.entity.Reply;
import org.mintleaf.modules.video.entity.Video;
import org.mintleaf.modules.video.entity.VideoTag;
import org.mintleaf.modules.videowebfront.dao.RecommendedDao;
import org.mintleaf.modules.videowebfront.entity.CommentVo;
import org.mintleaf.modules.videowebfront.entity.OperationRecord;
import org.mintleaf.modules.videowebfront.entity.SecondLvCommentVo;
import org.mintleaf.modules.videowebfront.services.PlayService;
import org.mintleaf.vo.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 前台管理主控制器
 *
 * @Author: MengchuZhang
 * @Date: 2018/8/15 8:24
 * @Version 1.0
 */
@Api(tags = "前台管理主控制器", description = "描述")
@Controller
@RequestMapping("videoWebFront/play")
public class PlayController {

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
     * 进入前台播放面
     *
     * @return
     */
    @ApiOperation(value = "进入前台播放面", notes = "描述")
    @RequestMapping(value = "play.html", method = {RequestMethod.GET})
    public ModelAndView play(Integer vid) {
        //增加视频的播放量
        OperationRecord or = new OperationRecord();
        or.setVideoId(vid);
        or.setOperationType("1");
        CoreUser user = (CoreUser) session.getAttribute("user");
        if (user != null) {
            or.setUserId(user.getId());
        }
        playService.addPlayRec(or);

        ModelAndView view = new ModelAndView("modules/videowebfront/play.html");
        Video video = videoDao.single(vid);
        view.addObject("video", video);
        return view;
    }

    /**
     * 获得相似视频
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "获得相似视频", notes = "描述")
    @RequestMapping(value = "getSimilarVideo.do", method = {RequestMethod.GET})
    public ResultMsg getVideoCurrHot(Integer vid) {
        List<VideoTag> vs = recommendedDao.getSimilarVideo(vid);
        List<Video> list = new ArrayList<>();
        for (VideoTag v : vs) {
            Video video = videoDao.single(v.getVideoId());
            list.add(video);
        }
        ResultMsg result = new ResultMsg();
        result.setData(list);
        return result;
    }

    /**
     * 获得视频的评论和回复
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "获得视频的评论和回复", notes = "描述")
    @RequestMapping(value = "getComments.do", method = {RequestMethod.GET})
    public ResultMsg getComments(Integer vid) {
        List<CommentVo> commentVos = new ArrayList<>();
        Comment c = new Comment();
        c.setVideoId(vid);
        List<Comment> comms = commentDao.template(c);
        for (Comment comment : comms) {
            CoreUser user = (CoreUser) comment.getTails().get("coreUser");
            CommentVo commentVo = new CommentVo();
            commentVo.setId(comment.getId());
            commentVo.setCommentName(user.getName());
            commentVo.setCommentTime(new Date());
            commentVo.setLiked(comment.getLiked());
            commentVo.setLikeCount(comment.getLikeCount());
            commentVo.setContent(comment.getContent());
            List<SecondLvCommentVo> subComments = new ArrayList<>();
            Reply r = new Reply();
            r.setCommentId(comment.getId());
            List<Reply> replies = replyDao.template(r);
            for (Reply rp : replies) {
                SecondLvCommentVo secCommVo = new SecondLvCommentVo();
                CoreUser u = (CoreUser) rp.getTails().get("coreUser");
                secCommVo.setId(rp.getId());
                secCommVo.setReplyName(u.getName());
                CoreUser toUserName = userDao.single(rp.getToUserId());
                secCommVo.setRepliedName(toUserName.getName());
                secCommVo.setContent(rp.getContent());
                secCommVo.setCommentTime(new Date());
                secCommVo.setLiked(rp.getLiked());
                secCommVo.setLikeCount(rp.getLikeCount());
                subComments.add(secCommVo);
            }
            commentVo.setSubComments(subComments);
            commentVos.add(commentVo);
        }
        Comment comments = new Comment();
        comments.setCommentVos(commentVos);
        ResultMsg result = new ResultMsg();
        result.setData(comments);
        return result;
    }

}
