package com.zhuandian.votesystem.entity;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * desc :
 * author：xiedong
 * date：2019/3/20
 */
public class VoteEntity extends BmobObject implements Serializable {
    private String voteString;
    private String voteContent;
    private int voteCount;
    private int anonymCountCount;
    private int supportCount;
    private int opposeCount;

    public int getSupportCount() {
        return supportCount;
    }

    public void setSupportCount(int supportCount) {
        this.supportCount = supportCount;
    }

    public int getOpposeCount() {
        return opposeCount;
    }

    public void setOpposeCount(int opposeCount) {
        this.opposeCount = opposeCount;
    }

    public String getVoteString() {
        return voteString;
    }

    public void setVoteString(String voteString) {
        this.voteString = voteString;
    }

    public String getVoteContent() {
        return voteContent;
    }

    public void setVoteContent(String voteContent) {
        this.voteContent = voteContent;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getAnonymCountCount() {
        return anonymCountCount;
    }

    public void setAnonymCountCount(int anonymCountCount) {
        this.anonymCountCount = anonymCountCount;
    }
}
