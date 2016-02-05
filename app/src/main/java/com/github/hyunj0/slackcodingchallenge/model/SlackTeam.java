package com.github.hyunj0.slackcodingchallenge.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SlackTeam {

    @SerializedName("ok")
    @Expose
    private boolean ok;

    @SerializedName("members")
    @Expose
    private List<Member> members;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}