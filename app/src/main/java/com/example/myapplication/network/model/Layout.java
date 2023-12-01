package com.example.myapplication.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class Layout implements Parcelable {
    private int layoutId;
    private String name;
    private int width;
    private int height;
    private List<Zone> zones;

    protected Layout(Parcel in) {
        layoutId = in.readInt();
        name = in.readString();
        width = in.readInt();
        height = in.readInt();
        zones = in.createTypedArrayList(Zone.CREATOR);
    }

    public static final Creator<Layout> CREATOR = new Creator<Layout>() {
        @Override
        public Layout createFromParcel(Parcel in) {
            return new Layout(in);
        }

        @Override
        public Layout[] newArray(int size) {
            return new Layout[size];
        }
    };

    public int getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<Zone> getZones() {
        return zones;
    }

    public void setZones(List<Zone> zones) {
        this.zones = zones;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(layoutId);
        dest.writeString(name);
        dest.writeInt(width);
        dest.writeInt(height);
        dest.writeTypedList(zones);
    }
// Generate getters and setters for all fields
}
