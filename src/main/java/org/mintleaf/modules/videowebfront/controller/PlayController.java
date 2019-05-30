package org.mintleaf.modules.videowebfront.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mintleaf.modules.core.dao.CoreUserDao;
import org.mintleaf.modules.core.entity.CoreUser;
import org.mintleaf.modules.video.dao.VideoDao;
import org.mintleaf.modules.video.entity.Video;
import org.mintleaf.modules.videowebfront.entity.OperationRecord;
import org.mintleaf.modules.videowebfront.services.PlayService;
import org.mintleaf.vo.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static org.mintleaf.vo.ResultMsg.ok;

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
    PlayService playService;
    @Autowired
    HttpSession session;

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

    /**
     * 用户下载视频
     *
     * @return
     */
    @ApiOperation(value = "用户下载视频", notes = "描述")
    @RequestMapping(value = "download.do", method = {RequestMethod.GET})
    @ResponseBody
    public ResultMsg download(Integer vid) {
        //新增用户操作记录-新增视频
        OperationRecord or = new OperationRecord();
        or.setVideoId(vid);
        or.setOperationType("2");
        CoreUser user = (CoreUser) session.getAttribute("user");
        or.setUserId(user.getId());
        playService.addDownloadRec(or);
        return ok();
    }
    /**
     * 用户收藏视频
     *
     * @return
     */
    @ApiOperation(value = "用户收藏视频", notes = "描述")
    @RequestMapping(value = "collect.do", method = {RequestMethod.GET})
    @ResponseBody
    public ResultMsg collect(Integer vid) {
        //新增用户操作记录-新增视频
        OperationRecord or = new OperationRecord();
        or.setVideoId(vid);
        or.setOperationType("4");
        CoreUser user = (CoreUser) session.getAttribute("user");
        or.setUserId(user.getId());
        boolean isCollect = playService.addCollectRec(or);
        String msg = isCollect ? "收藏成功":"取消收藏";
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setMsg(msg);
        return resultMsg;
    }
}
