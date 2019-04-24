package org.mintleaf.modules.parse.entity;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: 林清流
 * @modified By：15919
 * @create: 2019-04-16 15:01
 */
@Data
public class VideUrlOne {
    private String definition;
    private List<String> urlSegms;
    private double du;
    private int segs;
}