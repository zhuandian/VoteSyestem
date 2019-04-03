package com.zhuandian.votesystem;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zhuandian.base.BaseActivity;

import butterknife.OnClick;


public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setUpView() {

    }


    @OnClick({R.id.tv_simple_vote, R.id.tv_member_vote})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_simple_vote:
                startActivity(new Intent(MainActivity.this, SimpleVoteActivity.class));
                break;
            case R.id.tv_member_vote:
                startActivity(new Intent(MainActivity.this, MemberVoteActivity.class));
                break;
        }
    }
}
