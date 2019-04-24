package org.mintleaf.modules.core.dao;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.mintleaf.modules.core.entity.CoreButton;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: MengchuZhang
 * @Date: 2018/9/2 11:07
 * @Version 1.0
 */
@Component
@SqlResource("coreButton")
public interface CoreButtonDao extends BaseMapper<CoreButton> {
    int deleteByIds(@Param("ids") String[] ids);
    public List<CoreButton> findButtonByRoleSample(@Param("name") String name);
}
