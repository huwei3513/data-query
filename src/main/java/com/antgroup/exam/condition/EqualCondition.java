package com.antgroup.exam.condition;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;

/**
 * @author huwei
 * @date 2020/8/26
 */
public class EqualCondition<T> implements Condition<T> {

    private final HashMap<String, Object> EQ_MAP = new HashMap<>();

    @Override
    public Condition<T> add(String fieldName, Object value) {
        EQ_MAP.put(fieldName, value);
        return this;
    }

    @Override
    public boolean check(T t) {
        JSONObject jsonObject = JSONObject.parseObject(t.toString());
        final boolean[] eqResult = {true};
        EQ_MAP.keySet().forEach(s -> {
            eqResult[0] = eqResult[0] && EQ_MAP.get(s).equals(jsonObject.get(s));
        });
        return eqResult[0];
    }
}
