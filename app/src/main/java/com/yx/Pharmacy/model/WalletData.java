package com.yx.Pharmacy.model;

import java.util.List;

/**
 * Created by KID on 2018/8/6.
 */

public class WalletData {

    public String money;
    public List<WallatModel> list;

    public static class WallatModel {
        public String title;
        public String fee;
        public String paytime;
        public int type;
    }

}
