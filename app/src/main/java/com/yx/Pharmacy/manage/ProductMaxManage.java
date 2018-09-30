package com.yx.Pharmacy.manage;

import com.yx.Pharmacy.receiver.ReceiverDialogManage;

import org.json.JSONObject;

 /**
   * Author: Zbea
   * Date: 2018/9/29 16:57
   * Description: 每件商品的最大购买量管理
  */
public class ProductMaxManage {


    private static ProductMaxManage mProductMaxManage= null;
    private ProductMaxManageListener mProductMaxManageListener;

    private ProductMaxManage() {

    }

    public static ProductMaxManage newInstance()
    {
        if (mProductMaxManage==null)
        {
            mProductMaxManage=new ProductMaxManage();
        }
        return mProductMaxManage;
    }

    public void refresh(int max)
    {
        if (mProductMaxManageListener!=null)
            mProductMaxManageListener.onRefresh(max);
    }


    public ProductMaxManageListener setProductMaxManageListener(ProductMaxManageListener ProductMaxManageListener)
    {
        if (ProductMaxManageListener!=null)
            mProductMaxManageListener=ProductMaxManageListener;
        return mProductMaxManageListener;
    }

    public interface ProductMaxManageListener
    {
        void onRefresh(int max);
    }
    
}
