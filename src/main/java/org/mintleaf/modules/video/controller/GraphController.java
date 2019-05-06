package org.mintleaf.modules.video.controller;

import com.github.abel533.echarts.Legend;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.Tooltip;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.Position;
import com.github.abel533.echarts.code.SelectedMode;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.data.PieData;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Pie;
import com.github.abel533.echarts.style.ItemStyle;
import com.github.abel533.echarts.style.TextStyle;
import com.github.abel533.echarts.style.itemstyle.Normal;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mintleaf.modules.video.dao.GraphDao;
import org.mintleaf.modules.video.entity.Video;
import org.mintleaf.modules.video.entity.VideoTag;
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
 * 图表统计控制器
 *
 * @Author: Lin
 * @Date: 2018/8/23 15:38
 * @Version 1.0
 */
@Api(tags = "图表统计控制器", description = "描述")
@Controller
@RequestMapping("graph")
public class GraphController {

    @Autowired
    GraphDao graphDao;

    /**
     * 进入列表页面
     *
     * @return
     */
    @ApiOperation(value = "进入主页面", notes = "描述")
    @RequestMapping(value = "index.html", method = {RequestMethod.GET})
    public ModelAndView index() {
        return new ModelAndView("modules/video/graph/index.html");
    }

    /**
     * 获得视频标签给echart的饼图
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "获得视频标签给echart", notes = "描述")
    @RequestMapping(value = "getVideoTag.echart", method = {RequestMethod.GET})
    public ResultMsg getVideoTag() {

        Option option = new Option();
        option.tooltip(new Tooltip().trigger(Trigger.item).formatter("{a} <br/>{b}: {c} ({d}%)"));
        option.legend(new Legend().orient(Orient.vertical).x("left"));
        Pie pie1 = new Pie("视频分类");
        ItemStyle label = new ItemStyle();
        label.normal(new Normal().position(Position.inside));
        pie1.radius(new String[]{"0", "30%"}).label(label).setSelectedMode(SelectedMode.single);
        List<VideoTag> tags = graphDao.getTagToEchart();
        pie1.data(new PieData("年代", tags.get(0).getTails().get("value")),
                new PieData("类目", tags.get(1).getTails().get("value")),
                new PieData("地区", tags.get(2).getTails().get("value")));
        Pie pie2 = new Pie("视频标签");
        ItemStyle label2 = new ItemStyle();
        Normal normal = new Normal();
        normal.formatter("{a}\n{b}：{c} ({d}%)  ").textStyle(new TextStyle())
                .barBorderColor("#eee").borderColor("#aaa").borderRadius(40).borderWidth(1);
        label2.normal(normal);
        pie2.radius(new String[]{"40%", "55%"}).label(label2);
        List<VideoTag> videoTags = graphDao.getVideoTagToEchart();
        for (VideoTag videoTag : videoTags) {
            pie2.data(new PieData(videoTag.getTails().get("name").toString(), videoTag.getTails().get("value")));
        }
        option.series(pie1, pie2);
        ResultMsg result = new ResultMsg();
        result.setData(option);
        return result;
    }

    /**
     * 获得视频标签给echart的饼图
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "获得视频播放总量的前六给echart", notes = "描述")
    @RequestMapping(value = "getVideoTotalTop.echart", method = {RequestMethod.GET})
    public ResultMsg getVideoTotalTop(String type) {
        List<Video> videos = new ArrayList<>();
        String[] xAxisData = new String[6];
        Integer[] yAxisData = new Integer[6];
        switch (type) {
            case "play":
                videos = graphDao.getVideoPlayTop();
                for (int i = 0; i < videos.size(); i++) {
                    xAxisData[i] = videos.get(i).getTitle();
                    yAxisData[i] = videos.get(i).getPlayCount();
                }
                break;
            case "download":
                videos = graphDao.getVideoDownloadTop();
                for (int i = 0; i < videos.size(); i++) {
                    xAxisData[i] = videos.get(i).getTitle();
                    yAxisData[i] = videos.get(i).getDownloadCount();
                }
                break;
            case "good":
                videos = graphDao.getVideoGoodTop();
                for (int i = 0; i < videos.size(); i++) {
                    xAxisData[i] = videos.get(i).getTitle();
                    yAxisData[i] = videos.get(i).getGoodCount();
                }
                break;
            case "collect":
                videos = graphDao.getVideoCollectTop();
                for (int i = 0; i < videos.size(); i++) {
                    xAxisData[i] = videos.get(i).getTitle();
                    yAxisData[i] = videos.get(i).getCollectCount();
                }
                break;
            default:
                break;

        }

        Option option = new Option();
//        option.xAxis(new CategoryAxis().data("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));
        option.xAxis(new CategoryAxis().data(xAxisData));
        option.yAxis(new ValueAxis());
        Bar bar = new Bar();
//        bar.data(320, 200, 150, 80, 70, 10, 5);
        bar.data(yAxisData);
        option.series(bar);
        ResultMsg result = new ResultMsg();
        result.setData(option);
        return result;
    }

    /**
     * 获得视频标签给echart的饼图
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "获得视频下载总量的前六给echart", notes = "描述")
    @RequestMapping(value = "getVideoDownloadTop.echart", method = {RequestMethod.GET})
    public ResultMsg getVideoDownloadTop() {
        Option option = new Option();
        option.xAxis(new CategoryAxis().data("wo", "qu", "nian", "mai", "le", "ge", "biao"));
        option.yAxis(new ValueAxis());
        Bar bar = new Bar();
        bar.data(220, 200, 150, 80, 70, 50, 30);
        option.series(bar);
        ResultMsg result = new ResultMsg();
        result.setData(option);
        return result;
    }

}
