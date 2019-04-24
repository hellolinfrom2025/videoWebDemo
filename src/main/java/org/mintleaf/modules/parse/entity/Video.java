package org.mintleaf.modules.parse.entity;

import lombok.Data;

import java.util.List;

@Data
public class Video {
	private String name;
	private String pic;
	private List<Dt> dts;
}
