package com.gmail.pingkiuho.navigationdrawertemplates.view.drawer;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.v4.widget.ImageViewCompat;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gmail.pingkiuho.navigationdrawertemplates.R;

/**
 * Created by Brian Ho on 15/3/2018.
 */

public class CustomMenuItem extends LinearLayout {
    public static final String TAG = CustomMenuItem.class.getSimpleName();
    private ImageView mIconImageView;
    private TextView mTitleTextView;

    public CustomMenuItem(Context context) {
        super(context);
        inflate(getContext(), R.layout.view_menu_item, this);
        mIconImageView = findViewById(R.id.imageView_icon);
        mTitleTextView = findViewById(R.id.textView_title);

        ImageViewCompat.setImageTintList(mIconImageView, ColorStateList.valueOf(Color.WHITE));
        mTitleTextView.setTextColor(Color.WHITE);
    }

    public void setIndentation(int px) {
        LayoutParams params = (LayoutParams) getLayoutParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            params.setMarginStart(px);
        } else {
            params.leftMargin = px;
        }
        setLayoutParams(params);
    }

    public void setIcon(@DrawableRes int icon) {
        mIconImageView.setImageResource(icon);
    }

    public void setTitle(String title) {
        mTitleTextView.setText(title);
    }
}
