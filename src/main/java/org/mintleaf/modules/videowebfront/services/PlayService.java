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
     *
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
        if (username != null) {
            //3. 用户是否有相关操作历史记录
            OperationRecord oldRec = operationRecDao.templateOne(record);
            if (oldRec != null) {
                oldRec.setTime(new Date());
                Integer times = oldRec.getOperationTimes() + 1;
                oldRec.setOperationTimes(times);
                operationRecDao.updateTemplateById(oldRec);
            } else {
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

    /**
     * 下载量+1
     *
     * @param record
     * @return : boolean
     * @author : 林清流
     * @time : 2019/5/15 16:13
     */
    @Transactional(rollbackFor = Exception.class)
    public void addDownloadRec(OperationRecord record) {
        //1. 用户是否有相关操作历史记录
        OperationRecord oldRec = operationRecDao.templateOne(record);
        if (oldRec != null) {
            oldRec.setTime(new Date());
            Integer times = oldRec.getOperationTimes() + 1;
            oldRec.setOperationTimes(times);
            operationRecDao.updateTemplateById(oldRec);
        } else {
            OperationRecord newRec = new OperationRecord();
            newRec.setId(new Random().nextInt());
            newRec.setUserId(record.getUserId());
            newRec.setVideoId(record.getVideoId());
            newRec.setOperationType("2");
            newRec.setOperationTimes(1);
            newRec.setTime(new Date());
            operationRecDao.insert(newRec);
        }
        //2. 更新视频的播放量
        OperationRecord r = new OperationRecord();
        r.setVideoId(record.getVideoId());
        r.setOperationType("2");
        Long count = operationRecDao.templateCount(r);
        Video video = videoDao.single(record.getVideoId());
        video.setDownloadCount(count.intValue());
        videoDao.updateTemplateById(video);
    }
    /**
     * 是否收藏
     *
     * @param record
     * @return : boolean
     * @author : 林清流
     * @time : 2019/5/15 16:13
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean addCollectRec(OperationRecord record) {
        boolean isCollect;
        //1. 用户是否有相关操作历史记录
        OperationRecord oldRec = operationRecDao.templateOne(record);
        if (oldRec != null) {
            //有收藏就删除
            operationRecDao.deleteById(oldRec.getId());
            isCollect= false;
        } else {
            //没有就新增收藏记录
            OperationRecord newRec = new OperationRecord();
            newRec.setId(new Random().nextInt());
            newRec.setUserId(record.getUserId());
            newRec.setVideoId(record.getVideoId());
            newRec.setOperationType("4");
            newRec.setOperationTimes(1);
            newRec.setTime(new Date());
            operationRecDao.insert(newRec);
            isCollect =true;
        }
        //2. 更新视频的收藏量
        OperationRecord r = new OperationRecord();
        r.setVideoId(record.getVideoId());
        r.setOperationType("4");
        Long count = operationRecDao.templateCount(r);
        Video video = videoDao.single(record.getVideoId());
        video.setCollectCount(count.intValue());
        videoDao.updateTemplateById(video);

        return isCollect;
    }
    /**
     * 是否点赞
     *
     * @param record
     * @return : boolean
     * @author : 林清流
     * @time : 2019/5/15 16:13
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean addlikeRec(OperationRecord record) {
        boolean islike;
        //1. 用户是否有相关操作历史记录
        OperationRecord oldRec = operationRecDao.templateOne(record);
        if (oldRec != null) {
            //有点赞就删除
            operationRecDao.deleteById(oldRec.getId());
            islike= false;
        } else {
            //没有就新增点赞记录
            OperationRecord newRec = new OperationRecord();
            newRec.setId(new Random().nextInt());
            newRec.setUserId(record.getUserId());
            newRec.setVideoId(record.getVideoId());
            newRec.setOperationType("3");
            newRec.setOperationTimes(1);
            newRec.setTime(new Date());
            operationRecDao.insert(newRec);
            islike =true;
        }
        //2. 更新视频的点赞量
        OperationRecord r = new OperationRecord();
        r.setVideoId(record.getVideoId());
        r.setOperationType("3");
        Long count = operationRecDao.templateCount(r);
        Video video = videoDao.single(record.getVideoId());
        video.setGoodCount(count.intValue());
        videoDao.updateTemplateById(video);

        return islike;
    }

}


