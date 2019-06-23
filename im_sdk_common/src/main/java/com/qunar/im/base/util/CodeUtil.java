package com.qunar.im.base.util;

import android.util.Base64;
import java.io.UnsupportedEncodingException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class CodeUtil {
    public static final String PASSWORD_ENC_SECRET = "mythmayor";

    public static String decodeString(String str) {
        if (str == null) {
            return "转换失败";
        }
        try {
            return new String(pack(str), "gbk");
        } catch (UnsupportedEncodingException e) {
            return "转换失败";
        }
    }

    public static byte[] pack(String str) {
        int nibbleshift = 4;
        int position = 0;
        byte[] output = new byte[((str.length() / 2) + (str.length() % 2))];
        for (char v : str.toCharArray()) {
            byte n = (byte) v;
            if (n >= (byte) 48 && n <= (byte) 57) {
                n = (byte) (n - 48);
            } else if (n < (byte) 65 || n > (byte) 70) {
                if (n >= (byte) 97 && n <= (byte) 102) {
                    n = (byte) (n - 87);
                }
            } else {
                n = (byte) (n - 55);
            }
            output[position] = (byte) (output[position] | (n << nibbleshift));
            if (nibbleshift == 0) {
                position++;
            }
            nibbleshift = (nibbleshift + 4) & 7;
        }
        return output;
    }

    public static String decodeShiLiu(String str) {
        if (str == null) {
            return "转换失败";
        }
        try {
            return unpack(str.getBytes("GBK"));
        } catch (Exception e) {
            return "转换失败";
        }
    }

    public static String unpack(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        for (byte b : bytes) {
            String hv = Integer.toHexString(b & 255);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static String encryptPassword(String clearText) {
        try {
            SecretKey key = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec("mythmayor".getBytes("UTF-8")));
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(1, key);
            return Base64.encodeToString(cipher.doFinal(clearText.getBytes("UTF-8")), 0);
        } catch (Exception e) {
            return clearText;
        }
    }

    public static String decryptPassword(String encryptedPwd) {
        try {
            SecretKey key = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec("mythmayor".getBytes("UTF-8")));
            byte[] encryptedWithoutB64 = Base64.decode(encryptedPwd, 0);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(2, key);
            return new String(cipher.doFinal(encryptedWithoutB64));
        } catch (Exception e) {
            return encryptedPwd;
        }
    }
}
