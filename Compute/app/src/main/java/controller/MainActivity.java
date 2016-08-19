package controller;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zs.compute.R;

import java.util.ArrayList;
import java.util.List;

import adapter.UserAdapter;
import models.UserInfoModel;
import utils.SkipUtils;


public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener
{
    private ProgressDialog mProgressDialog;
    private ListView mListView;
    private UserAdapter mAdapter;
    private List<UserInfoModel> mSource;

    @Override
    protected int getContentViewId()
    {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState, View mainView)
    {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("读取中...");
        mProgressDialog.show();

        mSource = new ArrayList<>();
        createData();

        mListView = (ListView) mainView.findViewById(R.id.computer_main_activity_content_lv);
        mAdapter = new UserAdapter(this,mSource,R.layout.compute_main_activity_item);

        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }

    private void createData()
    {
        UserInfoModel userInfoModel = new UserInfoModel();
        userInfoModel.setUserName("张三");
        userInfoModel.setLastEditTime("11月");
        userInfoModel.setUserId(1);

        mSource.add(userInfoModel);
        mSource.add(userInfoModel);
        mSource.add(userInfoModel);
        mSource.add(userInfoModel);
        mSource.add(userInfoModel);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        SkipUtils.skipUserDetailActivity(this);

    }
}
