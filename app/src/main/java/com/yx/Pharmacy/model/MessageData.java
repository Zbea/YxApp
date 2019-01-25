package com.yx.Pharmacy.model;

import java.util.List;

/**
 * Created by KID on 2018/8/10.
 */

public class MessageData {
    //        "data":{
//        "transaction":0,
//                "system":0,
//                "activity":0,
//                "notification":0,
//                "message": []
//         }
    public int transaction;
    public int system;
    public int activity;
    public int notification;
    public List<MessageModel>message;

    public  static class MessageModel {
        //    {
//        "itemid": 15,
//            "content": "测试123456",
//            "addtime": 1532774892,
//            "isread": 1,
//            "pushtype": 1,
//            "pushdata": "21",
//            "areaid": 2,
//            "banner": "http://120.79.62.56:8806/file/upload/201806/26/152624871.png",
//            "weburl": "https://www.baidu.com",
//            "activityname": "何福林",
//            "introduce": ""
//    }
        public int itemid;
        public String content;
        public String addtime;
        public int isread;
        public int pushtype;
        public String pushdata;
        public int areaid;
        public String banner;
        public String weburl;
        public String activityname;
        public String introduce;
        public String img_url;
        public String count;
        public String status;
//        public String order_time;
//        public String backtime;
        public String goodsid;

        public String thumb;
        public String title;
        public String price;
        public String scqy;
        public String gg;

        public String levelid;
        public String type;
        public int is_imgtext;

    }

}
