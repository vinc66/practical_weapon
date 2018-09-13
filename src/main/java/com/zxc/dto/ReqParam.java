package com.zxc.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Author: vincent  .
 * Date  : 2018/4/18  .
 * Desc  :
 */
@Data
public class ReqParam {

    private Integer start = 0;
    public Integer getStart() {
        return now * pageSize;
    }

    private Integer now = 0;
    private Integer pageSize = 10;
    //查询开始时间
    private String startTime;
    //查询结束时间
    private String endTime;
    //模糊查询
    private String search;

    private Integer status;

    private String phone;

    private String name;

    //    用户id
    private String userId;

    //    客户状态 0公海1有人跟进
    private Integer chickenStatus;

    //    课程id
    private Integer clazzId;
//    客户id
    private Integer chickenId;
//    客户跟踪类型 0:预约记录1跟进记录
    private Integer flowType;


    //        上线课程id
    private Integer onClazz;
    //    下架课程id
    private Integer offClazz;

    private List<Integer> ids;

}
