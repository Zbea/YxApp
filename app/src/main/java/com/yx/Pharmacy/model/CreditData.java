package com.yx.Pharmacy.model;

import java.util.List;

/**
 * Created by KID on 2018/8/6.
 */

public class CreditData {

    public String credit;
    public List<CreditModel>creditlist;

    public static class CreditModel {
        public int amount;
        public String addtime;
        public String note;
        public int type;
    }

}
