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
     * 校验条件是否满足
     *
     * @param t 被校验对象
     * @return 条件是否成立
     */
    boolean check(T t);
}
