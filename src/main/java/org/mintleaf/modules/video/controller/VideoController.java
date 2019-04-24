package org.mintleaf.modules.video.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.beetl.sql.core.engine.PageQuery;
import org.mintleaf.modules.video.dao.VideoDao;
import org.mintleaf.modules.video.dao.VideoTagDao;
import org.mintleaf.modules.video.dao.VideoTypeDao;
import org.mintleaf.modules.video.entity.Video;
import org.mintleaf.modules.video.entity.VideoTag;
import org.mintleaf.modules.video.entity.VideoType;
import org.mintleaf.vo.PageFrame;
import org.mintleaf.vo.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 菜单相关控制器
 *
 * @Author: mengchuzhang
 * @Date: 2018/8/22 11:15
 * @Version 1.0
 */
@Api(tags = "视频信息控制器", description = "描述")
@Controller
@RequestMapping("videoDetail")
public class VideoController {

    @Autowired
    VideoDao videoDao;
    @Autowired
    VideoTagDao videoTagDao;
    @Autowired
    VideoTypeDao videoTypeDao;

    /**
     * 进入列表页面
     * @return
     */
    @ApiOperation(value = "进入列表页面", notes = "描述")
    @RequestMapping(value = "index.html", method = {RequestMethod.GET})
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("modules/video/videodetail/index.html");
        return view;
    }

    /**
     * 进入编辑页面
     *
     * @return
     */
    @ApiOperation(value = "进入编辑页面", notes = "描述")
    @RequestMapping(value = "edit.html", method = {RequestMethod.GET})
    public ModelAndView edit() {
        ModelAndView view = new ModelAndView("modules/video/videodetail/edit.html");
        return view;
    }

    /**
     * 进入新增页面
     *
     * @return
     */
    @ApiOperation(value = "进入新增页面", notes = "描述")
    @RequestMapping(value = "add.html", method = {RequestMethod.GET})
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("modules/video/videodetail/add.html");
        return view;
    }

    /**
     * 进入视频预览页面
     *
     * @return
     */
    @ApiOperation(value = "进入视频预览页面", notes = "描述")
    @RequestMapping(value = "addTag.html", method = {RequestMethod.GET})
    public ModelAndView addTag(Video video) {
        ModelAndView view = new ModelAndView("modules/video/videodetail/play.html");
        view.addObject("videoObj",video);
        return view;
    }

    /**
     * 添加操作
     *
     * @param video
     * @return
     */
    @ApiOperation(value = "添加操作", notes = "描述")
    @RequestMapping(value = "add.do", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg add(Video video) {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        int videoId = new Random().nextInt();
        video.setCreatePerson(username);
        video.setId(videoId);
        video.setCreateTime(new Date());
        videoDao.insertTemplate(video);
        addVideoTag(videoId, video.getAreaId());
        addVideoTag(videoId, video.getCategoryId());
        addVideoTag(videoId, video.getYearId());
        ResultMsg result = new ResultMsg();
        result.setData(video);
        return result;
    }

    /**
     * 删除操作
     *
     * @param video
     * @return
     */
    @ApiOperation(value = "删除操作", notes = "描述")
    @RequestMapping(value = "delete.do", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg delete(Video video) {
        videoDao.deleteById(video.getId());
        ResultMsg result = new ResultMsg();
        return result;
    }

    /**
     * 批量删除操作
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "批量删除操作", notes = "描述")
    @RequestMapping(value = "deleteBatch.do", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg deleteBatch(String id) {
        videoDao.deleteByIds(id.split(","));
        ResultMsg result = new ResultMsg();
        return result;
    }

    /**
     * 编辑操作
     *
     * @param video
     * @return
     */
    @ApiOperation(value = "编辑操作", notes = "描述")
    @RequestMapping(value = "edit.do", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg update(Video video) {
        video.setUpdateTime(new Date());
        videoDao.updateTemplateById(video);
        //先删除视频标签关系表，再新增新的视频与类型的对应关系
        videoTagDao.deleteByVideoId(video.getId());
        addVideoTag(video.getId(), video.getAreaId());
        addVideoTag(video.getId(), video.getCategoryId());
        addVideoTag(video.getId(), video.getYearId());
        ResultMsg result = new ResultMsg();
        result.setData(video);
        return result;
    }

    /**
     * 查询单条数据操作
     *
     * @param video
     * @return
     */
    @ApiOperation(value = "查询单条数据操作", notes = "描述")
    @RequestMapping(value = "findById.json", method = {RequestMethod.POST}) //请求类型
    @ResponseBody
    public ResultMsg findById(Video video) {
        //查询视频基本信息
        Video videoDetail = videoDao.single(video.getId());
        //获取当前视频的所有标签
        VideoTag tag = new VideoTag();
        tag.setVideoId(video.getId());
        List<VideoTag> videoTags = videoTagDao.template(tag);
        //遍历标签对应的视频分类信息
        //年代
        List<VideoType> yearTypes = videoTypeDao.findChildTypeByPid(1);
        for (VideoType childType : yearTypes) {
            for (VideoTag videoTag : videoTags) {
                if (videoTag.getVideoTypeId().equals(childType.getId())) {
                    videoDetail.setYearId(videoTag.getVideoTypeId());
                }
            }
        }
        //类目
        List<VideoType> categoryTypes = videoTypeDao.findChildTypeByPid(2);
        for (VideoType childType : categoryTypes) {
            for (VideoTag videoTag : videoTags) {
                if (videoTag.getVideoTypeId().equals(childType.getId())) {
                    videoDetail.setCategoryId(videoTag.getVideoTypeId());
                }
            }
        }
        //地区
        List<VideoType> areaTypes = videoTypeDao.findChildTypeByPid(3);
        for (VideoType childType : areaTypes) {
            for (VideoTag videoTag : videoTags) {
                if (videoTag.getVideoTypeId().equals(childType.getId())) {
                    videoDetail.setAreaId(videoTag.getVideoTypeId());
                }
            }
        }
        ResultMsg result = new ResultMsg();
        result.setData(videoDetail);
        return result;
    }

    /**
     * 查询全部数据操作
     *
     * @return
     */
    @ApiOperation(value = "查询全部数据操作", notes = "描述")
    @RequestMapping(value = "all.json", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg all() {
        List<Video> videoDetails = videoDao.all();
        ResultMsg result = new ResultMsg();
        result.setData(videoDetails);
        return result;
    }

    /**
     * 查询分页数据操作
     *
     * @param video
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "查询分页数据操作", notes = "描述")
    @RequestMapping(value = "page.json", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg page(@ModelAttribute Video video, Long pageNum, Long pageSize) {
        PageQuery<Video> pageQuery = new PageQuery<>();
        pageQuery.setPageSize(Long.valueOf(pageSize));
        pageQuery.setPageNumber(Long.valueOf(pageNum));
        pageQuery.setParas(video);
        videoDao.templatePage(pageQuery);
        List<Video> videoDetails = pageQuery.getList();
        long totalPage = pageQuery.getTotalPage();
        long totalRow = pageQuery.getTotalRow();
        for (Video videoDetail : videoDetails) {
            StringBuilder tags = new StringBuilder();
            //获取当前视频的所有标签
            VideoTag tag = new VideoTag();
            tag.setVideoId(videoDetail.getId());
            List<VideoTag> videoTags = videoTagDao.template(tag);
            //遍历标签对应的视频分类信息
            for (int i = 0; i < videoTags.size(); i++) {
                VideoType type = new VideoType();
                type.setId(videoTags.get(i).getVideoTypeId());
                VideoType videoType = videoTypeDao.templateOne(type);
                tags.append(videoType.getName());
                //最后一个不加 ，
                if (i < videoTags.size() - 1) {
                    tags.append(",");
                }
            }
            videoDetail.setTagNames(tags.toString());
        }
        PageFrame pageFrame = new PageFrame();
        pageFrame.setList(videoDetails);
        pageFrame.setPageNum(Long.valueOf(pageNum));
        pageFrame.setPageSize(Long.valueOf(pageSize));
        pageFrame.setPages(totalPage);
        pageFrame.setTotal(totalRow);
        ResultMsg result = new ResultMsg();
        result.setData(pageFrame);
        return result;
    }

    /**
     * 新增视频的区域标签
     *
     * @param videoId
     * @param tagId
     */
    private void addVideoTag(int videoId, Integer tagId) {
        if (tagId != null) {
            VideoTag videoTag = new VideoTag();
            videoTag.setId(new Random().nextInt());
            videoTag.setVideoId(videoId);
            videoTag.setVideoTypeId(tagId);
            videoTagDao.insertTemplate(videoTag);
        }
    }
}
