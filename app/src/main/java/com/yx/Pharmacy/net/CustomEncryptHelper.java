package com.yx.Pharmacy.net;

import android.text.TextUtils;

import java.util.Random;

/**
 * Created by Administrator on 2017/8/4.
 * 加密解密方法
 */

public class CustomEncryptHelper {
    public static String Encrypt(String sSource) {
        if (TextUtils.isEmpty(sSource)) {
            return "";
        }
        int li_len, i, li_asc, li_rand, li_head;
        String ls_i, ls_code = "";
        li_len = sSource.length();
        long nowTicks = System.nanoTime();
        Random rdm1 = new Random(nowTicks);
        double nextDouble = rdm1.nextDouble();
        li_head = (int) (nextDouble * 10);
        if (li_head == 0)
            li_head = 1;
        for (i = 0; i < li_len; i++) {

            Random rdm2 = new Random(nowTicks);
            int rand2 = (int) (rdm2.nextDouble() * 94);
            if (rand2 == 0)
                rand2 = 1;
            li_rand = rand2 + 32;
            li_asc = sSource.toCharArray()[i];

            if (li_asc + i + li_head > 126) {
                if (li_rand % 2 == 1)
                    li_rand = li_rand + 1;
                ls_i = String.valueOf(((char) (li_rand))) + String.valueOf(((char) (li_asc - i - li_head)));
            } else {
                if (li_rand % 2 == 0)
                    li_rand = li_rand + 1;
                ls_i = String.valueOf(((char) (li_rand))) + String.valueOf(((char) (li_asc + i + li_head)));
            }
            ls_code = ls_code + ls_i;

        }
        Random rdm3 = new Random(nowTicks);
        int rand1 = (int) (rdm3.nextDouble() * 9);
        if (rand1 == 0)
            rand1 = 1;
        ls_code = String.valueOf(((char) (rand1 * 10 + li_head + 40))) + ls_code;

        return ls_code;
    }

    public static String Decrypt(String sSource) {
        if (sSource == null)
            return null;
        if (sSource.equals(""))
            return "";
        int li_len, i, li_asc;
        String ls_i, ls_code = "";

        int li_ret;
        li_len = sSource.length();
        ls_code = "";
        li_ret = sSource.toCharArray()[0] % 10;
        for (i = 2; i < li_len; i = i + 2) {
            li_asc = sSource.toCharArray()[i];
            if (sSource.toCharArray()[i - 1] % 2 == 0) {
                ls_i = String.valueOf((char) (li_asc + (i - 1) / 2 + li_ret));
            } else {
                ls_i = String.valueOf(((char) (li_asc - (i - 1) / 2 - li_ret)));
            }
            ls_code = ls_code + ls_i;
        }

        return ls_code;
    }

}
