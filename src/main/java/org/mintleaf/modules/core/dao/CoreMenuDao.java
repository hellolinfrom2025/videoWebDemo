package org.mintleaf.modules.core.dao;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;
import org.mintleaf.modules.core.entity.CoreMenu;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: MengchuZhang
 * @Date: 2018/8/20 20:32
 * @Version 1.0
 */
@Component
@SqlResource("coreMenu")
public interface CoreMenuDao extends BaseMapper<CoreMenu> {
    public List<CoreMenu> findMenuByNameSample(@Param("name") String name);
    public List<CoreMenu> findMenuByRoleSample(@Param("name") String name);
    void selectPage(PageQuery pageQuery);
    int deleteByIds(@Param("ids") String[] ids);
}
