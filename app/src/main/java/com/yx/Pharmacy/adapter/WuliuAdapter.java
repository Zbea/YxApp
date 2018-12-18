package com.yx.Pharmacy.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.model.WuliuData;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by KID on 2018/7/17.
 */

public class WuliuAdapter extends BaseQuickAdapter<WuliuData.WuliModel,BaseViewHolder> {
    private Context context;

    public WuliuAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<WuliuData.WuliModel> data) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final WuliuData.WuliModel item) {
        TextView tv_wuliu_translate=helper.getView(R.id.tv_wuliu_translate);
        TextView tv_wuliu_date=helper.getView(R.id.tv_wuliu_date);
        if(item!=null){
            if(helper.getLayoutPosition()==getItemCount()-1){
                helper.getView(R.id.iv_line).setVisibility(View.GONE);
            }else {
                helper.getView(R.id.iv_line).setVisibility(View.VISIBLE);
            }
            tv_wuliu_translate.setText(item.translate);
            tv_wuliu_date.setText(item.translatetime);

            ImageView iv_status=helper.getView(R.id.iv_status);
            if(helper.getLayoutPosition()==0){
                tv_wuliu_translate.setTextColor(item.translate.contains("签收")?ContextCompat.getColor(context,R.color.green):ContextCompat.getColor(context,R.color.color_606060));
                tv_wuliu_date.setTextColor(item.translate.contains("签收")?ContextCompat.getColor(context,R.color.green):ContextCompat.getColor(context,R.color.color_606060));
                iv_status.setImageResource(item.translate.contains("签收")?R.drawable.wlqszt:R.drawable.wldqwqszt);
            }else {
                iv_status.setImageResource(R.drawable.wlgwzt);
                tv_wuliu_translate.setTextColor(ContextCompat.getColor(context,R.color.color_606060));
                tv_wuliu_date.setTextColor(ContextCompat.getColor(context,R.color.color_606060));
            }
        }

//        List<String>phones=getNumbers(item.translate);
//        if(phones.size()>0){
            setTelUrl(context,tv_wuliu_translate,item.translate);
//        }
    }





    /**
     * 从字符串中查找电话号码字符串
     */
    public static List<String> getNumbers(String content) {
        List<String> digitList = new ArrayList<String>();
        Pattern p = Pattern.compile("(\\d{11})");
        Matcher m = p.matcher(content);
        while (m.find()) {
            String find = m.group(1).toString();
            digitList.add(find);
        }
        return digitList;
    }
    public static SpannableString zhuanHuanTelUrl(final String strTel){
        SpannableString ss = new SpannableString(strTel);
        final List<String> list = getNumbers(strTel);
        if(list.size()>0){
            for (int i = 0; i < list.size(); i++) {
                final int finalI = i;
                ss.setSpan(new ClickableSpan() {
                    @Override
                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setColor(Color.parseColor("#25ae5f"));       //设置文件颜色
                        ds.setUnderlineText(false);      //设置下划线
                    }

                    @Override
                    public void onClick(View widget) {

                    }
                },strTel.indexOf(list.get(i)), strTel.indexOf(list.get(i))+11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

        }
        return ss;
    }
    /**
     * 将字符串中的电话号码设置点击事件和下划线
     * @param context
     * @param tv
     * @param strTel
     */
    public static void setTelUrl(Context context, TextView tv, String strTel){
        zhuanHuanTelUrl(strTel);
        tv.setHighlightColor(Color.TRANSPARENT); //设置点击后的颜色为透明，否则会一直出现高亮
        tv.setText(zhuanHuanTelUrl(strTel));
        tv.setMovementMethod(LinkMovementMethod.getInstance());//开始响应点击事件
    }
}
