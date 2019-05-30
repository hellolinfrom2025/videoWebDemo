package org.mintleaf.modules.videowebfront.controller;

import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.data.PieData;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.series.Pie;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.mintleaf.modules.core.entity.CoreUser;
import org.mintleaf.modules.video.entity.VideoTag;
import org.mintleaf.modules.videowebfront.dao.DataGraphDao;
import org.mintleaf.utils.DateUtils;
import org.mintleaf.vo.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.List;

/**
 * 前台用户数据分析主控制器
 *
 * @Author: linql
 * @Date: 2018/8/15 8:24
 * @Version 1.0
 */
@Api(tags = "前台用户数据分析主控制器", description = "描述")
@Controller
@RequestMapping("videoWebFront/dataGraph")
public class DataGraphController {

    @Autowired
    DataGraphDao dataGraphDao;

    /**
     * 进入前台用户数据分析页面
     *
     * @return
     */
    @ApiOperation(value = "进入前台用户数据分析页面", notes = "描述")
    @RequestMapping(value = "index.html", method = {RequestMethod.GET})
    public ModelAndView index() {
        return new ModelAndView("modules/videowebfront/datagraph.html");
    }

    /**
     * 当前用户最近10天活跃趋势统计图
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "当前用户最近10天活跃趋势统计图", notes = "描述")
    @RequestMapping(value = "getOneUserActiveToEchart.echart", method = {RequestMethod.GET})
    public ResultMsg getOneUserActiveToEchart() {

        String[] xAxisData = new String[10];
        Integer[] yAxisData = new Integer[10];
        Integer[] yAxisData2 = new Integer[10];
        Integer[] yAxisData3 = new Integer[10];
        Integer[] yAxisData4 = new Integer[10];
        //获得当前登录的用户
        CoreUser user = (CoreUser) SecurityUtils.getSubject().getSession().getAttribute("user");
        int uid = user.getId();
        try {
            for (int i = 0; i < xAxisData.length; i++) {
                xAxisData[xAxisData.length - i - 1] = DateUtils.dateFormat(DateUtils.dateAddDays(null, -i), null);
                for (int j = 1; j < 5; j++) {
                    if (j == 1) {
                        yAxisData[xAxisData.length - i - 1] = dataGraphDao.getOneUserActiveToEchart(String.valueOf(j), i, uid).size();
                    } else if (j == 2) {
                        yAxisData2[xAxisData.length - i - 1] = dataGraphDao.getOneUserActiveToEchart(String.valueOf(j), i, uid).size();
                    } else if (j == 3) {
                        yAxisData3[xAxisData.length - i - 1] = dataGraphDao.getOneUserActiveToEchart(String.valueOf(j), i, uid).size();
                    } else if (j == 4) {
                        yAxisData4[xAxisData.length - i - 1] = dataGraphDao.getOneUserActiveToEchart(String.valueOf(j), i, uid).size();
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Option option = new Option();
        option.xAxis(new CategoryAxis().data(xAxisData).boundaryGap(false));
        option.yAxis(new ValueAxis());
        option.series(new Line("播放").data(yAxisData).smooth(true),
                new Line("下载").data(yAxisData2).smooth(true),
                new Line("收藏").data(yAxisData3).smooth(true),
                new Line("点赞").data(yAxisData4).smooth(true));
        ResultMsg result = new ResultMsg();
        result.setData(option);
        return result;
    }

    /**
     * 获得用户播放量最多的前六数量给图表
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "获得用户播放量最多的前六数量给图表", notes = "描述")
    @RequestMapping(value = "getUserPlayCount.echart", method = {RequestMethod.GET})
    public ResultMsg getUserPlayCount() {
        //获得当前登录的用户
        CoreUser user = (CoreUser) SecurityUtils.getSubject().getSession().getAttribute("user");
        int uid = user.getId();
        List<VideoTag> videos = dataGraphDao.getUserPlayCountToEchart(uid);
        String[] xAxisData = new String[videos.size()];
        Integer[] yAxisData = new Integer[videos.size()];
        for (int i = 0; i < videos.size(); i++) {
            xAxisData[i] = videos.get(i).getTails().get("title").toString();
            yAxisData[i] = (Integer) videos.get(i).getTails().get("operationTimes");
        }

        Option option = new Option();
        option.xAxis(new CategoryAxis().data(xAxisData));
        option.yAxis(new ValueAxis());
        option.series(new Bar().data(yAxisData));
        ResultMsg result = new ResultMsg();
        result.setData(option);
        return result;
    }

    /**
     * 获得用户（播放、下载、点赞、收藏）数量最多的视频标签给图表
     *
     * @param operationType 用户对视频的操作了类型（1：播放、2：下载、3：点赞：、4：收藏）
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "获得用户（播放、下载、点赞、收藏）数量最多的视频标签给图表", notes = "描述")
    @RequestMapping(value = "getUserVideoType.echart", method = {RequestMethod.GET})
    public ResultMsg getUserVideoType(int operationType) {

        Long yearValue = 0L, categoryValue = 0L, areaValue = 0L;
        Option option = new Option();
        Pie pie2 = new Pie();
        //获得当前登录的用户
        CoreUser user = (CoreUser) SecurityUtils.getSubject().getSession().getAttribute("user");
        int uid = user.getId();
        for (int i = 1; i < 4; i++) {
            List<VideoTag> videoTags = dataGraphDao.getUserVideoTypeToEchart(operationType, uid, i);
            for (VideoTag videoTag : videoTags) {
                pie2.data(new PieData(videoTag.getTails().get("name").toString(), videoTag.getTails().get("value")));
                if (i == 1) {
                    yearValue += (Long) videoTag.getTails().get("value");
                }
                if (i == 2) {
                    categoryValue += (Long) videoTag.getTails().get("value");
                }
                if (i == 3) {
                    areaValue += (Long) videoTag.getTails().get("value");
                }
            }
        }

        Pie pie1 = new Pie();
        pie1.data(new PieData("年代", yearValue),
                new PieData("类目", categoryValue),
                new PieData("地区", areaValue));
        option.series(pie1, pie2);
        ResultMsg result = new ResultMsg();
        result.setData(option);
        return result;
    }
}
