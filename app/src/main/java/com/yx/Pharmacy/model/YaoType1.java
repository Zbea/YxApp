package com.yx.Pharmacy.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KID on 2018/7/14.
 * 药类  1级类别
 */

public class YaoType1  implements Parcelable {
    public String catname;//类别名称
    public int catid; //类别id
    public List<YaoType2> child=new ArrayList<>();//2级分类
    public YaoType1() {
    }
    public YaoType1(String catname, int catid, List<YaoType2> child) {
        this.catname = catname;
        this.catid = catid;
        this.child = child;
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
        dest.writeTypedList(child);

    }

    public static final Creator<YaoType1>CREATOR =new Creator<YaoType1>(){


        @Override
        public YaoType1 createFromParcel(Parcel source) {
            return new YaoType1(source);
        }

        @Override
        public YaoType1[] newArray(int size) {
            return new YaoType1[size];
        }
    };

    public YaoType1(Parcel source){
        //将映射在Parcel对象中的数据还原回来
        //警告，这里的顺序一定要和writeToParcel中定义的顺序一致才行！

        catname=source.readString();
        catid=source.readInt();
        if(child==null){
            child=new ArrayList<>();
        }
        source.readTypedList(child,YaoType2.CREATOR);
    }

}
