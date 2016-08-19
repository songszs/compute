package utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Rect;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.ImageView;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Project Name: m4399_Forums
 * File Name:    ViewUtils.java
 * ClassName:    ViewUtils
 *
 * Description: ViewUtils
 *
 * @author ZangSong
 * @date 2015年8月4日 上午11:19:38
 *
 * Copyright (c) 2015年, 4399 Network CO.ltd. All Rights Reserved.
 */
public class ViewUtils
{
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    /**
     * 动态生成View ID
     * API LEVEL 17 以上View.generateViewId()生成
     * API LEVEL 17 以下需要手动生成
     */
    public static int generateViewId()
    {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1)
        {
            for (; ; )
            {
                final int result = sNextGeneratedId.get();
                // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
                int newValue = result + 1;
                if (newValue > 0x00FFFFFF)
                {
                    newValue = 1; // Roll over to 1, not 0.
                }
                if (sNextGeneratedId.compareAndSet(result, newValue))
                {
                    return result;
                }
            }
        }
        else
        {
            return View.generateViewId();
        }
    }

    /**
     * @param value 0设置黑白,1设置彩色
     * @Description:设置imageview颜色
     * @Title: ViewUtils.java
     * @date 2015年9月10日 上午10:50:07
     * @author ZangSong
     */
    public static void setImageUneffectiveColor(ImageView imageView, int value)
    {
        if (imageView == null)
        {
            return;
        }
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(value);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        imageView.setColorFilter(f);
    }


    /**
     * 获取view在屏幕Y轴的坐标
     *
     * @param v
     * @return Y轴的坐标
     */
    public static int getScreeLocationY(View v)
    {
        return getScreeLocationXY(v)[1];
    }

    /**
     * 获取view在屏幕X轴的坐标
     *
     * @param v
     * @return X轴的坐标
     */
    public static int getScreeLocationX(View v)
    {
        return getScreeLocationXY(v)[0];
    }

    /**
     * 获取View屏幕坐标
     *
     * @param v
     * @return
     */
    public static int[] getScreeLocationXY(View v)
    {
        int[] xy = new int[2];
        v.getLocationOnScreen(xy);
        return xy;
    }

    /**
     * 优先从Activity获取LayoutInflater
     *
     * @param cxt
     * @return LayoutInflater
     */
    public static LayoutInflater getLayoutInflater(Context cxt)
    {
        LayoutInflater li = null;
        if (cxt instanceof Activity)
        {
            li = ((Activity) cxt).getLayoutInflater();
        }
        else
        {
            li = LayoutInflater.from(cxt);
        }
        return li;
    }

    /**
     * 优先从Activity获取LayoutInflater
     *
     * @param v
     * @return LayoutInflater
     */
    public static LayoutInflater getLayoutInflater(View v)
    {
        return getLayoutInflater(v.getContext());
    }

    /**
     * 增加view的点击区域
     *
     * @param view
     */
    public static void expandViewTouchDelegate(final View view,final float dp)
    {
        if (view == null)
        {
            return;
        }
        view.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Rect bounds = new Rect();
                view.setEnabled(true);
                view.getHitRect(bounds);

                int padding = DensityUtils.dip2px(view.getContext(), dp);
                bounds.top -= padding;
                bounds.bottom += padding;
                bounds.left -= padding;
                bounds.right += padding;
                if (View.class.isInstance(view.getParent()))
                {
                    ((View) view.getParent()).setTouchDelegate(new TouchDelegate(bounds, view));
                }
            }
        }, 100);
    }
}
