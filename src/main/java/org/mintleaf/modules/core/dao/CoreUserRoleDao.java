package org.mintleaf.modules.core.dao;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.mintleaf.modules.core.entity.CoreUserRole;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: MengchuZhang
 * @Date: 2018/8/20 11:23
 * @Version 1.0
 */
@Component
@SqlResource("coreUserRole")
public interface CoreUserRoleDao extends BaseMapper<CoreUserRole> {

    int deleteSample(@Param("userid") int userid);
     List<CoreUserRole> sample(@Param("userid") int userid);
    int deleteByIds(@Param("ids") String[] ids);

}
