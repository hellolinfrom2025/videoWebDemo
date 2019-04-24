package org.mintleaf.modules.core.dao;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.mapper.BaseMapper;
import org.mintleaf.modules.core.entity.CoreRoleMenu;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: MengchuZhang
 * @Date: 2018/8/25 0:54
 * @Version 1.0
 */
@Component
public interface CoreRoleMenuDao extends BaseMapper<CoreRoleMenu> {
    int deleteSample(@Param("roleid") int roleid);
    public List<CoreRoleMenu> sample(@Param("roleid") int roleid);

}
