package com.zhuandian.votesystem.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.zhuandian.base.BaseAdapter;
import com.zhuandian.base.BaseViewHolder;
import com.zhuandian.votesystem.R;
import com.zhuandian.votesystem.entity.MemberVoteEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * desc :
 * author：xiedong
 * date：2019/4/3
 */
public class MemberAdapter extends BaseAdapter<MemberVoteEntity.Member, BaseViewHolder> {
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_count)
    TextView tvCount;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private OnItemClickListener onItemClickListener;

    public MemberAdapter(List<MemberVoteEntity.Member> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    protected void converData(BaseViewHolder myViewHolder, final MemberVoteEntity.Member member, int position) {
        ButterKnife.bind(this, myViewHolder.itemView);
        tvName.setText(member.getName());
        tvCount.setText(member.getCount() + "票");
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(member);
                }
            }
        });
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.item_member;
    }

    public interface OnItemClickListener {
        void onClick(MemberVoteEntity.Member member);
    }
}
