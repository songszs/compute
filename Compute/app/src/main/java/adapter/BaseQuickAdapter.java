/**
 * Copyright 2013 Joan Zapata
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstraction class of a BaseAdapter in which you only need
 * to provide the convert() implementation.<br/>
 * Using the provided BaseAdapterHelper, your code is minimalist.
 *
 * @param <T> The type of the items in the list.
 */
public abstract class BaseQuickAdapter<T, H extends BaseAdapterHelper> extends BaseAdapter
{

    protected static final String TAG = BaseQuickAdapter.class.getSimpleName();

    protected final Context context;

    protected final int[] layoutResIds;

    protected final List<T> data;

    protected boolean notifyImageOnly;

    /**
     * Create a QuickAdapter.
     *
     * @param context     The context.
     * @param layoutResId The layout resource id of each item.
     */
    public BaseQuickAdapter(Context context, int layoutResId)
    {
        this(context, null, layoutResId);
    }

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param context      The context.
     * @param data         A new list is created out of this one to avoid mutable list
     * @param layoutResIds The layout resource id of each item.
     */
    public BaseQuickAdapter(Context context, List<T> data, int... layoutResIds)
    {
        // this.data = data == null ? new ArrayList<T>() : new ArrayList<T>(data);
        // modify by lx at 2014/11/24 18:31
        // 此处不用new ArrayList的原因是为了能够让外更容易去控制这里面的数据
        this.data = data == null ? new ArrayList<T>() : data;
        this.context = context;
        this.layoutResIds = layoutResIds;
    }

    @Override
    public int getCount()
    {
        return data.size();
    }

    @Override
    public T getItem(int position)
    {
        if (position >= data.size())
        {
            return null;
        }
        return data.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public int getViewTypeCount()
    {
        return layoutResIds.length;
    }

    @Override
    public int getItemViewType(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        int layoutResId = layoutResIds[getItemViewType(position)];
        H helper = getAdapterHelper(position, convertView, parent, layoutResId);
        T item = getItem(position);
        helper.setAssociatedObject(item);
        convert(helper, item);
        return helper.getView();
    }

    @Override
    public boolean isEnabled(int position)
    {
        return position < data.size();
    }

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    protected abstract void convert(H helper, T item);

    /**
     * You can override this method to use a custom BaseAdapterHelper in order to fit your needs
     *
     * @param position    The position of the item within the adapter's data set of the item whose view we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return An instance of BaseAdapterHelper
     */
    protected abstract H getAdapterHelper(int position, View convertView, ViewGroup parent, int layoutResId);


    /**
     * fix bug observer may be null
     *
     * @param observer
     */
    @Override
    public void unregisterDataSetObserver(DataSetObserver observer)
    {
        if (observer != null)
        {
            super.unregisterDataSetObserver(observer);
        }
    }

    @Override
    public void notifyDataSetChanged()
    {
        notifyImageOnly = false;
        super.notifyDataSetChanged();
    }

    public void notifyDataSetChanged(boolean updateImageOnly)
    {
        this.notifyImageOnly = updateImageOnly;
        super.notifyDataSetChanged();
    }
}