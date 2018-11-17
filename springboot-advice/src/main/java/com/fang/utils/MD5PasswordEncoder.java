package com.fang.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in 2018/11/16 14:17
 * @Modified by:
 */
public class MD5PasswordEncoder {
    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
    private String salt;
    private String algorithm = "MD5";

    public MD5PasswordEncoder(String salt) {
        this.salt = salt;
    }

    /**
     * 加密
     * @param password
     * @return
     */
    public String encode(String password) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] digest = md.digest((password + salt).getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                String hex = Integer.toHexString(digest[i] & 0xFF);
                if (hex.length() == 1) {
                    sb.append("0");
                }
                sb.append(hex.toUpperCase());
            }
            result = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 创建盐值
     * @return
     */
    public static String createSalt() {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<32; i++) {
            int index = random.nextInt(62);
            sb.append(str.charAt(index));
        }
        return sb.toString();
    }
}
