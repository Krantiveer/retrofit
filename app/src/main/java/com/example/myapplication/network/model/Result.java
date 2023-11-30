package com.example.myapplication.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class Result implements Parcelable {
    private List<Index> index;
    private Collections collections;

    protected Result(Parcel in) {
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

    public List<Index> getIndex() {
        return index;
    }

    public Collections getCollections() {
        return collections;
    }

    public void setIndex(List<Index> index) {
        this.index = index;
    }

    public void setCollections(Collections collections) {
        this.collections = collections;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
    }
}
