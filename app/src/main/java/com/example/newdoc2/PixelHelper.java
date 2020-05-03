package com.example.newdoc2;

import android.content.Context;
import android.util.TypedValue;

public class PixelHelper {
    public static int pixelsToDp(float px, Context context) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, px,
                context.getResources().getDisplayMetrics());
    }
}
