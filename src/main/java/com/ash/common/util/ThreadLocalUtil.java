package com.ash.common.util;

/**
 * ThreadLocal 为线程分配局部变量
 * @author jianshengfei
 * @Description
 * @create 2020-08-24 10:07
 */
public class ThreadLocalUtil {

    private static final ThreadLocal<String> context = new ThreadLocal<String>();
 
    /**
     * 根据key获取值
     * @return
     */
    public static String getValue() {
        return context.get();
    }

    /**
     * 存储
     * @param value
     * @return
     */
    public static void setValue(String value) {
        context.set(value);
    }

    /**
     * 移除
     */
    public static void removeValue() {
        context.remove();
    }
	 
}
