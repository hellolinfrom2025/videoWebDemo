package org.mintleaf.modules.videowebfront.services;

import org.apache.shiro.SecurityUtils;
import org.mintleaf.modules.video.dao.VideoDao;
import org.mintleaf.modules.video.entity.Video;
import org.mintleaf.modules.videowebfront.dao.OperationRecordDao;
import org.mintleaf.modules.videowebfront.entity.OperationRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Random;

/**
 * TODO
 *
 * @author by 15919
 * @createDate 2019/5/15 12:38
 */
@Service
public class PlayService {

    @Autowired
    OperationRecordDao operationRecDao;
    @Autowired
    VideoDao videoDao;

    /**
     * 播放量+1
     * @param record 传入videoId、userId和operationType='1'(播放)，或只传videoId和operationType='1'(播放)
     * @return : boolean
     * @author : 林清流
     * @time : 2019/5/15 16:13
     */
    @Transactional(rollbackFor = Exception.class)
    public void addPlayRec(OperationRecord record) {
        //1. 视频的播放量+1
        Video video = videoDao.single(record.getVideoId());
        Integer playCount = video.getPlayCount() + 1;
        video.setPlayCount(playCount);
        videoDao.updateTemplateById(video);
        //2. 用户是否登录，
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        if (username!=null) {
            //3. 用户是否有相关操作历史记录
            OperationRecord oldRec = operationRecDao.templateOne(record);
            if (oldRec!=null){
                oldRec.setTime(new Date());
                Integer times = oldRec.getOperationTimes()+1;
                oldRec.setOperationTimes(times);
                operationRecDao.updateTemplateById(oldRec);
            }else {
                OperationRecord newRec = new OperationRecord();
                newRec.setId(new Random().nextInt());
                newRec.setUserId(record.getUserId());
                newRec.setVideoId(record.getVideoId());
                newRec.setOperationType("1");
                newRec.setOperationTimes(1);
                newRec.setTime(new Date());
                operationRecDao.insert(newRec);
            }
        }
    }
}


