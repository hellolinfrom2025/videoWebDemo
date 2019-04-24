package org.mintleaf.modules.core.dao;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.mintleaf.modules.core.entity.CoreRoleButton;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: MengchuZhang
 * @Date: 2018/9/2 11:08
 * @Version 1.0
 */
@Component
@SqlResource("coreRoleButton")
public interface CoreRoleButtonDao extends BaseMapper<CoreRoleButton> {
    int deleteSample(@Param("roleid") int roleid);
    public List<CoreRoleButton> sample(@Param("roleid") int roleid);
}
