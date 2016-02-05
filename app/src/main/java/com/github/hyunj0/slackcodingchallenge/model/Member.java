package com.github.hyunj0.slackcodingchallenge.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Member {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("team_id")
    @Expose
    private String teamId;

    @SerializedName("name")
    @Expose
    private String username;

    @SerializedName("color")
    @Expose
    private String color;

    @SerializedName("tz")
    @Expose
    private String tz;

    @SerializedName("tz_label")
    @Expose
    private String tzLabel;

    @SerializedName("tz_offset")
    @Expose
    private int tzOffset;

    @SerializedName("profile")
    @Expose
    private Profile profile;

    @SerializedName("is_admin")
    @Expose
    private boolean isAdmin;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTz() {
        return tz;
    }

    public void setTz(String tz) {
        this.tz = tz;
    }

    public String getTzLabel() {
        return tzLabel;
    }

    public void setTzLabel(String tzLabel) {
        this.tzLabel = tzLabel;
    }

    public int getTzOffset() {
        return tzOffset;
    }

    public void setTzOffset(int tzOffset) {
        this.tzOffset = tzOffset;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "username: " + this.username + " profile: " + this.profile.toString() + " timezone: " + this.tz;
    }
}