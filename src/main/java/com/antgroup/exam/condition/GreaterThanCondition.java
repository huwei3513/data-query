package com.antgroup.exam.condition;

import com.alibaba.fastjson.JSONObject;
import com.antgroup.exam.common.DatatypeException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author huwei
 * @date 2020/8/27
 */
public class GreaterThanCondition<T> implements Condition<T> {

    private final Map<String, Object> CONDITION_MAP = new HashMap<>();
    private final Class<T> CLAZZ;

    public GreaterThanCondition(Class<T> clazz) {
        this.CLAZZ = clazz;
    }

    @Override
    public Condition<T> add(String fieldName, Object value) throws NoSuchFieldException, DatatypeException {
        checkField(fieldName, value, this.CLAZZ);
        CONDITION_MAP.put(fieldName, value);
        return this;
    }

    @Override
    public boolean check(T t) {
        JSONObject jsonObject = JSONObject.parseObject(t.toString());
        final boolean[] eqResult = {true};
        CONDITION_MAP.keySet().forEach(s -> {
            boolean isGreater = false;
            if (null != jsonObject.get(s)) {
                isGreater = Double.parseDouble(jsonObject.get(s).toString())
                        > Double.parseDouble(CONDITION_MAP.get(s).toString());
            }
            eqResult[0] = eqResult[0] && isGreater;
        });
        return eqResult[0];
    }
}
