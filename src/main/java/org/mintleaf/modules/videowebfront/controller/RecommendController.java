package org.mintleaf.modules.videowebfront.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

import java.util.ArrayList;
import java.util.List;

/**
 * 前台推荐主控制器
 * @Author: linql
 * @Date: 2018/8/15 8:24
 * @Version 1.0
 */
@Api(tags="前台推荐主控制器",description="描述")
@Controller
@RequestMapping("videoWebFront/recommend")
public class RecommendController {

    @Autowired
    RecommendedDao recommendedDao;
    @Autowired
    VideoDao videoDao;

    /**
     * 获得最近一周的视频播放量的前六
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "获得最近一周的视频播放量的前六", notes = "描述")
    @RequestMapping(value = "getHotVideo.do", method = {RequestMethod.GET})
    public ResultMsg getVideoCurrHot() {
        List<VideoTag> videos = recommendedDao.getVideoCurrHot("1");
        List<Video> list = new ArrayList<>();
        for (VideoTag video : videos) {
            Video v = new Video();
            v.setTitle(video.getTails().get("title").toString());
            v.setCover(video.getTails().get("cover").toString());
            v.setId(video.getVideoId());
            v.setPlayCount((Integer.valueOf(video.getTails().get("value").toString())));
            list.add(v);
        }
        ResultMsg result = new ResultMsg();
        result.setData(list);
        return result;
    }

    /**
     * 获得最新上传的视频的前六
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "获得最新上传的视频的前六", notes = "描述")
    @RequestMapping(value = "getNewUploadVideo.do", method = {RequestMethod.GET})
    public ResultMsg getNewUploadVideo() {
        List<Video> videos = recommendedDao.getNewUploadVideo();
        List<Video> list = new ArrayList<>();
        for (Video video : videos) {
            Video v = new Video();
            v.setTitle(video.getTitle());
            v.setCover(video.getCover());
            v.setId(video.getId());
            v.setPlayCount(video.getPlayCount());
            list.add(v);
        }
        ResultMsg result = new ResultMsg();
        result.setData(list);
        return result;
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
