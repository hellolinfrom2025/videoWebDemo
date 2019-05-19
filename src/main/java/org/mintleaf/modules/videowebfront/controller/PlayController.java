package org.mintleaf.modules.videowebfront.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mintleaf.modules.core.dao.CoreUserDao;
import org.mintleaf.modules.core.entity.CoreUser;
import org.mintleaf.modules.video.dao.CommentDao;
import org.mintleaf.modules.video.dao.ReplyDao;
import org.mintleaf.modules.video.dao.VideoDao;
import org.mintleaf.modules.video.entity.Video;
import org.mintleaf.modules.videowebfront.dao.RecommendedDao;
import org.mintleaf.modules.videowebfront.entity.OperationRecord;
import org.mintleaf.modules.videowebfront.services.PlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * 前台播放管理主控制器
 *
 * @Author: linql
 * @Date: 2018/8/15 8:24
 * @Version 1.0
 */
@Api(tags = "前台播放管理主控制器", description = "描述")
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
     * 进入前台播放面且视频播放次数+1
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

}
