package com.yx.Pharmacy.model;

/*
 *  @项目名：  android 
 *  @包名：    com.yx.Pharmacy.model
 *  @文件名:   ProductDetailModel
 *  @创建者:   CC
 *  @创建时间:  2018/7/21 14:50
 *  @描述：    TODO
 */

import java.util.List;

public class ProductDetailModel {

    /**
     * title : 古汉 养生精口服液10ml*30支 补气滋阴补肾失眠多梦健脑益肾安神补脑疲乏无力
     * qprice : 75
     * sprice : 0
     * itemid : 22
     * elite : 1
     * price : 75
     * scqy : 启迪古汉集团衡阳中药有限公司
     * endtime : 1546215400
     * note : 
     * gg : 10ml*30支
     * pzwh : 国药准字Z43020746
     * sales : 0
     * orders : 0
     * thumb : http://120.79.62.56:8806/file/upload/201807/07/100951232.png.thumb.png
     * thumb1 : http://120.79.62.56:8806/file/upload/201807/07/100956552.png.thumb.png
     * thumb2 : http://120.79.62.56:8806/file/upload/201807/07/101000522.png.thumb.png
     * catid : 93
     * oldprice : 78
     * content : <img src="https://img10.360buyimg.com/imgzone/jfs/t8113/199/921626103/304591/8e1858ca/59b102f3N298dc7b2.png" alt="" />
     * type : 1
     * starttime : 1531536660
     * pic : ["http://120.79.62.56:8806/file/upload/201807/07/100951232.png.thumb.png","http://120.79.62.56:8806/file/upload/201807/07/100956552.png.thumb.png","http://120.79.62.56:8806/file/upload/201807/07/101000522.png.thumb.png"]
     * product : [{"title":"汇仁 肾宝片 126片 男女肾虚亏阴阳虚 温阳补肾药品","price":"322.00","thumb":"http://120.79.62.56:8806/file/upload/201807/07/100047662.png.thumb.png","itemid":20},{"title":"济民可信 金水宝胶囊63粒 治疗阳痿早泄 补肾益肺 腰膝酸软药品","price":"38.00","thumb":"http://120.79.62.56:8806/file/upload/201807/07/100533212.png.thumb.png","itemid":21},{"title":"古汉 养生精口服液10ml*30支 补气滋阴补肾失眠多梦健脑益肾安神补脑疲乏无力","price":"78.00","thumb":"http://120.79.62.56:8806/file/upload/201807/07/100951232.png.thumb.png","itemid":22}]
     * couponinfo : 满100元减20元 满200元减40元 满300元减70元 
     */

    public String title;
    public String          qprice;
    public String          sprice;
    public String          itemid;
    public String          elite;
    public String          price;
    public String       disprice;
    public String       scqy;
    public String          endtime;
    public String       note;
    public String       gg;
    public String       pzwh;
    public String          sales;
    public String          sale;
    public String          salesacti;
    public String          orders;
    public String       thumb;
    public String       thumb1;
    public String       thumb2;
    public String       ph1;
    public String          catid;
    public String          oldprice;
    public String          oprice;
    public String       content;
    public String          type;
    public String          starttime;
    public String       couponinfo;
    public boolean       quehuo;
    public String       presale;
    public String       presalenote;
    public String       giftId;
    public List<String> pic;
    public boolean issave;
    public double max;
    public String flashmax;
    public String addmum;
    public String minimum;
    public boolean productLimit;
    public boolean flashLimit ;
    public int is_price;
    /**
     * title : 汇仁 肾宝片 126片 男女肾虚亏阴阳虚 温阳补肾药品
     * price : 322.00
     * thumb : http://120.79.62.56:8806/file/upload/201807/07/100047662.png.thumb.png
     * itemid : 20
     */

    public List<ProductBean> product;
    /**
     * gifts : a:1:{i:0;a:3:{s:1:"n";i:0;s:1:"g";i:0;s:3:"pid";s:0:"";}}
     * amount : 1000000
     * levelnote : ● 7天内可使用满100优惠10件价格
     * birthtime : 1533139200
     * birthend : 1533139200
     * validtime : 1533139200
     * validend : 1533139200
     */

    public String gifts;
    public String    amount;
    public String levelnote;
    public String    birthtime;
    public String    birthend;
    public String    validtime;
    public String    validend;

    public static class ProductBean {
        public String title;
        public String price;
        public String thumb;
        public String    itemid;
    }
}
