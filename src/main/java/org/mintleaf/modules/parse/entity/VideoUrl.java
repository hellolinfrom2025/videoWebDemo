package org.mintleaf.modules.parse.entity;

import lombok.Data;

import java.util.List;

/**
 * @description: 在线播放地址
 * @author: 林清流
 * @modified By：15919
 * @create: 2019-04-16 14:40
 */
@Data
public class VideoUrl {
    private String title;
    private String thumbnail;
    private List<VideUrlOne> urls;
}

