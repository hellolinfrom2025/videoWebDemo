package org.mintleaf.modules.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.beetl.sql.core.engine.PageQuery;
import org.mintleaf.modules.core.dao.CoreUserDao;
import org.mintleaf.modules.core.dao.CoreUserRoleDao;
import org.mintleaf.modules.core.entity.CoreUser;
import org.mintleaf.modules.core.entity.CoreUserRole;
import org.mintleaf.utils.MD5Util;
import org.mintleaf.vo.PageFrame;
import org.mintleaf.vo.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

import static org.mintleaf.vo.ResultMsg.fail;
import static org.mintleaf.vo.ResultMsg.ok;

/**
 * 用户相关控制器
 * @Author: MengchuZhang
 * @Date: 2018/8/20 14:51
 * @Version 1.0
 */

@Api(tags="用户相关控制器",description="描述")
@Controller
@RequestMapping("coreUser")
public class CoreUserController {

    @Autowired
    CoreUserDao coreUserDao;

    @Autowired
    CoreUserRoleDao coreUserRoleDao;

    /**
     * 进入列表页面
     * @return
     */
    @ApiOperation(value="进入列表页面", notes="描述")
    @RequestMapping(value="index.html",method = {RequestMethod.GET})
    public ModelAndView index(){
        ModelAndView view =new ModelAndView("modules/core/coreuser/index.html");
        return view;
    }

    /**
     * 进入编辑页面
     * @return
     */
//    @RequiresRoles("超级用户")
    @RequiresPermissions("user:edit")
    @ApiOperation(value="进入编辑页面", notes="描述")
    @RequestMapping(value="edit.html",method = {RequestMethod.GET})
    public ModelAndView edit(){
        ModelAndView view =new ModelAndView("modules/core/coreuser/edit.html");
        return view;
    }

    /**
     * 进入新增页面
     * @return
     */
    @RequiresPermissions("user:add")
    @ApiOperation(value="进入新增页面", notes="描述")
    @RequestMapping(value="add.html",method = {RequestMethod.GET})
    public ModelAndView add(){
        ModelAndView view =new ModelAndView("modules/core/coreuser/add.html");
        return view;
    }

    /**
     * 进入密码修改页面
     * @return
     */
    @ApiOperation(value="进入密码修改页面", notes="描述")
    @RequestMapping(value="updatePwd.html",method = {RequestMethod.GET})
    public ModelAndView updatePwd() {
        ModelAndView view =new ModelAndView("modules/core/coreuser/updatePwd.html");
        return view;
    }

