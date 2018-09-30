package com.yx.Pharmacy.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by fatchao
 * 日期  2017-07-24.
 * 邮箱  fat_chao@163.com
 */

public class RightBean implements Parcelable {
    private String titleName;
    private String catname;
    private String tag;
    private boolean isTitle;
    private String thumb;
    private int catid;

    public RightBean(String catname) {
        this.catname = catname;
    }

    protected RightBean(Parcel in) {
        titleName=in.readString();
        catname = in.readString();
        tag = in.readString();
        isTitle = in.readByte() != 0;
        thumb = in.readString();
        catid=in.readInt();
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public static final Creator<RightBean> CREATOR = new Creator<RightBean>() {
        @Override
        public RightBean createFromParcel(Parcel in) {
            return new RightBean(in);
        }

        @Override
        public RightBean[] newArray(int size) {
            return new RightBean[size];
        }
    };


    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isTitle() {
        return isTitle;
    }

    public void setTitle(boolean title) {
        isTitle = title;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public int getCatid() {
        return catid;
    }

    public void setCatid(int catid) {
        this.catid = catid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titleName);
        dest.writeString(catname);
        dest.writeString(tag);
        dest.writeByte((byte) (isTitle ? 1 : 0));
        dest.writeString(thumb);
        dest.writeInt(catid);
    }
}
