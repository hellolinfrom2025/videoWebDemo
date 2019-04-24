package org.mintleaf.modules.core.dao;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.mintleaf.modules.core.entity.CoreRole;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: MengchuZhang
 * @Date: 2018/8/20 11:18
 * @Version 1.0
 */
@Component
@SqlResource("coreRole")
public interface CoreRoleDao extends BaseMapper<CoreRole> {
    public List<CoreRole> findRoleByIdSample(@Param("id") long id);
    int deleteByIds(@Param("ids") String[] ids);
}
