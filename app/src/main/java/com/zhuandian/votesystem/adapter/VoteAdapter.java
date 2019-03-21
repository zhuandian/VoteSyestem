package com.zhuandian.votesystem.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.zhuandian.base.BaseAdapter;
import com.zhuandian.base.BaseViewHolder;
import com.zhuandian.votesystem.R;
import com.zhuandian.votesystem.entity.VoteEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * desc :
 * author：xiedong
 * date：2019/3/20
 */
public class VoteAdapter extends BaseAdapter<VoteEntity, BaseViewHolder> {
    @BindView(R.id.tv_vote_name)
    TextView tvVoteName;
    @BindView(R.id.tv_vote_content)
    TextView tvVoteContent;
    @BindView(R.id.tv_vote_time)
    TextView tvVoteTime;
    @BindView(R.id.tv_vote_number)
    TextView tvVoteNumber;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private OnItemClickListener onItemClickListener;

    public VoteAdapter(List<VoteEntity> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    protected void converData(BaseViewHolder myViewHolder, final VoteEntity voteEntity, int position) {
        ButterKnife.bind(this, myViewHolder.itemView);
        tvVoteName.setText(voteEntity.getVoteString());
        tvVoteContent.setText(voteEntity.getVoteContent());
        tvVoteNumber.setText(voteEntity.getVoteCount() + "");
        tvVoteTime.setText(voteEntity.getCreatedAt());
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(voteEntity);
                }
            }
        });
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.item_vote;
    }

    public interface OnItemClickListener {
        void onClick(VoteEntity voteEntity);
    }
}
