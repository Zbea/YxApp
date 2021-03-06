package com.yx.Pharmacy.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.manage.MessageIsReadNumManage;
import com.yx.Pharmacy.model.MessageData;
import com.yx.Pharmacy.util.DateUtil;
import com.yx.Pharmacy.util.GlideUtil;

import java.util.List;

/**
 * Created by KID on 2018/7/17.
 */

public class MessageAdapter extends BaseQuickAdapter<MessageData.MessageModel,BaseViewHolder> {
    private Context context;
    private MessageClickListener listener;
    private static final int HAS_Read=1;
    public interface MessageClickListener{
        void goWebActivity(String weburl);//跳转活动模块
        void goProductDetail(String pid); //跳转商品详情
        void goOrderDetail(String orderid);//跳转订单详情
        void goBackOrderDetail(String orderbackid); //跳转售后订单详情
        void goMenDianInfo(String itemid); //跳转门店信息
        void goZoneActivity(int type, int levelid); //跳转专区活动
        void godata7Activity(String levelid, String type);//push=7 活动
        void readMessage(String itemid, int position);

    }
    public void setListener(MessageClickListener listener){
        this.listener=listener;
    }


    public MessageAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<MessageData.MessageModel> data) {
        super(layoutResId, data);
        this.context=context;
    }
    //pushtype说明：1.活动类型消息  url广告 2.商品活动类型  单个商品的活动 3.到货通知（接口处理）
    // 4.订单的各种状态的处理消息 5售后订单的处理消息 6.门店审核的处理消息
    @Override
    protected void convert(final BaseViewHolder helper, final MessageData.MessageModel item) {
        if(item.pushtype==1){//优惠促销 活动类型消息  url广告
            helper.getView(R.id.ll_type_wuliu).setVisibility(View.GONE);
            helper.getView(R.id.ll_type_system_notice).setVisibility(View.GONE);
            helper.getView(R.id.ll_type_youhui).setVisibility(View.VISIBLE);
            helper.getView(R.id.ll_type_arrive_notice).setVisibility(View.GONE);
            ImageView iv_youhui=helper.getView(R.id.iv_youhui);
            GlideUtil.loadImg(context,item.banner,iv_youhui);
            helper.setText(R.id.tv_youhui_name,item.activityname);
            helper.setText(R.id.tv_youhui_time, DateUtil.formatyyyyMMddHHmmss(Long.valueOf(item.addtime+"000")));
            helper.setText(R.id.tv_youhui_bottom_desc,"点击查看活动详情");
            helper.getView(R.id.iv_youhui_isread).setVisibility(item.isread==1?View.GONE:View.VISIBLE);
        }else if(item.pushtype==2){//优惠促销 商品活动类型  单个商品的活动
            helper.getView(R.id.ll_type_wuliu).setVisibility(View.GONE);
            helper.getView(R.id.ll_type_system_notice).setVisibility(View.GONE);
            helper.getView(R.id.ll_type_youhui).setVisibility(View.VISIBLE);
            helper.getView(R.id.ll_type_arrive_notice).setVisibility(View.GONE);
            ImageView iv_youhui=helper.getView(R.id.iv_youhui);
            GlideUtil.loadImg(context,item.banner,iv_youhui);
            helper.setText(R.id.tv_youhui_name,"单品优惠");
            helper.setText(R.id.tv_youhui_time, DateUtil.formatyyyyMMddHHmmss(Long.valueOf(item.addtime+"000")));
            helper.setText(R.id.tv_youhui_bottom_desc,"点击查看商品详情");
            helper.getView(R.id.iv_youhui_isread).setVisibility(item.isread==1?View.GONE:View.VISIBLE);
        }else if(item.pushtype==3){//到货通知
            helper.getView(R.id.ll_type_wuliu).setVisibility(View.GONE);
            helper.getView(R.id.ll_type_system_notice).setVisibility(View.GONE);
            helper.getView(R.id.ll_type_youhui).setVisibility(View.GONE);
            helper.getView(R.id.ll_type_arrive_notice).setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_arrive_notice_name,"到货提醒");
            helper.setText(R.id.tv_arrive_note_time,DateUtil.formatyyyyMMddHHmmss(Long.valueOf(item.addtime+"000")));
            ImageView iv_arrive_notice=helper.getView(R.id.iv_arrive_notice);
            GlideUtil.loadImg(context,item.thumb,iv_arrive_notice,R.drawable.fhd);
            helper.setText(R.id.tv_arrive_notice_title,item.title);
            helper.setText(R.id.tv_arrive_notice_company,item.scqy);
            helper.setText(R.id.tv_arrive_notice_price,item.price);
            helper.setText(R.id.tv_arrive_notice_gg,item.gg);
            helper.setText(R.id.tv_arrive_notice_bottom_desc,"点击产看商品详情");
            helper.getView(R.id.iv_arrive_notice_isread).setVisibility(item.isread==1?View.GONE:View.VISIBLE);

        }else if(item.pushtype==4){//4.订单的各种状态的处理消息，根据status
            helper.getView(R.id.ll_type_wuliu).setVisibility(View.VISIBLE);
            helper.getView(R.id.ll_type_system_notice).setVisibility(View.GONE);
            helper.getView(R.id.ll_type_youhui).setVisibility(View.GONE);
            helper.getView(R.id.ll_type_arrive_notice).setVisibility(View.GONE);
            ImageView iv_wuliu=helper.getView(R.id.iv_wuliu);
            GlideUtil.loadImg(context,item.img_url,iv_wuliu);
            helper.setText(R.id.tv_wuliu_num,item.count+"");
            helper.setText(R.id.tv_wuliu_name,"订单通知");
            helper.setText(R.id.tv_wuliu_time, DateUtil.formatyyyyMMddHHmmss(Long.valueOf(item.addtime+"000")));
            helper.setText(R.id.tv_wuliu_left2, "下单时间");
            helper.setText(R.id.tv_wuliu_right2, DateUtil.formatyyyyMMddHHmmss(Long.valueOf(item.order_time+"000")));
            helper.setText(R.id.tv_wuliu_left3, "订单编号");
            helper.setText(R.id.tv_wuliu_right3, item.pushdata);
            helper.setText(R.id.tv_bottom_desc,"点击查看订单详情");
            helper.getView(R.id.iv_wuliu_isread).setVisibility(item.isread==1?View.GONE:View.VISIBLE);
            if(item.status==1){
                helper.setText(R.id.tv_wuliu_state,"待付款");
            }else if(item.status==2){//待发货
                helper.setText(R.id.tv_wuliu_state,"待发货");
            }else if(item.status==9){
                helper.setText(R.id.tv_wuliu_state,"已完成");
            }else if(item.status==8){
                helper.setText(R.id.tv_wuliu_state,"已取消");
            }else if(item.status==7||item.status==3){
                helper.setText(R.id.tv_wuliu_state,"待收货");
            }
        }else if(item.pushtype==5){//5.售后订单的各种状态的处理消息，根据status
            helper.getView(R.id.ll_type_wuliu).setVisibility(View.VISIBLE);
            helper.getView(R.id.ll_type_system_notice).setVisibility(View.GONE);
            helper.getView(R.id.ll_type_youhui).setVisibility(View.GONE);
            helper.getView(R.id.ll_type_arrive_notice).setVisibility(View.GONE);
            ImageView iv_wuliu=helper.getView(R.id.iv_wuliu);
            GlideUtil.loadImg(context,item.img_url,iv_wuliu);
            helper.setText(R.id.tv_wuliu_num,item.count+"");
            helper.setText(R.id.tv_wuliu_name,"售后订单");
            helper.setText(R.id.tv_wuliu_time, DateUtil.formatyyyyMMddHHmmss(Long.valueOf(item.addtime+"000")));
            helper.setText(R.id.tv_wuliu_left2, "下单时间");
            helper.setText(R.id.tv_wuliu_right2, DateUtil.formatyyyyMMddHHmmss(Long.valueOf(item.backtime+"000")));
            helper.setText(R.id.tv_wuliu_left3, "订单编号");
            helper.setText(R.id.tv_wuliu_right3, item.pushdata);
            helper.setText(R.id.tv_bottom_desc,"点击查看订单详情");
            helper.getView(R.id.iv_wuliu_isread).setVisibility(item.isread==1?View.GONE:View.VISIBLE);
            if(item.status==0||item.status==1){
                helper.setText(R.id.tv_wuliu_state,"审核中");
            }else if(item.status==6){
                helper.setText(R.id.tv_wuliu_state,"已撤销");
            }else if(item.status==7){
                helper.setText(R.id.tv_wuliu_state,"处理中");
            }else if(item.status==8){
                helper.setText(R.id.tv_wuliu_state,"审核失败");
            } else {
                helper.setText(R.id.tv_wuliu_state,"已完成");
            }
        }
        else if(item.pushtype==6){//6系统通知（门店审核的处理消息）点击查看门店详情
            helper.getView(R.id.ll_type_wuliu).setVisibility(View.GONE);
            helper.getView(R.id.ll_type_system_notice).setVisibility(View.VISIBLE);
            helper.getView(R.id.ll_type_youhui).setVisibility(View.GONE);
            helper.getView(R.id.ll_type_arrive_notice).setVisibility(View.GONE);
            helper.setText(R.id.tv_system_notice_name,"系统通知");
            helper.setText(R.id.tv_system_notice_time,DateUtil.formatyyyyMMddHHmmss(Long.valueOf(item.addtime+"000")));
            helper.setText(R.id.tv_system_notice_content,item.content);
            helper.setText(R.id.tv_bootom_desc,"点击查看门店详情");
            helper.getView(R.id.rl_system_notice_bottom).setVisibility(View.VISIBLE);
            helper.getView(R.id.iv_system_notice_isread).setVisibility(item.isread==1?View.GONE:View.VISIBLE);
        } else if(item.pushtype==7){//7系统通知(无底部)
//            helper.getView(R.id.ll_type_wuliu).setVisibility(View.GONE);
//            helper.getView(R.id.ll_type_system_notice).setVisibility(View.VISIBLE);
//            helper.getView(R.id.ll_type_youhui).setVisibility(View.GONE);
//            helper.getView(R.id.ll_type_arrive_notice).setVisibility(View.GONE);
//            helper.setText(R.id.tv_system_notice_name,"系统通知");
//            helper.setText(R.id.tv_system_notice_time,DateUtil.formatyyyyMMddHHmmss(Long.valueOf(item.addtime+"000")));
//            helper.setText(R.id.tv_system_notice_content,item.content);
//            helper.getView(R.id.rl_system_notice_bottom).setVisibility(View.GONE);
//            helper.getView(R.id.iv_system_notice_isread).setVisibility(item.isread==1?View.GONE:View.VISIBLE);
            helper.getView(R.id.ll_type_wuliu).setVisibility(View.GONE);
            helper.getView(R.id.ll_type_system_notice).setVisibility(View.GONE);
            helper.getView(R.id.ll_type_youhui).setVisibility(View.VISIBLE);
            helper.getView(R.id.ll_type_arrive_notice).setVisibility(View.GONE);
            ImageView iv_youhui=helper.getView(R.id.iv_youhui);
            GlideUtil.loadImg(context,item.banner,iv_youhui);
            helper.setText(R.id.tv_youhui_name,item.activityname);
            helper.setText(R.id.tv_youhui_time, DateUtil.formatyyyyMMddHHmmss(Long.valueOf(item.addtime+"000")));
            helper.setText(R.id.tv_youhui_bottom_desc,"点击查看活动详情");
            helper.getView(R.id.iv_youhui_isread).setVisibility(item.isread==1?View.GONE:View.VISIBLE);
        } else {//其他特殊情况暂不处理，只显示
            helper.getView(R.id.ll_type_wuliu).setVisibility(View.VISIBLE);
            helper.getView(R.id.ll_type_system_notice).setVisibility(View.GONE);
            helper.getView(R.id.ll_type_youhui).setVisibility(View.GONE);
            helper.getView(R.id.ll_type_arrive_notice).setVisibility(View.GONE);
        }

        helper.getView(R.id.ll_type_wuliu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.isread!=HAS_Read){
                    MessageIsReadNumManage.newInstance().refreshDate(1);
                    if(listener!=null)listener.readMessage(item.itemid,helper.getLayoutPosition());
                }
                if(item.pushtype==4){//跳转订单详情
                    if(listener!=null)listener.goOrderDetail(item.pushdata);
                }else {//跳转售后订单
                    if(listener!=null)listener.goBackOrderDetail(item.pushdata);
                }
            }
        });
        helper.getView(R.id.ll_type_system_notice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.isread!=HAS_Read){
                    MessageIsReadNumManage.newInstance().refreshDate(2);
                    if(listener!=null)listener.readMessage(item.itemid,helper.getLayoutPosition());
                }
                if(item.pushtype==6){//跳转门店
                    if(listener!=null)listener.goMenDianInfo(item.pushdata);
                }else {//跳转活动专区
//                    if(listener!=null)listener.goZoneActivity();
                }
            }
        });
        helper.getView(R.id.ll_type_youhui).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.isread!=HAS_Read){
                    MessageIsReadNumManage.newInstance().refreshDate(3);
                    if(listener!=null)listener.readMessage(item.itemid,helper.getLayoutPosition());
                }
                if(item.pushtype==1){//跳转活动模块
                    if(listener!=null)listener.goWebActivity(item.weburl);
                }else if(item.pushtype==7){//跳转特殊活动
                    if(listener!=null)listener.godata7Activity(item.levelid,item.type);
                } else {//跳转单品（商品详情）
                    if(listener!=null)listener.goProductDetail(item.pushdata);
                }
            }
        });
        helper.getView(R.id.ll_type_arrive_notice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.isread!=HAS_Read){
                    MessageIsReadNumManage.newInstance().refreshDate(4);
                    if(listener!=null)listener.readMessage(item.itemid,helper.getLayoutPosition());
                }
                if(listener!=null)listener.goProductDetail(item.pushdata);
            }
        });
    }
}
