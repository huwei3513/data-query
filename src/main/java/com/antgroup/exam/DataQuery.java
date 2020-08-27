package com.antgroup.exam;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author huwei
 * @date 2020/8/26
 */
public class DataQuery<T> {

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
    //List<Student> query(List<Student> data, Where where, OrderBy orderBy, GroupBy groupBy, Limit limit);
    public List<T> query(List<T> data, Where<T> where, OrderBy<T> orderBy, String groupBy, String limit) {
        List<T> result = new ArrayList<>();
        //return s.getAge() > 10 && s.getAge() < 9;
        result = data.stream().filter(where).sorted(orderBy).collect(Collectors.toList());
        return result;
    }
}
