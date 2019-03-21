package com.zhuandian.votesystem;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.zhuandian.base.BaseActivity;
import com.zhuandian.votesystem.entity.VoteEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

public class VoteActivity extends BaseActivity {


    @BindView(R.id.tv_vote_name)
    TextView tvVoteName;
    @BindView(R.id.tv_vote_content)
    TextView tvVoteContent;
    @BindView(R.id.tv_vote_time)
    TextView tvVoteTime;
    @BindView(R.id.rb_support)
    RadioButton rbSupport;
    @BindView(R.id.rb_oppose)
    RadioButton rbOppose;
    @BindView(R.id.tv_commit_vote)
    TextView tvCommitVote;
    @BindView(R.id.cb_anonymous)
    CheckBox cbAnonymous;
    @BindView(R.id.tv_oppose_number)
    TextView tvOpposeNumber;
    @BindView(R.id.tv_support_number)
    TextView tvSupportNumber;
    private VoteEntity voteEntity;
    private int voteState = 1;  // 1.赞成 2.反对
    private boolean isAnonymous = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_vote;
    }

    @Override
    protected void setUpView() {
        voteEntity = ((VoteEntity) getIntent().getSerializableExtra("entity"));
        updateVote(voteEntity);
        cbAnonymous.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isAnonymous = isChecked;
            }
        });
    }

    private void updateVote(VoteEntity voteEntity) {
        tvVoteName.setText(voteEntity.getVoteString());
        tvVoteContent.setText(voteEntity.getVoteContent());
        tvVoteTime.setText(voteEntity.getCreatedAt());
        tvOpposeNumber.setText(String.format("反对人数:%s", voteEntity.getOpposeCount()));
        tvSupportNumber.setText(String.format("赞成人数:%s", voteEntity.getSupportCount()));
    }


    @OnClick({R.id.rb_support, R.id.rb_oppose, R.id.tv_commit_vote})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_support:
                voteState = 1;
                break;
            case R.id.rb_oppose:
                voteState = 2;
                break;
            case R.id.tv_commit_vote:
                updateVoteState();
                break;
        }
    }

    private void updateVoteState() {
        BmobQuery<VoteEntity> query = new BmobQuery<>();
        query.addWhereEqualTo("objectId", voteEntity.getObjectId())
                .findObjects(new FindListener<VoteEntity>() {
                    @Override
                    public void done(final List<VoteEntity> list, BmobException e) {
                        VoteEntity voteEntity = list.get(0);
                        if (voteState == 1) {
                            voteEntity.setSupportCount(voteEntity.getSupportCount() + 1);
                        } else if (voteState == 2) {
                            voteEntity.setOpposeCount(voteEntity.getOpposeCount() + 1);
                        }
                        voteEntity.update(new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null) {
                                    updateVote(list.get(0));
                                    Toast.makeText(VoteActivity.this, (isAnonymous ? "匿名 " : "") + "投" + (voteState == 1 ? "赞成" : "反对") + "成功...", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
