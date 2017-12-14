package com.red.dargon.nsbaselibrary.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by javalong on 16-11-14.
 * <p>
 * 加密工具类
 */
public class CodeUtils {
    /**
     * MD5加密
     * 取32位字母小写
     *
     * @return 小写md5
     */
    public static String getMD5Str(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException caught!");
            System.exit(-1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(
                        Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        //32位
        return md5StrBuff.toString().toLowerCase();
    }

    private static long lastClickTime;

    /**
     * 防暴力点击 true则return false则走方法
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 800) {
            ToastUtils.showShort("请勿快速点击");
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 防暴力点击 true则return false则走方法
     */
    public static boolean isFastDoubleClick(long checkTime) {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < checkTime) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

}
