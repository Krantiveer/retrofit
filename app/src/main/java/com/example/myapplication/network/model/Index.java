package com.example.myapplication.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class Index implements Parcelable {
    private int downloadid;
    private int cd_downloads;
    private int id;
    private String title;
    private int status;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("authorid")
    private int authorId;
    @SerializedName("video_count")
    private int videoCount;
    @SerializedName("style_tags")
    private List<String> styleTags;
    @SerializedName("skill_tags")
    private List<String> skillTags;
    @SerializedName("series_tags")
    private List<String> seriesTags;
    @SerializedName("curriculum_tags")
    private List<String> curriculumTags;
    private String educator;
    private int owned;
    private int sale;
    @SerializedName("purchase_order")
    private Object purchaseOrder; // Assuming it can be boolean or int
    private int watched;
    @SerializedName("progress_tracking")
    private double progressTracking;

    protected Index(Parcel in) {
        downloadid = in.readInt();
        cd_downloads = in.readInt();
        id = in.readInt();
        title = in.readString();
        status = in.readInt();
        releaseDate = in.readString();
        authorId = in.readInt();
        videoCount = in.readInt();
        educator = in.readString();
        owned = in.readInt();
        sale = in.readInt();
        watched = in.readInt();
        progressTracking = in.readDouble();
    }

    public static final Creator<Index> CREATOR = new Creator<Index>() {
        @Override
        public Index createFromParcel(Parcel in) {
            return new Index(in);
        }

        @Override
        public Index[] newArray(int size) {
            return new Index[size];
        }
    };

    public int getDownloadid() {
        return downloadid;
    }

    public void setDownloadid(int downloadid) {
        this.downloadid = downloadid;
    }

    public int getCd_downloads() {
        return cd_downloads;
    }

    public void setCd_downloads(int cd_downloads) {
        this.cd_downloads = cd_downloads;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getVideoCount() {
        return videoCount;
    }

    public void setVideoCount(int videoCount) {
        this.videoCount = videoCount;
    }

    public List<String> getStyleTags() {
        return styleTags;
    }

    public void setStyleTags(List<String> styleTags) {
        this.styleTags = styleTags;
    }

    public List<String> getSkillTags() {
        return skillTags;
    }

    public void setSkillTags(List<String> skillTags) {
        this.skillTags = skillTags;
    }

    public List<String> getSeriesTags() {
        return seriesTags;
    }

    public void setSeriesTags(List<String> seriesTags) {
        this.seriesTags = seriesTags;
    }

    public List<String> getCurriculumTags() {
        return curriculumTags;
    }

    public void setCurriculumTags(List<String> curriculumTags) {
        this.curriculumTags = curriculumTags;
    }

    public String getEducator() {
        return educator;
    }

    public void setEducator(String educator) {
        this.educator = educator;
    }

    public int getOwned() {
        return owned;
    }

    public void setOwned(int owned) {
        this.owned = owned;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public Object getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(Object purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public int getWatched() {
        return watched;
    }

    public void setWatched(int watched) {
        this.watched = watched;
    }

    public double getProgressTracking() {
        return progressTracking;
    }

    public void setProgressTracking(double progressTracking) {
        this.progressTracking = progressTracking;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(downloadid);
        dest.writeInt(cd_downloads);
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeInt(status);
        dest.writeString(releaseDate);
        dest.writeInt(authorId);
        dest.writeInt(videoCount);
        dest.writeString(educator);
        dest.writeInt(owned);
        dest.writeInt(sale);
        dest.writeInt(watched);
        dest.writeDouble(progressTracking);
    }
// Getters for the fields
    // Add setters if needed
}

