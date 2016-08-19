package adapter;

import android.content.Context;
import com.zs.compute.R;
import java.util.List;
import models.UserInfoModel;

/**
 * Created by user on 2016/8/4.
 */
public class UserAdapter extends QuickAdapter<UserInfoModel>
{

    public UserAdapter(Context context, List<UserInfoModel> data, int layoutResId)
    {
        super(context, data, layoutResId);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, UserInfoModel item)
    {
        helper.setText(R.id.computer_main_activity_item_id_tv,item.getUserName());
        helper.setText(R.id.computer_main_activity_item_username_tv,item.getUserName());
        helper.setText(R.id.computer_main_activity_item_edittime_tv,item.getLastEditTime());
    }
}
