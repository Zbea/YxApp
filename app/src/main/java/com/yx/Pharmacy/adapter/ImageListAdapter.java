package com.yx.Pharmacy.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.util.GlideUtil;
import com.yx.Pharmacy.util.UiUtil;

/**
 * Created time  2018/8/28 0028
 * @author : mcx
 * 类描述 : 
 */

public class ImageListAdapter
        extends BaseQuickAdapter<String,BaseViewHolder>
{
    public ImageListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        GlideUtil.loadImgNoStyle(UiUtil.getContext(),item,helper.getView(R.id.iv_photo));
        helper.getView(R.id.iv_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null)
                    onItemClickListener.onClick();
            }
        });
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener=onItemClickListener;
    }

    public interface OnItemClickListener
    {
        void onClick();
    }
}
