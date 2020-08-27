package com.antgroup.exam.condition;

import com.alibaba.fastjson.JSONObject;
import com.antgroup.exam.common.DatatypeException;

import java.util.HashMap;

/**
 * @author huwei
 * @date 2020/8/27
 */
public class LessThanCondition<T> implements Condition<T> {

    private final HashMap<String, Object> CONDITION_MAP = new HashMap<>();
    private final Class<T> CLAZZ;

    public LessThanCondition(Class<T> clazz) {
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
            boolean isGreater = true;
            if (null != jsonObject.get(s)) {
                isGreater = Double.parseDouble(jsonObject.get(s).toString())
                        < Double.parseDouble(CONDITION_MAP.get(s).toString());
            }
            eqResult[0] = eqResult[0] && isGreater;
        });
        return eqResult[0];
    }
}
