package org.mintleaf.modules.parse.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mintleaf.modules.parse.entity.VideoUrl;
import org.mintleaf.modules.parse.service.PPTVProcessor;
import org.mintleaf.vo.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 获得视频在线播放真实地址接口
 * @author: 林清流
 * @modified By：15919
 * @create: 2019-04-16 10:40
 **/
@Api(tags = "视频播放地址解析控制器", description = "描述")
@RestController
@RequestMapping("parseVideoUrl")
public class ParseVideoUrlController {

    @Autowired
    PPTVProcessor pptvProcessor;

    @ApiOperation(value = "获取PPTV视频在线播放真实地址", notes = "描述")
    @RequestMapping(value = "getPptvUrl", method = {RequestMethod.GET})
    public ResultMsg getPptvUrl(String url) {
        VideoUrl videoUrl = pptvProcessor.runSpider(url);
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setData(videoUrl);
        return resultMsg;
    }
}