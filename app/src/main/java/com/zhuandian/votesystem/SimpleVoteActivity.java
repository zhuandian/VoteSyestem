package com.zhuandian.votesystem;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
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


/**
 * desc :普通投票页面
 * author：xiedong
 * date：2019/3/20
 */
public class SimpleVoteActivity extends BaseActivity {


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
    @BindView(R.id.et_vote_name)
    EditText etVoteName;
    private int voteState = 1;  // 1.赞成 2.反对
    private boolean isAnonymous = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_vote;
    }

    @Override
    protected void setUpView() {
        cbAnonymous.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isAnonymous = isChecked;
            }
        });

        BmobQuery<VoteEntity> query = new BmobQuery<>();
        query.findObjects(new FindListener<VoteEntity>() {
            @Override
            public void done(List<VoteEntity> list, BmobException e) {
                if (e == null) {
                    updateVote(list.get(0));
                }
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
                if (TextUtils.isEmpty(etVoteName.getText().toString()))
                    Toast.makeText(this, "请输入投票人名称...", Toast.LENGTH_SHORT).show();
                else
                    updateVoteState();
                break;
        }
    }

    private void updateVoteState() {
        BmobQuery<VoteEntity> query = new BmobQuery<>();
        query.findObjects(new FindListener<VoteEntity>() {
            @Override
            public void done(final List<VoteEntity> list, BmobException e) {
                boolean isAllowVote = false;//当前是否允许投票
                VoteEntity voteEntity = list.get(0);
                updateVote(voteEntity);
                for (String name : voteEntity.getNameList()) {
                    if (name.equals(etVoteName.getText().toString())) {
                        isAllowVote = true;
                    }
                }
                if (isAllowVote) {
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
                                Toast.makeText(SimpleVoteActivity.this, (isAnonymous ? "匿名 " : "") + "投" + (voteState == 1 ? "赞成" : "反对") + "成功...", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    new AlertDialog.Builder(SimpleVoteActivity.this)
                            .setTitle("抱歉！")
                            .setMessage("抱歉当前用户没有投票权限，请更换用户名后再投票")
                            .create()
                            .show();
                }
            }
        });
    }

}
