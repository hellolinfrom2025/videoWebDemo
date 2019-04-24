package org.mintleaf.modules.core.dao;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.mintleaf.modules.core.entity.CoreLogger;
import org.springframework.stereotype.Component;


/**
 * @Author: MengchuZhang
 * @Date: 2018/9/30 11:07
 * @Version 1.0
 */
@Component
@SqlResource("coreLogger")
public interface CoreLoggerDao extends BaseMapper<CoreLogger> {
    int deleteByIds(@Param("ids") String[] ids);
}
