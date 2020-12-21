package com.ash.common.generate;


import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;

/**
 * @author Jianshengfei
 * @Description
 * @create 2020-12-14 14:00
 */
public class UUIDUtil {


    private static final int maxLength = 14;

    /**
     * 随即编码
     */
    private static final int[] r = new int[]{7, 9, 6, 2, 8, 1, 3, 0, 5, 4};

    /**
     * 订单类别头
     */
    private static final String ORDER_CODE = "";

    /**
     * 退货类别头
     */
    private static final String RETURN_ORDER = "";

    /**
     * 退款类别头
     */
    private static final String REFUND_ORDER = "";

    /**
     * 带 -  的UUID
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString();
    }

    /**
     * 不带 - 的UUID
     * @return
     */
    public static String getContinuouslyUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    /**
     * 根据当前时间生成UUID
     * @return
     */
    public static UUID getUUIDByTimeBase(){
        return Generators.timeBasedGenerator(EthernetAddress.fromInterface()).generate();
    }


    /**
     * 生成固定长度随机码
     *
     * @param n 长度
     */
    private static long getRandom(long n) {
        long min = 1, max = 9;
        for (int i = 1; i < n; i++) {
            min *= 10;
            max *= 10;
        }
        long rangeLong = (((long) (new Random().nextDouble() * (max - min)))) + min;
        return rangeLong;
    }

    /**
     * 根据id进行加密+加随机数组成固定长度编码
     */
    private static String toCode(Integer userId) {
        String idStr = userId.toString();
        StringBuilder idsbs = new StringBuilder();
        for (int i = idStr.length() - 1; i >= 0; i--) {
            idsbs.append(r[idStr.charAt(i) - '0']);
        }
        return idsbs.append(getRandom(maxLength - idStr.length())).toString();
    }

    /**
     * 生成时间戳
     */
    private static String getDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
    }

    /**
     * 生成不带类别标头的编码
     *
     * @param userId
     */
    private static synchronized String getCode(Integer userId) {
        userId = userId == null ? 10000 : userId;
        return getDateTime() + toCode(userId);
    }


    /**
     * 生成单号编码(调用方法)
     * @param userId  网站中该用户唯一ID 防止重复
     */

    public static synchronized String getOrderCode(Integer userId) {
        return ORDER_CODE + getCode(userId);
    }

    /**
     * 生成退货单号编码（调用方法）
     * @param userId 网站中该用户唯一ID 防止重复
     */
    public static synchronized String getReturnCode(Integer userId) {
        return RETURN_ORDER + getCode(userId);
    }


    /**
     * 生成退款单号编码(调用方法)
     * @param userId  网站中该用户唯一ID 防止重复
     */
    public static synchronized String getRefundCode(Integer userId) {
        return REFUND_ORDER + getCode(userId);
    }


    public static void main(String[] args) {
        System.out.println(getContinuouslyUUID());
        System.out.println(getOrderCode(null));
    }




}