    /**
     * 添加操作
     * @param coreUser
     * @return
     */
    @ApiOperation(value="添加操作", notes="描述")
    @RequestMapping(value = "add.do",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg add(CoreUser coreUser) {
        //向用户表插入新添加用户信息
        coreUser.setPsw(MD5Util.MD5("123456"));
//        String uuid=UUIDUtils.getUUID();
//        coreUser.setId(Integer.parseInt(uuid));
        coreUserDao.insertTemplate(coreUser);
        //根据添加的用户名查出单条数据
        CoreUser newUser=coreUserDao.sample(coreUser.getName());
        //向用户角色关系表插入新添加用户建立的角色
        String[] roles = coreUser.getRole().split(",");
        for (String role : roles) {
            CoreUserRole ur = new CoreUserRole();
            ur.setUserid(newUser.getId());
            ur.setRoleid( Integer.valueOf(role));
            coreUserRoleDao.insertTemplate(ur);
        }
        ResultMsg result=new ResultMsg();
        result.setData(coreUser);
        return result;
    }

    /**
     * 删除操作
     * @param coreUser
     * @return
     */
    @RequiresPermissions("user:delete")
    @ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
    @ApiOperation(value="删除操作", notes="描述")
    @RequestMapping(value = "delete.do",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg delete(CoreUser coreUser) {
        //根据id删除用户表单条数据
        coreUserDao.deleteById(coreUser.getId());
        //根据id删除用户角色关系表所有与id关联的数据
        CoreUserRole ur = new CoreUserRole();
        ur.setUserid(coreUser.getId());
        coreUserRoleDao.deleteSample(ur.getUserid());
        return ok();
    }

    /**
     * 批量删除操作
     * @param id
     * @return
     */
    @RequiresPermissions("user:delete")
    @ApiOperation(value="批量删除操作", notes="描述")
    @RequestMapping(value = "deleteBatch.do",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg deleteBatch(String id) {
        //根据多个用户id删除用户表信息
        coreUserDao.deleteByIds(id.split(","));
        //根据多个用户id删除用户角色关系表信息
        coreUserRoleDao.deleteByIds(id.split(","));
        return ok();
    }

    /**
     * 编辑操作
     * @param coreUser
     * @return
     */
    @ApiOperation(value="编辑操作", notes="描述")
    @RequestMapping(value = "edit.do",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg update(CoreUser coreUser) {
        //更新用户表信息
        coreUser.setUpdatetime(new Date());
        coreUserDao.updateTemplateById(coreUser);
        //根据新的用户名查出单条数据
        CoreUser editUser=coreUserDao.sample(coreUser.getName());
        //根据id删除用户角色关系表所有与id关联的数据
        CoreUserRole ur = new CoreUserRole();
        ur.setUserid(editUser.getId());
        coreUserRoleDao.deleteSample(ur.getUserid());
        //向用户角色关系表插入用户新修改的角色
        String[] roles = coreUser.getRole().split(",");
        for (String role : roles) {
            ur.setUserid(editUser.getId());
            ur.setRoleid( Integer.valueOf(role));
            coreUserRoleDao.insertTemplate(ur);
        }
        ResultMsg result=new ResultMsg();
        result.setData(coreUser);
        return result;
    }

    /**
     * 查询单条数据操作
     * @param coreUser
     * @return
     * @throws Exception
     */
    @ApiOperation(value="查询单条数据操作", notes="描述")
    @RequestMapping(value = "findById.json",method = {RequestMethod.POST}) //请求类型
    @ResponseBody
    public ResultMsg updateCoreMenu(CoreUser coreUser) throws Exception{
        //向用户表查询用户信息
        CoreUser user=coreUserDao.single(coreUser.getId());
        //向用户关系表查询用户角色信息
        List<CoreUserRole> userRoles=coreUserRoleDao.sample(user.getId());
        String roles="";
        for (CoreUserRole userRole : userRoles) {

            String role=String.valueOf(userRole.getRoleid());
            roles=roles+","+role;
            System.out.print(roles);
        }
        user.setRole(roles);
        ResultMsg result=new ResultMsg();
        result.setData(user);
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
        List<CoreUser> coreMenus =coreUserDao.all();
        ResultMsg result=new ResultMsg();
        result.setData(coreMenus);
        return  result;
    }

    /**
     * 查询分页数据操作
     * @param coreUser
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value="查询分页数据操作", notes="描述")
    @RequestMapping(value="page.json",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg page(@ModelAttribute CoreUser coreUser, Long pageNum, Long pageSize){
        PageQuery<CoreUser> pageQuery=new PageQuery<CoreUser>();
        pageQuery.setPageSize(Long.valueOf(pageSize));
        pageQuery.setPageNumber(Long.valueOf(pageNum));
        pageQuery.setParas(coreUser);
        //查出用户分页集合
        coreUserDao.templatePage(pageQuery);
        //遍历用户集合根据用户名称查出角色信息写入vo字段
        List<CoreUser> users =pageQuery.getList();
        for (CoreUser user : users) {
            //根据用户名称查出角色信息id
            List<CoreUserRole> userRoles=coreUserRoleDao.sample(user.getId());
            //格式拼接用","作为分隔符
            String roles="";
            for (CoreUserRole userRole : userRoles) {
                String role=String.valueOf(userRole.getRoleid());
                roles=roles+","+role;
                System.out.print(roles);
            }
            //写入vo,格式（1，2）
            user.setRole(roles);
        }


        PageFrame pageFrame=new PageFrame();
        pageFrame.setList(users);
        pageFrame.setPageNum(Long.valueOf(pageNum));
        pageFrame.setPageSize(Long.valueOf(pageSize));
        pageFrame.setPages(pageQuery.getTotalPage());
        pageFrame.setTotal(pageQuery.getTotalRow());
        ResultMsg result=new ResultMsg();
        result.setData(pageFrame);
        return  result;
    }

    /**
     * 修改用户密码操作
     * @param oldPwd
     * @param newPwd
     * @param confirm
     * @return
     */
    @ApiOperation(value="修改用户密码操作", notes="描述")
    @RequestMapping(value = "/updatePwd.do", method = RequestMethod.POST, params = {"oldPwd",  "newPwd", "confirm" })
    @ResponseBody
    public ResultMsg updatePwd(@RequestParam("oldPwd") String oldPwd, @RequestParam("newPwd") String newPwd, @RequestParam("confirm") String confirm) {
        if (oldPwd == null || oldPwd.length() <= 0 || newPwd == null || newPwd.length() <= 0 || confirm == null || confirm.length() <= 0) {
            return  fail("三个密码都不能为空");
        }
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        CoreUser coreUser=coreUserDao.sample(username);
        if (!coreUser.getPsw().equals(MD5Util.MD5(oldPwd))) {
            return fail("密码输入错误");
        }
        coreUser.setPsw(MD5Util.MD5(newPwd));
        coreUserDao.updateTemplateById(coreUser);
        return ok();
    }
}
