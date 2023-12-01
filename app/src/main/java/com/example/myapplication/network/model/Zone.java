package com.example.myapplication.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class Zone implements Parcelable {
    private int zoneId;
    private String zoneName;
    private double widthPercentage;
    private double heightPercentage;
    private String transitionType;
    private String transitionSpeed;
    private List<Content> contents;

    protected Zone(Parcel in) {
        zoneId = in.readInt();
        zoneName = in.readString();
        widthPercentage = in.readDouble();
        heightPercentage = in.readDouble();
        transitionType = in.readString();
        transitionSpeed = in.readString();
    }

    public static final Creator<Zone> CREATOR = new Creator<Zone>() {
        @Override
        public Zone createFromParcel(Parcel in) {
            return new Zone(in);
        }

        @Override
        public Zone[] newArray(int size) {
            return new Zone[size];
        }
    };

    public int getZoneId() {
        return zoneId;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public double getWidthPercentage() {
        return widthPercentage;
    }

    public void setWidthPercentage(double widthPercentage) {
        this.widthPercentage = widthPercentage;
    }

    public double getHeightPercentage() {
        return heightPercentage;
    }

    public void setHeightPercentage(double heightPercentage) {
        this.heightPercentage = heightPercentage;
    }

    public String getTransitionType() {
        return transitionType;
    }

    public void setTransitionType(String transitionType) {
        this.transitionType = transitionType;
    }

    public String getTransitionSpeed() {
        return transitionSpeed;
    }

    public void setTransitionSpeed(String transitionSpeed) {
        this.transitionSpeed = transitionSpeed;
    }

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(zoneId);
        dest.writeString(zoneName);
        dest.writeDouble(widthPercentage);
        dest.writeDouble(heightPercentage);
        dest.writeString(transitionType);
        dest.writeString(transitionSpeed);
    }
    // Generate getters and setters for all fields
}
