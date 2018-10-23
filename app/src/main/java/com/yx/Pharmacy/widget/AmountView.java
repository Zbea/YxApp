package com.yx.Pharmacy.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.util.L;

/**
 * Created time  2018/8/2 0002
 * @author : mcx
 * 类描述 : 自定义组件：购买数量，带减少增加按钮
 */

public class AmountView extends LinearLayout
        implements View.OnClickListener, TextWatcher
{

    private static final String TAG = "AmountView";
    private int amount = 1; //购买数量
    private int goods_storage = 1; //商品库存

    private OnAmountChangeListener mListener;

    private EditText etAmount;
    private Button   btnDecrease;
    private Button   btnIncrease;
    private int mMinNum = 1;
    private int mAddNum = 1;
    private boolean mClickBtn = false;
    private boolean mEnable = true;
    private int mLookAmount;

    public AmountView(Context context) {
        this(context, null);
    }

    public AmountView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_amount, this);
        etAmount = (EditText) findViewById(R.id.etAmount);
        btnDecrease = (Button) findViewById(R.id.btnDecrease);
        btnIncrease = (Button) findViewById(R.id.btnIncrease);
        btnDecrease.setOnClickListener(this);
        btnIncrease.setOnClickListener(this);
        etAmount.addTextChangedListener(this);
        etAmount.clearFocus();
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.AmountView);
        int        btnWidth               = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_btnWidth, LayoutParams.WRAP_CONTENT);
        int        tvWidth                = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_tvWidth, 80);
        int        tvTextSize             = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_tvTextSize, 0);
        int        btnTextSize            = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_btnTextSize, 0);
        obtainStyledAttributes.recycle();

        LayoutParams btnParams = new LayoutParams(btnWidth, LayoutParams.MATCH_PARENT);
        btnDecrease.setLayoutParams(btnParams);
        btnIncrease.setLayoutParams(btnParams);
        if (btnTextSize != 0) {
            btnDecrease.setTextSize(TypedValue.COMPLEX_UNIT_PX, btnTextSize);
            btnIncrease.setTextSize(TypedValue.COMPLEX_UNIT_PX, btnTextSize);
        }

        LayoutParams textParams = new LayoutParams(tvWidth, LayoutParams.MATCH_PARENT);
        etAmount.setLayoutParams(textParams);
        if (tvTextSize != 0) {
            etAmount.setTextSize(tvTextSize);
        }
    }

    public void setOnAmountChangeListener(OnAmountChangeListener onAmountChangeListener) {
        this.mListener = onAmountChangeListener;
    }

    public void setGoods_storage(int goods_storage) {
        this.goods_storage = goods_storage;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        mClickBtn = true;
        if (i == R.id.btnDecrease) {
            if (amount-mAddNum >= mMinNum) {
                amount = amount-mAddNum;
                etAmount.setText(amount + "");
                etAmount.setSelection(etAmount.getText().length());
            }
        } else if (i == R.id.btnIncrease) {
            if (amount < goods_storage) {
                amount = amount + mAddNum;
                L.i("amount2:"+amount);
                etAmount.setText(amount + "");
                etAmount.setSelection(etAmount.getText().length());
            }
        }

        etAmount.clearFocus();

        if (mListener != null) {
            mListener.onAmountChange(this, amount,false);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().isEmpty()) {
            if (mListener != null) {
                if (!mClickBtn) {
                    mListener.onAmountChange(this, 0,true);
                }
                mClickBtn = false;
            }
            return;
        }
        amount = Integer.valueOf(s.toString());
        amount = Math.abs(amount);
        goods_storage = Math.abs(goods_storage);
        if (amount==0)
        {
            return;
        }
        if (amount >goods_storage) {
            etAmount.setText(goods_storage + "");
            etAmount.setSelection(etAmount.getText().length());
            return;
        }
        if (mEnable) {
            if (amount >= goods_storage) {
                btnIncrease.setEnabled(false);
            }else {
                btnIncrease.setEnabled(true);
            }
        }

        if (amount < mMinNum) {
            etAmount.setText(mMinNum + "");
            etAmount.setSelection(etAmount.getText().length());
            return;
        }

        if (mListener != null) {
            if (!mClickBtn) {
                mListener.onAmountChange(this, amount,true);
            }
            mClickBtn = false;
        }

    }

    public void setMinNum(int minNum) {
        mMinNum = minNum==0?1:minNum;
    }

    public void setAddNum(int addNum) {
        mAddNum = addNum==0?1:addNum;
    }


    public interface OnAmountChangeListener {
        void onAmountChange(View view, int amount, boolean isEdit);
    }

    public void setAmount(int amount) {
        this.amount = amount;
        this.mLookAmount = amount;
        etAmount.setText(amount + "");
    }

    public void setEnable(boolean b) {
        mEnable = b;
        btnDecrease.setEnabled(b);
        btnIncrease.setEnabled(b);
        etAmount.setEnabled(b);
    }


}
