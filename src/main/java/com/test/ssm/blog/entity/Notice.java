package com.test.ssm.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/*
公告
 */

@Data
public class Notice implements Serializable {
    private static final long serialVersionUID = -4901560494243593100L;
    private Integer noticeId;  //ID
    private String noticeTitle;  //标题
    private String noticeContent;  //内容
    private Date noticeCreateTime;  //创建时间
    private Date noticeUpdateTime;   //更新还是件
    private  Integer noticeStatus;   //状态
    private Integer noticeOrder;   //优先顺序

}
