package com.itaem.crazy.shirodemo.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Utils {

    /**
     * 分转元
     * @param amount
     * @return
     */
    public static String fen2Yuan(String amount) {
        if (StringUtils.isEmpty(amount)) {
            return "0.00";
        } else {
            try {
                DecimalFormat df = new DecimalFormat("######0.00");
                return df.format(BigDecimal.valueOf(Long.valueOf(amount)).divide(new BigDecimal(100))).toString();
            } catch (Exception e) {
                return amount;
            }
        }
    }

    /**
     * 元转分
     * @param amount
     * @return
     */
    public static String yuan2Fen(String amount) {
        if (StringUtils.isEmpty(amount)) {
            return "0.00";
        } else {
            try {
                DecimalFormat df = new DecimalFormat("######0");
                return df.format(BigDecimal.valueOf(Double.valueOf(amount)).multiply(new BigDecimal(100))).toString();
            } catch (Exception e) {
                return amount;
            }
        }
    }

    /**
     * 生成id
     */
    public static String getId(){
        String id1 = System.currentTimeMillis()+"";
        id1 = id1.substring(id1.length()-3,id1.length());
        String id2 = System.currentTimeMillis()+"";
        id2 = id2.substring(id2.length()-3,id2.length());
        return id1+id2;
    }
}
