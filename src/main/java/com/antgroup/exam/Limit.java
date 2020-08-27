package com.antgroup.exam;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author huwei
 * @date 2020/8/27
 */

@Getter
@AllArgsConstructor
public class Limit {

    private int offset;
    private int rows;

}
