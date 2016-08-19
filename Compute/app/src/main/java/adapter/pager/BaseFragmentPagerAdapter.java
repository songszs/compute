package adapter.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseFragmentPagerAdapter extends FragmentPagerAdapter
{

    private List<Fragment> mFragments;

    private List<String> mTitles;

    public BaseFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles)
    {
        super(fm);
        this.mFragments = fragments;
        this.mTitles = titles;
    }

    public BaseFragmentPagerAdapter(FragmentManager fm, Class<?>[] fragments, String[] titles)
    {
        super(fm);

        this.mFragments = new ArrayList<Fragment>();
        for (Class<?> fragment : fragments)
        {
            try
            {
                Object object = fragment.newInstance();
                if (object instanceof Fragment)
                {
                    mFragments.add((Fragment) object);
                }
            }
            catch (InstantiationException e)
            {
                e.printStackTrace();
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
        this.mTitles = Arrays.asList(titles);
    }

    @Override
    public Fragment getItem(int position)
    {
        return mFragments.get(position);
    }

    @Override
    public int getCount()
    {
        return mFragments.size();
    }

    @Override
    public int getItemPosition(Object object)
    {
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return mTitles.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return mTitles.get(position).hashCode() + this.hashCode();
    }
}
