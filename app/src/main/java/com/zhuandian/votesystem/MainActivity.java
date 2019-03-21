package com.zhuandian.votesystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zhuandian.base.BaseActivity;
import com.zhuandian.votesystem.adapter.VoteAdapter;
import com.zhuandian.votesystem.entity.VoteEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class MainActivity extends BaseActivity {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setUpView() {
        initData();

    }

    private void initData() {
        BmobQuery<VoteEntity> query = new BmobQuery<>();
        query.findObjects(new FindListener<VoteEntity>() {
            @Override
            public void done(List<VoteEntity> list, BmobException e) {
                if (e == null) {
                    VoteAdapter voteAdapter = new VoteAdapter(list, MainActivity.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerView.setAdapter(voteAdapter);

                    voteAdapter.setOnItemClickListener(new VoteAdapter.OnItemClickListener() {
                        @Override
                        public void onClick(VoteEntity voteEntity) {
                            startActivity(new Intent(MainActivity.this,VoteActivity.class));
                        }
                    });
                }
            }
        });
    }



}
