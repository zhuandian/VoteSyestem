package com.zhuandian.votesystem.entity;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * desc :投票候选人或者评价人员实体类
 * author：xiedong
 * date：2019/4/3
 */
public class MemberVoteEntity extends BmobObject {
    private String voteTitle;
    private String voteContent;
    private List<Member> nameList;

    public String getVoteTitle() {
        return voteTitle;
    }

    public void setVoteTitle(String voteTitle) {
        this.voteTitle = voteTitle;
    }

    public String getVoteContent() {
        return voteContent;
    }

    public void setVoteContent(String voteContent) {
        this.voteContent = voteContent;
    }

    public List<Member> getNameList() {
        return nameList;
    }

    public void setNameList(List<Member> nameList) {
        this.nameList = nameList;
    }

    public static class Member {
        private String name;
        private int count;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
