package com.example.myapplication.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Content implements Parcelable {
    private int contentId;
    private String name;
    private String format;
    private String contentType;
    private String permaLink;
    private String thumbLink;
    private int size;
    private int duration;
    private int ordinal;

    protected Content(Parcel in) {
        contentId = in.readInt();
        name = in.readString();
        format = in.readString();
        contentType = in.readString();
        permaLink = in.readString();
        thumbLink = in.readString();
        size = in.readInt();
        duration = in.readInt();
        ordinal = in.readInt();
    }

    public static final Creator<Content> CREATOR = new Creator<Content>() {
        @Override
        public Content createFromParcel(Parcel in) {
            return new Content(in);
        }

        @Override
        public Content[] newArray(int size) {
            return new Content[size];
        }
    };

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getPermaLink() {
        return permaLink;
    }

    public void setPermaLink(String permaLink) {
        this.permaLink = permaLink;
    }

    public String getThumbLink() {
        return thumbLink;
    }

    public void setThumbLink(String thumbLink) {
        this.thumbLink = thumbLink;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(int ordinal) {
        this.ordinal = ordinal;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(contentId);
        dest.writeString(name);
        dest.writeString(format);
        dest.writeString(contentType);
        dest.writeString(permaLink);
        dest.writeString(thumbLink);
        dest.writeInt(size);
        dest.writeInt(duration);
        dest.writeInt(ordinal);
    }
// Generate getters and setters for all fields
}

