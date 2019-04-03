package com.zhuandian.votesystem;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhuandian.base.BaseActivity;
import com.zhuandian.votesystem.adapter.MemberAdapter;
import com.zhuandian.votesystem.entity.MemberVoteEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * desc :投票候选人或者评价人员界面
 * author：xiedong
 * date：2019/3/20
 */
public class MemberVoteActivity extends BaseActivity {

    @BindView(R.id.tv_vote_name)
    TextView tvVoteName;
    @BindView(R.id.tv_vote_content)
    TextView tvVoteContent;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.tv_vote_time)
    TextView tvVoteTime;
    private boolean isVote = true;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_member_vote;
    }

    @Override
    protected void setUpView() {
        initData();
    }

    private void initData() {
        BmobQuery<MemberVoteEntity> query = new BmobQuery<>();
        query.findObjects(new FindListener<MemberVoteEntity>() {
            @Override
            public void done(final List<MemberVoteEntity> list, final BmobException e) {
                if (e == null) {
                    tvVoteName.setText(list.get(0).getVoteTitle());
                    tvVoteContent.setText(list.get(0).getVoteContent());
                    tvVoteTime.setText(list.get(0).getCreatedAt());
                    MemberAdapter adapter = new MemberAdapter(list.get(0).getNameList(), MemberVoteActivity.this);
                    rvList.setAdapter(adapter);
                    rvList.setLayoutManager(new LinearLayoutManager(MemberVoteActivity.this));
                    adapter.setOnItemClickListener(new MemberAdapter.OnItemClickListener() {
                        @Override
                        public void onClick(MemberVoteEntity.Member member) {
                            if (isVote) {
                                isVote = false;

                                int currentCount = member.getCount() + 1;
                                for (int i = 0; i < list.get(0).getNameList().size(); i++) {
                                    if (member.getName().equals(list.get(0).getNameList().get(i).getName())) {
                                        list.get(0).getNameList().get(i).setCount(currentCount);
                                        list.get(0).update(new UpdateListener() {
                                            @Override
                                            public void done(BmobException e) {
                                                if (e == null) {
                                                    initData();
                                                    Toast.makeText(MemberVoteActivity.this, "投票成功...", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    }
                                }

                            } else {
                                Toast.makeText(MemberVoteActivity.this, "只允许投票一次...", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }


}
