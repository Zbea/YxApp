package com.yx.Pharmacy.model;

import java.util.List;

/**
 * Created time  2018/7/16 0016
 * @author : mcx
 * 类描述 : 
 */

public class HomeAdvanceModel {

    /**
     * banner : [{"title":"何福林","image_src":"http://192.168.0.80:8806/file/upload/201806/26/152650421.png","image_url":""},{"title":"何福林",
     * "image_src":"http://192.168.0.80:8806/file/upload/201806/26/152624871.png","image_url":"baidu.com"},{"title":"何福林",
     * "image_src":"http://192.168.0.80:8806/file/upload/201806/26/152554431.png","image_url":"baidu.com"}]
     * gold : {"title":"何福林","image_src":"http://192.168.0.80:8806/file/upload/201806/26/152727121.png","image_url":""}
     * guid : [{"title":"新特药专区","image_src":"http://192.168.0.80:8806/file/upload/201806/26/153306351.png","image_url":""},{"title":"领券中心",
     * "image_src":"http://192.168.0.80:8806/file/upload/201806/26/153054701.png","image_url":""},{"title":"控销专区",
     * "image_src":"http://192.168.0.80:8806/file/upload/201806/26/152846441.png","image_url":""},{"title":"主推专区",
     * "image_src":"http://192.168.0.80:8806/file/upload/201806/26/152747151.png","image_url":""},{"title":"限购专区",
     * "image_src":"http://192.168.0.80:8806/file/upload/201806/26/153227891.png","image_url":""},{"title":"诊所专区",
     * "image_src":"http://192.168.0.80:8806/file/upload/201806/26/153332241.png","image_url":""},{"title":"处方药区",
     * "image_src":"http://192.168.0.80:8806/file/upload/201806/26/153400871.png","image_url":""},{"title":"近效期区",
     * "image_src":"http://192.168.0.80:8806/file/upload/201806/26/153427761.png","image_url":""},{"title":"0元特惠",
     * "image_src":"http://192.168.0.80:8806/file/upload/201806/26/153447301.png","image_url":""},{"title":"深圳专区",
     * "image_src":"http://192.168.0.80:8806/file/upload/201806/26/153511631.png","image_url":""}]
     */

    public GoldBean gold;
    public List<GoldBean> banner;
    public List<GoldBean>   guid;
    public GoldBean alert;
    public GoldBean custom;


    public static class GoldBean {
        /**
         * title : 何福林
         * image_src : http://192.168.0.80:8806/file/upload/201806/26/152727121.png
         * image_url : 
         */

        public String title;
        public String image_src;
        public int pushtype;
        public String activityname;
        public String levelid;
        public String type;

        public String weburl;
        public String goodsid;
        public String keyword;
        public String goodstype ;

    }
}
