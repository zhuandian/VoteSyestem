package com.zhuandian.votesystem;

import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import com.zhuandian.base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class VoteActivity extends BaseActivity {


    @BindView(R.id.tv_vote_name)
    TextView tvVoteName;
    @BindView(R.id.tv_vote_content)
    TextView tvVoteContent;
    @BindView(R.id.tv_vote_time)
    TextView tvVoteTime;
    @BindView(R.id.tv_vote_number)
    TextView tvVoteNumber;
    @BindView(R.id.rb_support)
    RadioButton rbSupport;
    @BindView(R.id.rb_oppose)
    RadioButton rbOppose;
    @BindView(R.id.tv_commit_vote)
    TextView tvCommitVote;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_vote;
    }

    @Override
    protected void setUpView() {

    }


    @OnClick({R.id.rb_support, R.id.rb_oppose, R.id.tv_commit_vote})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_support:
                break;
            case R.id.rb_oppose:
                break;
            case R.id.tv_commit_vote:
                break;
        }
    }
}
