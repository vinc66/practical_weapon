package com.zxc.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Author: vincent  .
 * Date  : 2018/4/18  .
 * Desc  :
 */
@Getter
@Setter
@NoArgsConstructor
public class PageUtils {
    private Integer now = 0;
    private Integer pageSize = 10;
    private Integer total;
    private List data;
    private Integer start = 0;

    public PageUtils(Integer now) {
        this.now = now;
    }

    public Integer getStart() {
        return now * pageSize;
    }

}
