package com.letv.cn.csg.dto;

import lombok.Data;

/**
 * 存放视频信息
 *
 * @author csg
 * @create 2017-06-9:40
 */
@Data
public class videoDTO {
    // 视频是否有效
    private Boolean available;
    // 视频标题
    private String title;
    // 视频背景图片
    private String image;
    // 视频地址
    private String value;
}
