package com.yx.Pharmacy.model;

/**
 * Created by KID on 2018/7/17.
 */

public class DrugModel {

    private String title;
    private String price;
    private String oldprice;
    private String sprice;
    private String levelnote;
    private int minimun;
    private int maxmun;
    private long starttime;
    private long endtime;
    private String gg;
    private String scqy;//公司
    private int sales;//已售
    private String thumb;//缩略图
    private int itemid;
    private String amount;
    private int salesacti;
    private String note;
    private int levelid;
    private int type;
    private boolean quehuo;
    private String     presale; // 预售 0:不是预售  1 预售
    private String     presalenote;// 预售说明
    public int activityCount ;
    public int salesCount;

    public String getLevelnote() {
        return levelnote;
    }

    public void setLevelnote(String levelnote) {
        this.levelnote = levelnote;
    }

    public String getPresale() {
        return presale;
    }

    public void setPresale(String presale) {
        this.presale = presale;
    }

    public String getPresalenote() {
        return presalenote;
    }

    public void setPresalenote(String presalenote) {
        this.presalenote = presalenote;
    }

    public boolean isQuehuo() {
        return quehuo;
    }

    public void setQuehuo(boolean quehuo) {
        this.quehuo = quehuo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOldprice() {
        return oldprice;
    }

    public void setOldprice(String oldprice) {
        this.oldprice = oldprice;
    }

    public String getSprice() {
        return sprice;
    }

    public void setSprice(String sprice) {
        this.sprice = sprice;
    }

    public int getMinimun() {
        return minimun;
    }

    public void setMinimun(int minimun) {
        this.minimun = minimun;
    }

    public int getMaxmun() {
        return maxmun;
    }

    public void setMaxmun(int maxmun) {
        this.maxmun = maxmun;
    }

    public long getStarttime() {
        return starttime;
    }

    public void setStarttime(long starttime) {
        this.starttime = starttime;
    }

    public long getEndtime() {
        return endtime;
    }

    public void setEndtime(long endtime) {
        this.endtime = endtime;
    }

    public String getGg() {
        return gg;
    }

    public void setGg(String gg) {
        this.gg = gg;
    }

    public String getScqy() {
        return scqy;
    }

    public void setScqy(String scqy) {
        this.scqy = scqy;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getSalesacti() {
        return salesacti;
    }

    public void setSalesacti(int salesacti) {
        this.salesacti = salesacti;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getLevelid() {
        return levelid;
    }

    public void setLevelid(int levelid) {
        this.levelid = levelid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


}
