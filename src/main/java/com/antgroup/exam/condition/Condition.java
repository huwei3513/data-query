package com.antgroup.exam.condition;

import com.antgroup.exam.common.DatatypeException;
import com.antgroup.exam.common.DigitalDataType;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

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
     * @throws NoSuchFieldException 字段不存在异常
     * @throws DatatypeException    数据类型错误异常
     */
    Condition<T> add(String fieldName, Object value) throws NoSuchFieldException, DatatypeException;

    /**
     * 校验条件是否满足
     *
     * @param t 被校验对象
     * @return 条件是否成立
     */
    boolean check(T t);

    /**
     * 校验字段类型和字段值
     *
     * @param fieldName 字段名称
     * @param value     字段值
     * @param clazz     对象具体数据类型
     * @throws NoSuchFieldException 字段不存在异常
     * @throws DatatypeException    数据类型错误异常
     */
    default void checkField(String fieldName, Object value, Class<T> clazz)
            throws DatatypeException, NoSuchFieldException {
        if (null == value) {
            throw new DatatypeException("数据值不能为空");
        }
        Field field = clazz.getDeclaredField(fieldName);
        Type type = field.getAnnotatedType().getType();
        if (!DigitalDataType.isDigital(type.getTypeName())) {
            throw new DatatypeException("数据类型错误");
        }
    }
}
