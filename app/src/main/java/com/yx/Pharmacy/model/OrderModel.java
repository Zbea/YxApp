package com.yx.Pharmacy.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by KID on 2018/7/31.
 * 订单模型
 */

public class OrderModel implements Serializable {
    //        "data": {
//                "orderid": "201808010957203989105",
//                "itemid": 109,
//                "number": 3,
//                "discount": "0.00",
//                "price": "225.00",
//                "amount": "225.00",
//                "buyer_name": "何先生",
//                "buyer_address": "深圳市 龙华区龙塘新村启航大厦9A",
//                "buyer_mobile": "13267156156",
//                "add_time": "1533088640",
//                "note": "",
//                "payment_type": null,
//                "updatetime": 0,
//                "send_no": "",
//                "send_type": "",
//                "status": 8,
//                "cartcount": 3,
//                "limittime": 30,
//                "goodsList": [
//        {
//            "title": "古汉 养生精口服液10ml*30支 补气滋阴补肾失眠多梦健脑益肾安神补脑疲乏无力",
//                "mallid": 22,
//                "thumb": "http://120.79.62.56:8806/file/upload/201807/07/100951232.png.thumb.png",
//                "count": 3,
//                "gg": "10ml*30支",
//                "scqy": "启迪古汉集团衡阳中药有限公司",
//                "cartcount": 3,
//                "type": 1
//        }
//        ],
//        "gift": [],
//        "storename": "龙塘新村启航大厦"
//    }
    public boolean order_back;
    public String orderid;
    public int status;
    public String price;
    public int number;
    public List<Goods> goodsList;
    public List<Goods> gift;
    //详情
    public int itemid;
    public String discount;
    public String amount;
    public String buyer_name;
    public String buyer_address;
    public String buyer_mobile;
    public String add_time;
    public String note;
    public String payment_type;
    public String updatetime;
    public String send_no;
    public String send_type;
    public int cartcount;
    public String limittime;
    public String storename;
    public String invoice_url;
    //售后订单
    public String orderbackid;
    public String reason;
    public int count;
    public String addtime;
    public String oprice;
    public int type;
    public String recordnote;
    public String checknote;

    public static class Goods  implements Serializable {
        public String mallid;
        public String title;
        public String thumb;
        public int count;
        public String price;
        public String oprice;
        public String saleprice;
        public String gg;
        public String scqy;
        public int goodstype;
        //详情
        public int cartcount;
        public int type;
        //申请售后
        public String discount;
        public int levelid;
        public String disprice;
        public String status;
        public String presale;
        public String starttime;
        public String endtime;
        public boolean isSelect;//应用内自扩展参数，用于申请售后订单的多选



    }

    //    {
//        "order_back": true,
//            "orderid": "201807301026196759033",
//            "status": 2,
//            "price": "80.00",
//            "number": 1,
//            "goodsList": [{
//        "mallid": 24,
//                "title": "云南白药 气血康口服液 10支（气短心悸 体虚乏力 失眠多梦）",
//                "thumb": "http://120.79.62.56:8806/file/upload/201807/07/102326492.png.thumb.png",
//                "count": 1,
//                "price": "80.00",
//                "oprice": "86.00",
//                "saleprice": "80.00",
//                "gg": "10ML*10支",
//                "scqy": "云南白药集团文山七花有限责任公司",
//                "goodstype": 0
//    }
}