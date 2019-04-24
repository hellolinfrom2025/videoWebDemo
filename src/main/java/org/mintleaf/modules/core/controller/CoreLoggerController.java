package org.mintleaf.modules.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.beetl.sql.core.engine.PageQuery;
import org.mintleaf.modules.core.dao.CoreLoggerDao;
import org.mintleaf.modules.core.entity.CoreLogger;
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
import java.util.Random;

/**
 * 日志相关控制器
 * @Author: MengchuZhang
 * @Date: 2018/9/2 11:11
 * @Version 1.0
 */
@Api(tags="日志相关控制器",description="描述")
@Controller
@RequestMapping("coreLogger")
public class CoreLoggerController {
    @Autowired
    CoreLoggerDao coreCoreLoggerDao;

    /**
     * 进入列表页面
     * @return
     */
    @ApiOperation(value="进入列表页面", notes="描述")
    @RequestMapping(value="index.html",method = {RequestMethod.GET})
    public ModelAndView index(){
        ModelAndView view =new ModelAndView("modules/core/corelogger/index.html");
        return view;
    }

    /**
     * 进入编辑页面
     * @return
     */
    @ApiOperation(value="进入编辑页面", notes="描述")
    @RequestMapping(value="edit.html",method = {RequestMethod.GET})
    public ModelAndView edit(){
        ModelAndView view =new ModelAndView("modules/core/corelogger/edit.html");
        return view;
    }

    /**
     * 进入新增页面
     * @return
     */
    @ApiOperation(value="进入新增页面", notes="描述")
    @RequestMapping(value="add.html",method = {RequestMethod.GET})
    public ModelAndView add(){
        ModelAndView view =new ModelAndView("modules/core/corelogger/add.html");
        return view;
    }

    /**
     * 添加操作
     * @param coreLogger
     * @return
     */
    @ApiOperation(value="添加操作", notes="描述")
    @RequestMapping(value = "add.do",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg add(CoreLogger coreLogger) {
//        String uuid=UUIDUtils.getUUID();
        coreLogger.setId(new Random().nextInt());
        coreCoreLoggerDao.insert(coreLogger);
        ResultMsg result=new ResultMsg();
        result.setData(coreLogger);
        return result;
    }

    /**
     * 删除操作
     * @param coreLogger
     * @return
     */
    @ApiOperation(value="删除操作", notes="描述")
    @RequestMapping(value = "delete.do",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg delete(CoreLogger coreLogger) {
        coreCoreLoggerDao.deleteById(coreLogger.getId());
        ResultMsg result=new ResultMsg();
        return result;
    }

    /**
     * 批量删除操作
     * @param id
     * @return
     */
    @ApiOperation(value="批量删除操作", notes="描述")
    @RequestMapping(value = "deleteBatch.do",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg deleteBatch(String id) {
        coreCoreLoggerDao.deleteByIds(id.split(","));
        ResultMsg result=new ResultMsg();
        return result;
    }

    /**
     * 编辑操作
     * @param coreLogger
     * @return
     */
    @ApiOperation(value="编辑操作", notes="描述")
    @RequestMapping(value = "edit.do",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg update(CoreLogger coreLogger) {
        coreCoreLoggerDao.updateTemplateById(coreLogger);
        ResultMsg result=new ResultMsg();
        result.setData(coreLogger);
        return result;
    }

    /**
     * 查询单条数据操作
     * @param coreLogger
     * @return
     * @throws Exception
     */
    @ApiOperation(value="查询单条数据操作", notes="描述")
    @RequestMapping(value = "findById.json",method = {RequestMethod.POST}) //请求类型
    @ResponseBody
    public ResultMsg updateCoreMenu(CoreLogger coreLogger) throws Exception{
        CoreLogger menu=coreCoreLoggerDao.single(coreLogger.getId());
        ResultMsg result=new ResultMsg();
        result.setData(menu);
        return result;

    }

    /**
     * 查询全部数据操作
     * @return
     */
    @ApiOperation(value="查询全部数据操作", notes="描述")
    @RequestMapping(value="all.json",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg all(){
        List<CoreLogger> coreLoggers =coreCoreLoggerDao.all();
        ResultMsg result=new ResultMsg();
        result.setData(coreLoggers);
        return  result;
    }

    /**
     * 查询分页数据操作
     * @param coreLogger
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value="查询分页数据操作", notes="描述")
    @RequestMapping(value="page.json",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg page(@ModelAttribute CoreLogger coreLogger, Long pageNum, Long pageSize){
        PageQuery<CoreLogger> pageQuery=new PageQuery<CoreLogger>();
        pageQuery.setPageSize(Long.valueOf(pageSize));
        pageQuery.setPageNumber(Long.valueOf(pageNum));
        pageQuery.setParas(coreLogger);
        coreCoreLoggerDao.templatePage(pageQuery);
        PageFrame pageFrame=new PageFrame();
        pageFrame.setList(pageQuery.getList());
        pageFrame.setPageNum(Long.valueOf(pageNum));
        pageFrame.setPageSize(Long.valueOf(pageSize));
        pageFrame.setPages(pageQuery.getTotalPage());
        pageFrame.setTotal(pageQuery.getTotalRow());
        ResultMsg result=new ResultMsg();
        result.setData(pageFrame);
        return  result;
    }
}
