package com.example.myapplication.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class DeviceInfo implements Parcelable {
    private int deviceId;
    private String token;

    // Constructor
    public DeviceInfo(int deviceId, String token) {
        this.deviceId = deviceId;
        this.token = token;
    }

    protected DeviceInfo(Parcel in) {
        deviceId = in.readInt();
        token = in.readString();
    }

    public static final Creator<DeviceInfo> CREATOR = new Creator<DeviceInfo>() {
        @Override
        public DeviceInfo createFromParcel(Parcel in) {
            return new DeviceInfo(in);
        }

        @Override
        public DeviceInfo[] newArray(int size) {
            return new DeviceInfo[size];
        }
    };

    // Getters and Setters
    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(deviceId);
        dest.writeString(token);
    }
}

