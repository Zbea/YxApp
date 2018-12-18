package com.yx.Pharmacy.manage;

import com.yx.Pharmacy.model.UrlBean;

/**
  * Author: Zbea
  * Date: 2018/9/29 16:57
  * Description:本地url
 */
public class LocalUrlManage {


   private static LocalUrlManage mCartCountManage= null;
   private UrlBean urlBean;

   private LocalUrlManage() {

   }

   public static LocalUrlManage newInstance()
   {
       if (mCartCountManage==null)
       {
           mCartCountManage=new LocalUrlManage();
       }
       return mCartCountManage;
   }


    public void setCount(UrlBean urlBean)
    {
        this.urlBean=urlBean;
    }

    public UrlBean getUrlBean()
    {
        return urlBean;
    }



   
}
