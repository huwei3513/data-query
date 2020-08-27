package com.antgroup.exam.common;

/**
 * @author huwei
 * @date 2020/8/27
 */
public enum DigitalDataType {

    /**
     * byte类型
     */
    BYTE("byte"),
    /**
     * Byte类型
     */
    BYTE_PACKAGE("java.lang.Byte"),
    /**
     * short类型
     */
    SHORT("short"),
    /**
     * Short类型
     */
    SHORT_PACKAGE("java.lang.Short"),
    /**
     * int类型
     */
    INT("int"),
    /**
     * INTEGER类型
     */
    INTEGER("java.lang.Integer"),
    /**
     * long类型
     */
    LONG("long"),
    /**
     * Long类型
     */
    LONG_PACKAGE("java.lang.Long"),
    /**
     * float类型
     */
    FLOAT("float"),
    /**
     * Float类型
     */
    FLOAT_PACKAGE("java.lang.Float"),
    /**
     * double类型
     */
    DOUBLE("double"),
    /**
     * Double类型
     */
    DOUBLE_PACKAGE("java.lang.Double");

    private final String typeName;

    DigitalDataType(String typeName) {
        this.typeName = typeName;
    }

    public static boolean isDigital(String typeName) {
        DigitalDataType[] values = DigitalDataType.values();
        for (DigitalDataType type : values) {
            if (type.typeName.contains(typeName)) {
                return true;
            }
        }
        return false;
    }
}
