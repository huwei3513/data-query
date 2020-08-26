package com.antgroup.exam.condition;

/**
 * @author huwei
 * @date 2020/8/26
 */
public interface Condition<T> {

    /**
     * 添加查询条件
     *
     * @param fieldName 字段名称
     * @param value     字段值
     * @return 查询条件
     */
    Condition<T> add(String fieldName, Object value);

    /**
     * 获取查询条件映射
     *
     * @param t 被校验对象
     * @return 查询条件映射
     */
    boolean check(T t);
}
