package com.antgroup.exam;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author huwei
 * @date 2020/8/26
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private String name;
    private String className;
    public int age;
    private int gender;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
