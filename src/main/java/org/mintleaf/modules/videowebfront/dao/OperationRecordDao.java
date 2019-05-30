package org.mintleaf.modules.videowebfront.dao;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.mintleaf.modules.videowebfront.entity.OperationRecord;
import org.springframework.stereotype.Component;

@Component
@SqlResource("operationRecord")
public interface OperationRecordDao extends BaseMapper<OperationRecord> {

}
