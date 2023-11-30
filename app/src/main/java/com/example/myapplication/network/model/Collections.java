package com.example.myapplication.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class Collections implements Parcelable {
    private List<SmartCollection> smart;
    private List<UserCollection> user;
    private List<String> curated;

    protected Collections(Parcel in) {
        curated = in.createStringArrayList();
    }

    public static final Creator<Collections> CREATOR = new Creator<Collections>() {
        @Override
        public Collections createFromParcel(Parcel in) {
            return new Collections(in);
        }

        @Override
        public Collections[] newArray(int size) {
            return new Collections[size];
        }
    };

    public List<SmartCollection> getSmart() {
        return smart;
    }

    public List<UserCollection> getUser() {
        return user;
    }

    public List<String> getCurated() {
        return curated;
    }

    public void setSmart(List<SmartCollection> smart) {
        this.smart = smart;
    }

    public void setUser(List<UserCollection> user) {
        this.user = user;
    }

    public void setCurated(List<String> curated) {
        this.curated = curated;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeStringList(curated);
    }
}
