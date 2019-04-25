package org.mintleaf.modules.video.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.beetl.sql.core.engine.PageQuery;
import org.mintleaf.modules.core.dao.CoreUserDao;
import org.mintleaf.modules.core.entity.CoreUser;
import org.mintleaf.modules.video.dao.CommentDao;
import org.mintleaf.modules.video.dao.ReplyDao;
import org.mintleaf.modules.video.dao.VideoDao;
import org.mintleaf.modules.video.entity.Comment;
import org.mintleaf.modules.video.entity.Reply;
import org.mintleaf.modules.video.entity.Video;
import org.mintleaf.vo.PageFrame;
import org.mintleaf.vo.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 角色相关控制器
 *
 * @Author: Lin
 * @Date: 2018/8/23 15:38
 * @Version 1.0
 */
@Api(tags = "视频评论控制器", description = "描述")
@Controller
@RequestMapping("comment")
public class CommentController {
    @Autowired
    CommentDao commentDao;
    @Autowired
    ReplyDao replyDao;
    @Autowired
    CoreUserDao userDao;
    @Autowired
    VideoDao videoDao;

    /**
     * 进入列表页面
     *
     * @return
     */
    @ApiOperation(value = "进入列表页面", notes = "描述")
    @RequestMapping(value = "index.html", method = {RequestMethod.GET})
    public ModelAndView index() {
        return new ModelAndView("modules/video/comment/index.html");
    }

    /**
     * 进入新增页面
     *
     * @return
     */
    @RequiresPermissions("comment:add")
    @ApiOperation(value = "进入新增页面", notes = "描述")
    @RequestMapping(value = "add.html", method = {RequestMethod.GET})
    public ModelAndView add() {
        return new ModelAndView("modules/video/comment/add.html");
    }

    /**
     * 分页获得评论数据
     *
     * @param comm
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "分页获得评论数据", notes = "描述")
    @RequestMapping(value = "page.json", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg page(@ModelAttribute Comment comm, Long pageNum, Long pageSize) {
        PageQuery<Comment> pageQuery = new PageQuery<>();
        pageQuery.setPageSize(Long.valueOf(pageSize));
        pageQuery.setPageNumber(Long.valueOf(pageNum));
        pageQuery.setParas(comm);
        commentDao.templatePage(pageQuery);
        List<Comment> comms = pageQuery.getList();
        for (Comment comment : comms) {
            Video video = (Video) comment.getTails().get("video");
            CoreUser user = (CoreUser) comment.getTails().get("coreUser");
            comment.setVideoTitle(video.getTitle());
            comment.setVideoDesc(video.getDesc());
            comment.setUserName(user.getName());
        }
        long totalPage = pageQuery.getTotalPage();
        long totalRow = pageQuery.getTotalRow();
        PageFrame pageFrame = new PageFrame();
        pageFrame.setList(comms);
        pageFrame.setPageNum(Long.valueOf(pageNum));
        pageFrame.setPageSize(Long.valueOf(pageSize));
        pageFrame.setPages(totalPage);
        pageFrame.setTotal(totalRow);
        ResultMsg result = new ResultMsg();
        result.setData(pageFrame);
        return result;
    }

    /**
     * 根据评论ID、分页获得回复数据
     *
     * @param reply
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "根据评论ID、分页获得回复数据", notes = "描述")
    @RequestMapping(value = "findReplyPage.json", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg replyPage(@ModelAttribute Reply reply, Long pageNum, Long pageSize) {
        PageQuery<Reply> pageQuery = new PageQuery<>();
        pageQuery.setPageSize(Long.valueOf(pageSize));
        pageQuery.setPageNumber(Long.valueOf(pageNum));
        pageQuery.setParas(reply);
        replyDao.templatePage(pageQuery);
        List<Reply> replies = pageQuery.getList();
        for (Reply rp : replies) {
            Comment comment = (Comment) rp.getTails().get("comment");
            CoreUser user = (CoreUser) rp.getTails().get("coreUser");
            CoreUser toUserName = userDao.single(rp.getToUserId());
            rp.setToUserName(toUserName.getName());
            rp.setFromUserName(user.getName());
            rp.setCommentCont(comment.getContent());
        }
        long totalPage = pageQuery.getTotalPage();
        long totalRow = pageQuery.getTotalRow();
        PageFrame pageFrame = new PageFrame();
        pageFrame.setList(replies);
        pageFrame.setPageNum(Long.valueOf(pageNum));
        pageFrame.setPageSize(Long.valueOf(pageSize));
        pageFrame.setPages(totalPage);
        pageFrame.setTotal(totalRow);
        ResultMsg result = new ResultMsg();
        result.setData(pageFrame);
        return result;
    }


}
