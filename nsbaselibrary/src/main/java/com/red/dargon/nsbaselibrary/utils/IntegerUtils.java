package com.red.dargon.nsbaselibrary.utils;

/**
 * Created by Dargon on 2017/7/18.
 */

public class IntegerUtils {
    /**
     * 判断数值是否是整数
     * @param orderAmount
     * @return
     */
    public static boolean checkOrderAmountIsInteger(double orderAmount) {
        double v = orderAmount * 100;
        return v % 100 <= 0;
    }
}
