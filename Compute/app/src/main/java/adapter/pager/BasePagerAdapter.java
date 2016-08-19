package adapter.pager;

import android.database.DataSetObserver;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * @author LinLuoXian
 * @Description:基础ViewPager适配类
 * @ClassName: BasePagerAdapter
 * @date 2014年11月11日 下午1:43:13
 */
public class BasePagerAdapter extends PagerAdapter
{

    private List<View> mViews;
    private List<String> mTitles;

    public BasePagerAdapter(List<View> views)
    {
        this(views, null);
    }

    public BasePagerAdapter(List<View> views, List<String> titles)
    {
        this.mTitles = titles;
        this.mViews = views;
    }

    @Override
    public int getCount()
    {
        return mViews.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1)
    {
        return arg0 == arg1;
    }

    @Override
    public Object instantiateItem(View container, int position)
    {
        ((ViewPager) container).addView(mViews.get(position));
        return mViews.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        if (mTitles != null)
        {
            return mTitles.get(position);
        }
        else
        {
            return "";
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        container.removeView((View) object);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer)
    {
        //特定情况下unregisterDataSetObserver可能会被调用2次导致崩溃
        try
        {
            super.unregisterDataSetObserver(observer);
        }
        catch (Exception e)
        {
        }
    }
}
