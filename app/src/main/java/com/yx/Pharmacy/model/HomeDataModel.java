package com.yx.Pharmacy.model;

import java.util.List;

/**
 * Created time  2018/7/16 0016
 * @author : mcx
 * 类描述 : 
 */

public class HomeDataModel {

    /**
     * activityname : 秒杀专区
     * thumb : http://192.168.0.80:8806/file/upload/201806/27/203921421.png
     * levelid : 2
     * type : 1
     * goodlists : [{"title":"汇仁 肾宝片 126片 男女肾虚亏阴阳虚 温阳补肾药品","price":300,"oldprice":322,"qprice":300,"sprice":0,"minimum":1,"maxmum":10000,"endtime":1546214400,
     * "gg":"0.7g*126片*1","scqy":"江西汇仁药业股份有限公司","sales":0,"thumb":"http://192.168.0.80:8806/file/upload/201807/07/100047662.png.thumb.png","itemid":20,
     * "amount":1000000,"salesacti":10000,"note":"","levelid":2,"starttime":1531565460},{"title":"济民可信 金水宝胶囊63粒 治疗阳痿早泄 补肾益肺 腰膝酸软药品","price":35,"oldprice":38,
     * "qprice":35,"sprice":0,"minimum":1,"maxmum":10000,"endtime":1546213400,"gg":"0.33g*63粒","scqy":"江西济民可信金水宝制药有限公司","sales":0,
     * "thumb":"http://192.168.0.80:8806/file/upload/201807/07/100533212.png.thumb.png","itemid":21,"amount":1000000,"salesacti":10000,"note":"","levelid":2,
     * "starttime":1531536660},{"title":"古汉 养生精口服液10ml*30支 补气滋阴补肾失眠多梦健脑益肾安神补脑疲乏无力","price":75,"oldprice":78,"qprice":75,"sprice":0,"minimum":1,"maxmum":10000,
     * "endtime":1546215400,"gg":"10ml*30支","scqy":"启迪古汉集团衡阳中药有限公司","sales":0,"thumb":"http://192.168.0.80:8806/file/upload/201807/07/100951232.png.thumb.png",
     * "itemid":22,"amount":1000000,"salesacti":10000,"note":"","levelid":2,"starttime":1531536660}]
     */

    public String activityname;
    public String              thumb;
    public String                 levelid; // 活动的id
    public String                 type; // 1-秒杀 2-特价 3-满减 9-控销
    public List<GoodlistsBean> goodlists;

    public static class GoodlistsBean {
        /**
         * title : 汇仁 肾宝片 126片 男女肾虚亏阴阳虚 温阳补肾药品
         * price : 300
         * oldprice : 322
         * qprice : 300
         * sprice : 0
         * minimum : 1
         * maxmum : 10000
         * endtime : 1546214400
         * gg : 0.7g*126片*1
         * scqy : 江西汇仁药业股份有限公司
         * sales : 0
         * thumb : http://192.168.0.80:8806/file/upload/201807/07/100047662.png.thumb.png
         * itemid : 20
         * amount : 1000000
         * salesacti : 10000
         * note : 
         * levelid : 2
         * starttime : 1531565460
         */

        public String title;
        public String    price;
        public String    oldprice;
        public String    levelnote;
        public String    qprice;
        public String    sprice;
        public String    disprice;
        public String    minimum;
        public String    maxmum;
        public String    validend;
        public long    endtime;
        public String gg;
        public String scqy;
        public String    sales;
        public String    sale;
        public String thumb;
        public String    itemid;
        public String    amount;
        public String    salesacti;
        public String note;
        public String    levelid;
        public String     type;
        public String    starttime;
        public String     presale; // 预售 0:不是预售  1 预售
        public String     presalenote;// 预售说明
    }
}
