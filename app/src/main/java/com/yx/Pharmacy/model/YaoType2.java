package com.yx.Pharmacy.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by KID on 2018/7/14.
 * 药类  2级类别
 */

public class YaoType2 implements Parcelable {
    public String catname;//类别名称
    public int  catid;//类别id
    public String thumb; //缩略图

    public YaoType2() {
    }

    public YaoType2(String catname, int catid, String thumb) {
        this.catname = catname;
        this.catid = catid;
        this.thumb = thumb;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        //等于将数据映射到Parcel中去
        dest.writeString(catname);
        dest.writeInt(catid);
        dest.writeString(thumb);

    }
    public static final Creator<YaoType2>CREATOR =new Creator<YaoType2>(){


        @Override
        public YaoType2 createFromParcel(Parcel source) {
            return new YaoType2(source);
        }

        @Override
        public YaoType2[] newArray(int size) {
            return new YaoType2[size];
        }
    };
    public YaoType2(Parcel in) {
        catname = in.readString();
        catid = in.readInt();
        thumb = in.readString();
    }

}
