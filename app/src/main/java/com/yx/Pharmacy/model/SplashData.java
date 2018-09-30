package com.yx.Pharmacy.model;

/**
 * Created by KID on 2018/8/20.
 */
public class SplashData {
//    {
//        "code": "200",
//            "message": "ok",
//            "extention": "",
//            "alertmsg": "",
//            "data": {
//        "splash": {
//            "pushtype": 1,
//                    "weburl": "http://www.baidu.com",
//                    "image_src": "http://120.79.62.56:8806/file/upload/201808/03/092529112.png",
//                    "title": "启动页广告",
//                    "sort": "0"
//        }
//    }
//    }
    public SplashModel splash;

    public  static class SplashModel {
        public int pushtype;
        public String weburl;
        public String image_src;
        public String title;
        public String sort;
    }
}
