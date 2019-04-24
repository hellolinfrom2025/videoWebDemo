package org.mintleaf.modules.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.beetl.sql.core.*;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.core.db.OracleStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.gen.GenConfig;
import org.mintleaf.modules.core.controller.dto.GenDto;
import org.mintleaf.vo.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import static org.mintleaf.vo.ResultMsg.fail;
import static org.mintleaf.vo.ResultMsg.ok;

/**
 * 代码生成相关控制器
 * @Author: MengchuZhang
 * @Date: 2018/9/5 7:58
 * @Version 1.0
 */
@Api(tags="代码生成相关控制器",description="描述")
@Controller
@RequestMapping("gen")
public class GenController {
    @Autowired
    private Environment env;
    /**
     * 进入生成页面
     * @return
     */
    @ApiOperation(value="进入生成页面", notes="描述")
    @RequestMapping(value="index.html",method = {RequestMethod.GET})
    public ModelAndView index(){
        ModelAndView view =new ModelAndView("modules/core/gen/index.html");
        return view;
    }

    /**
     * 代码生成操作
     * @param genDto
     * @return
     */
    @RequestMapping(value = "gen.do",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg genDo (GenDto genDto) {
         String driver = env.getProperty("spring."+genDto.getDataSource()+".driver-class-name");
         String url = env.getProperty("spring."+genDto.getDataSource()+".url");
         String userName= env.getProperty("spring."+genDto.getDataSource()+".username");
         String passWord= env.getProperty("spring."+genDto.getDataSource()+".password");

        DBStyle dbStyle;
        ConnectionSource source = ConnectionSourceHelper.getSimple(driver, url, userName, passWord);
        //数据库类型
        if("oracle".equals(genDto.getDbType())){
            dbStyle = new OracleStyle();
        }else if("mysql".equals(genDto.getDbType())) {
            dbStyle = new MySqlStyle();
        }else{
            //默认为mysql
            dbStyle = new MySqlStyle();
        }
            // sql语句目录
            SQLLoader loader = new ClasspathLoader(genDto.getSqlPath());
            // 数据库命名跟java命名转化规则，UnderlinedNameConversion 指数据库表和列是下划线分割
            UnderlinedNameConversion nc = new  UnderlinedNameConversion();
            // 最后，创建一个SQLManager,DebugInterceptor 不是必须的，但可以通过它查看sql执行情况
            SQLManager sqlManager = new SQLManager(dbStyle,loader,source,nc,new Interceptor[]{new DebugInterceptor()});
        try {
            //生成实体类，以及sql的md文件
            sqlManager.genSQLFile(genDto.getTableName(),new GenConfig());
            sqlManager.genPojoCode(genDto.getTableName(),genDto.getEntityPath());
        } catch (Exception e) {
            e.printStackTrace();
            return fail("生成失败");
        }
        return ok(genDto);

    }



}
