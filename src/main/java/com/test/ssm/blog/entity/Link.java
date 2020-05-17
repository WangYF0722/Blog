package com.test.ssm.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Link implements Serializable {
    private static final long serialVersionUID = -259829372268790508L;
    private Integer linkId;  //ID
    private String linkUrl;  //地址
    private String linkName;  //名称
    private String linkImage;  //图像
    private String linkDescription;  //描述
    private String linkOwnerNickname;  //昵称
    private String linkOwnerContact;  //联系方式
    private Date linkUpdateTime;  //更新时间
    private Date linkCreateTime;  //创建时间
    private Integer linkOrder;  //优先顺序
    private Integer linkStatus;  //状态
}
