package com.qunar.im.base.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

public class BusinessUtils {
    public static boolean checkPhoneNumber(String number) {
        return Pattern.compile("\\d{7}\\d.").matcher(number).matches();
    }

    public static boolean checkEmail(String email) {
        if (checkChiness(email)) {
            return false;
        }
        return Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$").matcher(email).matches();
    }

    public static boolean checkChiness(String str) {
        return str.matches("^[一-龥]+$");
    }

    public static double sub(double v1, double v2) {
        return new BigDecimal(Double.toString(v1)).subtract(new BigDecimal(Double.toString(v2))).doubleValue();
    }

    public static String formatDoublePrice(double value) {
        return new DecimalFormat("##########.##").format(value);
    }
}
