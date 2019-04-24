package org.mintleaf.modules.core.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.beetl.sql.core.engine.PageQuery;
import org.mintleaf.modules.core.dao.*;
import org.mintleaf.modules.core.entity.*;
import org.mintleaf.vo.PageFrame;
import org.mintleaf.vo.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mintleaf.vo.ResultMsg.fail;
import static org.mintleaf.vo.ResultMsg.ok;

/**
 *  角色相关控制器
 * @Author: MengchuZhang
 * @Date: 2018/8/23 15:38
 * @Version 1.0
 */
@Api(tags="角色相关控制器",description="描述")
@Controller
@RequestMapping("coreRole")
public class CoreRoleController {
    @Autowired
    CoreRoleDao coreRoleDao;

    @Autowired
    CoreMenuDao coreMenuDao;

    @Autowired
    CoreButtonDao coreButtonDao;

    @Autowired
    CoreRoleMenuDao coreRoleMenuDao;

    @Autowired
    CoreRoleButtonDao coreRoleButtonDao;

    /**
     * 进入列表页面
     * @return
     */
    @ApiOperation(value="进入列表页面", notes="描述")
    @RequestMapping(value="index.html",method = {RequestMethod.GET})
    public ModelAndView index(){
        ModelAndView view =new ModelAndView("modules/core/corerole/index.html");
        return view;
    }

    /**
     * 进入编辑页面
     * @return
     */
    @RequiresPermissions("role:edit")
    @ApiOperation(value="进入编辑页面", notes="描述")
    @RequestMapping(value="edit.html",method = {RequestMethod.GET})
    public ModelAndView edit(){
        ModelAndView view =new ModelAndView("modules/core/corerole/edit.html");
        return view;
    }

    /**
     * 进入授权页面
     * @return
     */
    @RequiresPermissions("role:perms")
    @ApiOperation(value="进入授权页面", notes="描述")
    @RequestMapping(value="permission.html",method = {RequestMethod.GET})
    public ModelAndView permission(){
        ModelAndView view =new ModelAndView("modules/core/corerole/permission.html");
        return view;
    }

    /**
     * 进入新增页面
     * @return
     */
    @RequiresPermissions("role:add")
    @ApiOperation(value="进入新增页面", notes="描述")
    @RequestMapping(value="add.html",method = {RequestMethod.GET})
    public ModelAndView add(){
        ModelAndView view =new ModelAndView("modules/core/corerole/add.html");
        return view;
    }

