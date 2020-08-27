package com.antgroup.exam;

import com.alibaba.fastjson.JSONObject;
import com.antgroup.exam.common.OrderDirection;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;

/**
 * @author huwei
 * @date 2020/8/27
 */
public class OrderBy<T> implements Comparator<T> {

    private final HashMap<String, OrderDirection> ORDER_MAP = new HashMap<>();


    public OrderBy<T> add(String fieldName, OrderDirection direction) {
        ORDER_MAP.put(fieldName, direction);
        return this;
    }

    @Override
    public int compare(T t1, T t2) {
        int result = 0;
        JSONObject object1 = JSONObject.parseObject(t1.toString());
        JSONObject object2 = JSONObject.parseObject(t2.toString());
        Set<String> keySet = ORDER_MAP.keySet();
        for (String fieldName : keySet) {
            OrderDirection direction = ORDER_MAP.get(fieldName);
            Object value1 = object1.get(fieldName);
            Object value2 = object2.get(fieldName);
            int isBigger = 0;
            if (null != value1 && null != value2) {
                int compare = value1.toString().compareTo(value2.toString());
                if (compare > 0) {
                    isBigger = 1;
                } else if (compare < 0) {
                    isBigger = -1;
                }
            } else if (value1 == null && value2 != null) {
                isBigger = -1;
            } else if (value1 != null) {
                isBigger = 1;
            }
            if (isBigger == 0) {
                continue;
            }
            if (OrderDirection.ASC.equals(direction)) {
                if (isBigger > 0) {
                    return -isBigger;
                } else {
                    return isBigger;
                }
            } else {
                if (isBigger > 0) {
                    return isBigger;
                } else {
                    return -isBigger;
                }
            }
        }
        return result;
    }
}
