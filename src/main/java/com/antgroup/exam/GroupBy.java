package com.antgroup.exam;

import com.alibaba.fastjson.JSONObject;

import java.util.function.Function;

/**
 * @author huwei
 * @date 2020/8/27
 */
public class GroupBy<T, R> implements Function<T, R> {

    private String fieldName;

    public GroupBy(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public R apply(T t) {
        JSONObject object = JSONObject.parseObject(t.toString());
        return (R) object.get(fieldName);
    }
}
