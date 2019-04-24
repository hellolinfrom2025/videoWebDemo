package org.mintleaf.common;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.mintleaf.modules.core.dao.CoreButtonDao;
import org.mintleaf.modules.core.dao.CoreMenuDao;
import org.mintleaf.modules.core.dao.CoreRoleDao;
import org.mintleaf.modules.core.dao.CoreUserDao;
import org.mintleaf.modules.core.entity.CoreButton;
import org.mintleaf.modules.core.entity.CoreMenu;
import org.mintleaf.modules.core.entity.CoreRole;
import org.mintleaf.modules.core.entity.CoreUser;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: MengchuZhang
 * @Date: 2018/8/13 16:25
 * @Version 1.0
 */

public class Realm extends AuthorizingRealm {


    @Autowired
    CoreUserDao coreUserDao;

    @Autowired
    CoreRoleDao coreRoleDao;

    @Autowired
    CoreMenuDao coreMenuDao;

    @Autowired
    CoreButtonDao coreButtonDao;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //从凭证中获得用户名
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        //根据用户名查询用户对象
//        AdminUser adminUser = new AdminUser();
        CoreUser coreUser=coreUserDao.sample(username);


        //查询用户拥有的角色
        List<CoreRole> list = coreRoleDao.findRoleByIdSample(coreUser.getId());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        for (CoreRole coreRole : list) {
            System.out.print(coreRole.getName());
            //赋予用户角色
            info.addRole(coreRole.getName());
            List<CoreMenu> menus = coreMenuDao.findMenuByRoleSample(coreRole.getName());
            List<CoreButton> buttons = coreButtonDao.findButtonByRoleSample(coreRole.getName());
            
            for (CoreMenu menu : menus)
            {//赋予用户角色权限
                System.out.print(menu.getPermission());
                info.addStringPermission(menu.getPermission());
            }
            for (CoreButton button : buttons)
            {//赋予用户角色权限
                System.out.print(button.getPermission());
                info.addStringPermission(button.getPermission());
            }
        }
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //获得当前用户的用户名
        String username = (String) authenticationToken.getPrincipal();

        //从数据库中根据用户名查找用户
        CoreUser coreUser=coreUserDao.sample(username);
        if (coreUserDao.sample(username) == null) {
            throw new UnknownAccountException(
                    "没有在本系统中找到对应的用户信息。");
        }


        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(coreUser.getName(), coreUser.getPsw(),getName());
        return info;
    }

}