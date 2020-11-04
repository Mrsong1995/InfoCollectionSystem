package com.key.dwsurvey.DTO;

import lombok.Data;

/**
 * @author suewong
 * @description
 * @date 2020/7/15 10:56
 */
@Data
public class AnFillblankDTO {

    //未填写数量
    private Integer emptyCount;
    //已填写数量
    private Integer blankCount;
}
