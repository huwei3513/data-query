package com.antgroup.exam.condition;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author huwei
 * @date 2020/8/27
 */
public class NotEqualCondition<T> implements Condition<T> {

    private final Map<String, Object> CONDITION_MAP = new HashMap<>();

    @Override
    public Condition<T> add(String fieldName, Object value) {
        CONDITION_MAP.put(fieldName, value);
        return this;
    }

    @Override
    public boolean check(T t) {
        JSONObject jsonObject = JSONObject.parseObject(t.toString());
        final boolean[] eqResult = {true};
        CONDITION_MAP.keySet().forEach(s -> {
            eqResult[0] = eqResult[0] && !CONDITION_MAP.get(s).equals(jsonObject.get(s));
        });
        return eqResult[0];
    }
}
