package com.yx.Pharmacy.model;

/*
 *  @项目名：  android 
 *  @包名：    com.yx.Pharmacy.model
 *  @文件名:   AddressModel
 *  @创建者:   CC
 *  @创建时间:  2018/7/25 22:38
 *  @描述：    TODO
 */

import java.util.List;

public class AddressModel {

    public String areaid;
    public String areaname;

    public List<ChildBean> child;

    public static class ChildBean {
        public String areaid;
        public String areaname;
        public List<CountyBean> child;

    }

    public static class CountyBean {
        public String areaid;
        public String areaname;
    }
}
