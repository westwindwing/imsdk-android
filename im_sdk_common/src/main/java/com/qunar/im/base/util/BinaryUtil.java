package com.qunar.im.base.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class BinaryUtil {
    protected static final char[] hexArray = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[(bytes.length * 2)];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 255;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[(j * 2) + 1] = hexArray[v & 15];
        }
        return new String(hexChars);
    }

    public static byte[] h2b(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.replace(" ", "");
        int length = hexString.length() / 2;
        byte[] d2 = new byte[length];
        int currentPos = 0;
        int i = 0;
        while (true) {
            int currentPos2 = currentPos + 1;
            d2[currentPos] = (byte) (Integer.parseInt(hexString.substring(i, i + 2), 16) & 255);
            i += 2;
            if (currentPos2 >= length) {
                return d2;
            }
            currentPos = currentPos2;
        }
    }

    public static String MD5(String strSrc) {
        byte[] bt = strSrc.getBytes();
        String strDes = "empty";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(bt);
            return bytesToHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return strDes;
        }
    }
}
