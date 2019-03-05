package com.yx.Pharmacy.model;

import java.util.List;

/**
 * Created time  2018/8/2 0002
 * @author : mcx
 * 类描述 : 
 */

public class ShopCartModel {

    public String notice;
    public String orderlimit;
    public List<DiscountListBean> discountList;
    public List<ShopCartListBean> shopCartList;
    public List<CouponListBean>                couponList;

    public static class DiscountListBean {
        /**
         * limit : 5000
         * discount : 9.8
         */

        public String limit;
        public String discount;
    }

    public static class ShopCartListBean {

        public String activitytype;
        public String               activityname;
        public boolean isUnvalid;
        public String                  type;
        public String                  activityid;
        public String               sort;
        public List<CouponListBean> couponList;
        public List<GoodsBean>      goods;
        public String alldiscount;
        public boolean isJoin=true;
        public boolean isSelectCoupon;
    }


    public static class CouponListBean {
        /**
         * couponcontent : 满200元减40元
         * couponid : 7
         * couponprice : 40
         * couponlimit : 200
         * couponmodel : 1
         * coupontype : 2
         * couponstate : 0
         * couponscene : 秒杀专区
         * couponovertime : 有效期至2019-08-06
         * discountprice : 40
         */

        public String couponcontent;
        public String    couponid;
        public String    couponprice;
        public String couponlimit;
        public String    couponmodel;
        public String    coupontype;
        public String    couponstate;
        public String couponscene;
        public String couponovertime;
        public String    discountprice;
        public String    coupondiscount;
        public String    credit;
        public boolean     isSelectCoupon;
        public boolean     couponEnable;
        public boolean isFrist;
    }


    public static class GoodsBean {
        /**
         * title : 济民可信 金水宝胶囊63粒 治疗阳痿早泄 补肾益肺 腰膝酸软药品
         * thumb : http://120.79.62.56:8806/file/upload/201807/07/100533212.png.thumb.png
         * gg : 0.33g*63粒
         * scqy : 江西济民可信金水宝制药有限公司
         * cartcount : 5
         * minimum : 1
         * addmum : 1
         * goodsid : 21
         * max : 1000000
         * quehuo : false
         * price : 35
         * endtimes : 1546213400
         */

        public String title;
        public String  thumb;
        public String  gg;
        public String  scqy;
        public String  activityid;
        public String     cartcount;
        public String     minimum;
        public String     addmum;
        public String     goodsid;
        public String     goodsType;
        public String     max;
        public String quehuo;
        public String     price;
        public String     endtimes;
        public String     validtime;
        public String     starttime;
        public String     limitnum;
        public String     oprice;
        public String     disprice;
        public String     usefulprice;
        public String     presale;
        public String     presalenote;
        public String     isvalid;
        public List<String>     info;
        public boolean     isSelect;
        public List<CouponListBean> couponList;
        public int is_price;
        public boolean isJoin=true;
        public boolean isSelectCoupon;
        /**
         * limit : 10
         * give : 1
         */

        public List<GiftListBean> giftList;



        public static class GiftListBean {
            public String limit;
            public String give;
            public GiftInfoBean       giftInfo;

            public static class GiftInfoBean {
                public String goodsname;
                public String    goodsid;
                public String goodsthumb;
                public String goodsgg;
            }
        }
    }
}