    /**
     * 添加操作
     * @param coreRole
     * @return
     */
    @ApiOperation(value="添加操作", notes="描述")
    @RequestMapping(value = "add.do",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg add(CoreRole coreRole) {
        coreRoleDao.insertTemplate(coreRole);
        ResultMsg result=new ResultMsg();
        result.setData(coreRole);
        return result;
    }

    /**
     * 删除操作
     * @param coreRole
     * @return
     */
    @RequiresPermissions("role:delete")
    @ApiOperation(value="删除操作", notes="描述")
    @RequestMapping(value = "delete.do",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg delete(CoreRole coreRole) {
        coreRoleDao.deleteById(coreRole.getId());
        ResultMsg result=new ResultMsg();
        return result;
    }

    /**
     * 批量删除操作
     * @param id
     * @return
     */
    @RequiresPermissions("role:delete")
    @ApiOperation(value="批量删除操作", notes="描述")
    @RequestMapping(value = "deleteBatch.do",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg deleteBatch(String id) {
        coreRoleDao.deleteByIds(id.split(","));
        ResultMsg result=new ResultMsg();
        return result;
    }

    /**
     * 编辑操作
     * @param coreRole
     * @return
     */
    @ApiOperation(value="编辑操作", notes="描述")
    @RequestMapping(value = "edit.do",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg update(CoreRole coreRole) {
        coreRole.setUpdatetime(new Date());
        coreRoleDao.updateTemplateById(coreRole);
        ResultMsg result=new ResultMsg();
        result.setData(coreRole);
        return result;
    }

    /**
     * 查询单条数据操作
     * @param coreRole
     * @return
     * @throws Exception
     */
    @ApiOperation(value="查询单条数据操作", notes="描述")
    @RequestMapping(value = "findById.json",method = {RequestMethod.POST}) //请求类型
    @ResponseBody
    public ResultMsg updateCoreMenu(CoreRole coreRole) throws Exception{
        CoreRole menu=coreRoleDao.single(coreRole.getId());
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
        List<CoreRole> coreMenus =coreRoleDao.all();
        ResultMsg result=new ResultMsg();
        result.setData(coreMenus);
        return  result;
    }

    /**
     * 查询分页数据操作
     * @param coreRole
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value="查询分页数据操作", notes="描述")
    @RequestMapping(value="page.json",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg page(@ModelAttribute CoreRole coreRole, Long pageNum, Long pageSize){
        PageQuery<CoreRole> pageQuery=new PageQuery<CoreRole>();
        pageQuery.setPageSize(Long.valueOf(pageSize));
        pageQuery.setPageNumber(Long.valueOf(pageNum));
        pageQuery.setParas(coreRole);
        coreRoleDao.templatePage(pageQuery);
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

    /**
     * 角色添加菜单权限
     * @param menuIds
     * @param roleId
     * @return
     */
    @ApiOperation(value="角色添加菜单权限", notes="描述")
    @RequestMapping(value="addPermission.do",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg addPermission(String menuIds,String roleId){
        CoreRoleMenu rm=new CoreRoleMenu();
        CoreRoleButton rb=new CoreRoleButton();
        //根据id删除角色菜单关系表所有与roleid关联的数据
        coreRoleMenuDao.deleteSample(Integer.valueOf(roleId));
        //根据id删除角色按钮关系表所有与roleid关联的数据
        coreRoleButtonDao.deleteSample(Integer.valueOf(roleId));

        String[] ids = menuIds.split(",");
        for (String id : ids) {
            CoreMenu end=coreMenuDao.single(id);
            //判断是否为菜单id
            if(end!=null) {
                //向角色菜单关系表插入角色新修改的菜单权限
                rm.setRoleid(Integer.valueOf(roleId));
                rm.setMenuid(Integer.valueOf(id));
                coreRoleMenuDao.insertTemplate(rm);
            }else{
                //向角色按钮关系表插入角色新修改的菜单权限
                rb.setRoleid(Integer.valueOf(roleId));
                rb.setButtonid(Integer.valueOf(id));
                coreRoleButtonDao.insertTemplate(rb);
            }
        }
        return  ok();
    }

    /**
     * 角色获取菜单权限
     * @param roleId
     * @return
     */
    @ApiOperation(value="角色获取菜单权限", notes="描述")
    @RequestMapping(value="getPermission.json",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg getPermission(String roleId){
        try {
            //根据角色id获取菜单权限
            List <CoreRoleMenu>coreRoleMenu=coreRoleMenuDao.sample(Integer.valueOf(roleId));
            //根据角色id获取按钮权限
            List <CoreRoleButton>coreRoleButton=coreRoleButtonDao.sample(Integer.valueOf(roleId));
            //合并序列
            ArrayList Permissions = new ArrayList();
            for (CoreRoleButton button : coreRoleButton)
            {
                Permissions.add(button);
            }
            for (CoreRoleMenu menu : coreRoleMenu)
            {
                Permissions.add(menu);
            }
            ResultMsg result=new ResultMsg();
            result.setData(Permissions);
            return  result;
        } catch (Exception e) {
            e.printStackTrace();
            return fail();
        }
    }

    /**
     * 获取菜单树
     * @return
     */
    @ApiOperation(value="获取菜单树", notes="描述")
    @RequestMapping(value="getMenuTree.json",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg getMenuTree(){
        List<CoreMenu> listMenu =coreMenuDao.all();
        List<CoreButton> listButton =coreButtonDao.all();
        List<JSONObject> lstTree = new ArrayList<JSONObject>();
        for (CoreMenu menu : listMenu)
        {
            String strMenu = null;

            strMenu = "{id:" + menu.getId() + "," + "pId:"
                    + menu.getParentid() + "," + "name:"
                    + "'"+menu.getName()+"'" +","+ "open:true" + "}";
            JSONObject jsonObject = JSON.parseObject(strMenu);
            lstTree.add(jsonObject);

        }
        //按钮
        for (CoreButton button : listButton)
        {       String strMenu = null;
                strMenu = "{id:" + button.getId() + "," + "pId:"
                        +button.getMenuid() + "," + "name:"
                        + "'"+button.getName()+"'" +","+ "open:true" + "}";
                JSONObject json = JSON.parseObject(strMenu);
                lstTree.add(json);

        }
        //测试json转字符串
//        String msg = "{'rs':0,'fs':'登录成功!'}";
//        JSONObject jsonObject = JSON.parseObject(msg);
//        System.out.println(jsonObject);
        ResultMsg result=new ResultMsg();
        result.setData(lstTree);
        return  result;
    }
}
