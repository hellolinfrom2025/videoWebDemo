package org.mintleaf.modules.core.dao;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.mintleaf.modules.core.entity.CoreUser;
import org.springframework.stereotype.Component;

/**
 * @Author: MengchuZhang
 * @Date: 2018/8/20 11:16
 * @Version 1.0
 */
@Component
@SqlResource("coreUser")
public interface CoreUserDao extends BaseMapper<CoreUser> {
    public CoreUser sample(@Param("name") String name);
    int deleteByIds(@Param("ids") String[] ids);
}
