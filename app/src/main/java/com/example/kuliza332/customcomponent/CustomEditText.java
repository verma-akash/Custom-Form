package com.example.kuliza332.customcomponent;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;

/**
 * Created by Akash Verma on 11/02/18.
 */

public class CustomEditText extends android.support.v7.widget.AppCompatEditText {

    private Context mContext;
    private boolean isEnabled = false;
    private String tagIdentifier;

    public CustomEditText(Context context) {
        super(context);
        init(context);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        this.setEnabled(isEnabled);
        this.setTextColor(getResources().getColor(android.R.color.black));
        ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.transparent));
        ViewCompat.setBackgroundTintList(this, colorStateList);
    }

    public String getTagIdentifier() {
        return tagIdentifier;
    }

    public void setTagIdentifier(String tagIdentifier) {
        this.tagIdentifier = tagIdentifier;
    }

    public void enableDisableEditTextView(boolean isEnabled) {
        this.setEnabled(isEnabled);
        if (!isEnabled) {
            this.clearFocus();
            ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.transparent));
            ViewCompat.setBackgroundTintList(this, colorStateList);
        } else {
            ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.colorAccent));
            ViewCompat.setBackgroundTintList(this, colorStateList);
        }
    }
}
