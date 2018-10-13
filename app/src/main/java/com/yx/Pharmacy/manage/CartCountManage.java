package com.yx.Pharmacy.manage;

/**
  * Author: Zbea
  * Date: 2018/9/29 16:57
  * Description: 每件商品的最大购买量管理
 */
public class CartCountManage {


   private static CartCountManage mCartCountManage= null;
   private CartCountManageListener mCartCountManageListener;
   private int count;

   private CartCountManage() {

   }

   public static CartCountManage newInstance()
   {
       if (mCartCountManage==null)
       {
           mCartCountManage=new CartCountManage();
       }
       return mCartCountManage;
   }

   public void refresh(int max)
   {
       count=max;
       if (mCartCountManageListener!=null)
           mCartCountManageListener.onRefresh(max);
   }

    public void setCount(int count)
    {
        this.count=count;
    }

    public int getCount()
    {
        return count;
    }


    public CartCountManageListener setCartCountManageListener(CartCountManageListener CartCountManageListener)
   {
       if (CartCountManageListener!=null)
           mCartCountManageListener=CartCountManageListener;
       return mCartCountManageListener;
   }

   public interface CartCountManageListener
   {
       void onRefresh(int max);
   }
   
}
