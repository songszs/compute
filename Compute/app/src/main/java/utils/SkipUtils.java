package utils;

import android.app.Activity;
import android.content.Intent;

import controller.EditActivity;

/**
 * Created by user on 2016/8/19.
 */
public class SkipUtils
{

    public static void skipUserDetailActivity(Activity context)
    {
        Intent intent = new Intent();
        intent.setClass(context, EditActivity.class);
        context.startActivity(intent);
    }
}
