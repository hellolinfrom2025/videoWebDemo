package org.mintleaf.modules.videowebfront.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.mintleaf.modules.core.dao.CoreUserDao;
import org.mintleaf.modules.core.entity.CoreUser;
import org.mintleaf.modules.video.dao.VideoDao;
import org.mintleaf.modules.video.entity.Video;
import org.mintleaf.modules.video.entity.VideoTag;
import org.mintleaf.modules.videowebfront.dao.RecommendedDao;
import org.mintleaf.vo.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * 前台管理主控制器
 * @Author: MengchuZhang
 * @Date: 2018/8/15 8:24
 * @Version 1.0
 */
@Api(tags="前台管理主控制器",description="描述")
@Controller
@RequestMapping("videoWebFront/play")
public class PlayController {

    @Autowired
    CoreUserDao userDao;
    @Autowired
    VideoDao videoDao;
    @Autowired
    RecommendedDao recommendedDao;

    /**
     * 进入前台播放面
     * @return
     */
    @ApiOperation(value="进入前台播放面", notes="描述")
    @RequestMapping(value="play.html",method = {RequestMethod.GET})
    public ModelAndView play(Integer vid){
        ModelAndView view =new ModelAndView("modules/videowebfront/play.html");
        Video video = videoDao.single(vid);
        view.addObject("video", video);
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        CoreUser user=userDao.sample(username);
        view.addObject("user", user);
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

}
