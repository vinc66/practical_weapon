package com.zxc.dto;

import com.zxc.enums.ErrorCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/17.
 */
@Getter
@Setter
@ApiModel(value = "返回数据格式")
public class ResultDto<T> implements Serializable {
    @ApiModelProperty(value = "返回码，0成功，其他返回码见msg")
    private int code = ErrorCode.SUCCESS.code;
    @ApiModelProperty(value = "错误返回信息")
    private String msg = ErrorCode.SUCCESS.msg;
    @ApiModelProperty(value = "成功返回的数据")
    private T data;
    @ApiModelProperty(value = "成功的条数，不是分页没用")
    private Integer total = 0;

    public static ResultDto newInstance() {
        return new ResultDto();
    }

    public ResultDto() {
        this.msg = ErrorCode.SUCCESS.msg;
        this.code = ErrorCode.SUCCESS.code;
    }

    public ResultDto(ErrorCode errorCode) {
        this.msg = errorCode.msg;
        this.code = errorCode.code;
    }

    public ResultDto(ErrorCode errorCode, String msg) {
        this.msg = msg;
        this.code = errorCode.code;
    }


    public ResultDto(T data) {
        super();
        this.data = data;
    }

    public ResultDto(T data, Integer total) {
        super();
        this.data = data;
        this.total = total;
    }


    public static ResultDto newInstance(Object list) {
        return new ResultDto(list);
    }
}
