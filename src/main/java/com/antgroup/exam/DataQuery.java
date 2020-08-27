package com.antgroup.exam;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * @author huwei
 * @date 2020/8/26
 * T:待查询的数据类型
 * R:待分组字段的类型，目前只支持按单个字段分组
 */
public class DataQuery<T, R> {

    /**
     * 根据条件查询数据，带排序、分组、数量限制
     *
     * @param data    目标数据集
     * @param where   查询条件
     * @param orderBy 排序规则
     * @param groupBy 分组规则
     * @param limit   返回最大数量
     * @return 查询结果数据集
     */
    public List<T> query(List<T> data, Where<T> where, OrderBy<T> orderBy, GroupBy<T, R> groupBy, Limit limit) {
        List<T> result = new ArrayList<>();
        if (null == where) {
            result = data;
        } else {
            result = data.parallelStream().filter(where).collect(toList());
        }
        if (null != orderBy) {
            result = result.parallelStream().sorted(orderBy).collect(toList());
        }
        if (null != groupBy) {
            Map<R, List<T>> resultMap = result.parallelStream().collect(groupingBy(groupBy));
            result.clear();
            for (R r : resultMap.keySet()) {
                result.addAll(resultMap.get(r));
            }
        }
        if (null != limit) {
            int offset = limit.getOffset();
            int rows = limit.getRows();
            if (offset < 0) {
                offset = 0;
            }
            if (offset > result.size()) {
                offset = result.size();
            }
            if (rows < 0) {
                rows = 0;
            }
            int toIndex = offset + rows;
            if (toIndex > result.size()) {
                toIndex = result.size();
            }
            result = result.subList(offset, toIndex);
        }
        return result;
    }
}
