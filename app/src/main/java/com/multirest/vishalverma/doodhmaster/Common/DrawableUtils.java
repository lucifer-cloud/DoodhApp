package com.multirest.vishalverma.doodhmaster.Common;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.content.ContextCompat;

import com.multirest.vishalverma.doodhmaster.R;



public final class DrawableUtils {

    public static Drawable getCircleDrawableWithText(Context context, String string) {
        Drawable background = ContextCompat.getDrawable(context, R.drawable.call_icon);
        Drawable text = CalendarUtils.getDrawableText(context, string, null, android.R.color.white, 12);

        Drawable[] layers = {background, text};
        return new LayerDrawable(layers);
    }

    public static Drawable getThreeDots(Context context) {
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.profile_pic);

        //Add padding to too large icon
        return new InsetDrawable(drawable, 100, 0, 100, 0);
    }

    private DrawableUtils() {
    }
}