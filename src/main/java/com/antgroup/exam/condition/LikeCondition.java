package com.antgroup.exam.condition;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author huwei
 * @date 2020/8/27
 */
public class LikeCondition<T> implements Condition<T> {

    private final Map<String, Object> CONDITION_MAP = new HashMap<>();

    @Override
    public Condition<T> add(String fieldName, Object value) {
        CONDITION_MAP.put(fieldName, value.toString());
        return this;
    }

    @Override
    public boolean check(T t) {
        JSONObject jsonObject = JSONObject.parseObject(t.toString());
        final boolean[] eqResult = {true};
        CONDITION_MAP.keySet().forEach(s -> {
            Pattern pattern = Pattern.compile((String) CONDITION_MAP.get(s));
            Matcher matcher = pattern.matcher(jsonObject.get(s).toString());
            eqResult[0] = eqResult[0] && matcher.find();
        });
        return eqResult[0];
    }
}
