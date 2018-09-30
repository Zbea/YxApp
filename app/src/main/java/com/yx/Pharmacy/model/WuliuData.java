package com.yx.Pharmacy.model;

import java.util.List;

/**
 * Created by KID on 2018/8/14.
 */

public class WuliuData {

//            "data": {
//                "send_type": "京东物流",
//                "send_no": "1179978374387874",
//                "translatetel": "950616",
//                "translatedetail": [
//        {
//            "translate": "货物已由本人签收，感谢您选择京东物流！",
//                "translatetime": "2018/08/14 12:32:38"
//        },
//        {
//            "translate": "配送员开始配送，请您准备收货，配送员，黄文辉，手机号，18680169802",
//                "translatetime": "2018/08/14 08:12:38"
//        },
//        {
//            "translate": "货物已分配，等待配送",
//                "translatetime": "2018/08/14 07:42:11"
//        },
//        {
//            "translate": "货物已交付京东物流",
//                "translatetime": "2018/08/13 21:15:48"
//        }
//        ]
//    }
    public String send_type;
    public String send_no;
    public String translatetel;
    public List<WuliModel>translatedetail;

    public static class WuliModel {
        public String translate;
        public String translatetime;
    }


}
