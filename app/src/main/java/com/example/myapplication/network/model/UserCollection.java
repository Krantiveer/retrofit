package com.example.myapplication.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserCollection implements Parcelable {
    private int id;
    private String label;
    @SerializedName("is_default")
    private int isDefault;
    @SerializedName("is_archive")
    private int isArchive;
    private String description;
    private List<Integer> courses;

    // Getters for the fields
    // Add setters if needed

    protected UserCollection(Parcel in) {
        id = in.readInt();
        label = in.readString();
        isDefault = in.readInt();
        isArchive = in.readInt();
        description = in.readString();
    }

    public static final Creator<UserCollection> CREATOR = new Creator<UserCollection>() {
        @Override
        public UserCollection createFromParcel(Parcel in) {
            return new UserCollection(in);
        }

        @Override
        public UserCollection[] newArray(int size) {
            return new UserCollection[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    public int getIsArchive() {
        return isArchive;
    }

    public void setIsArchive(int isArchive) {
        this.isArchive = isArchive;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getCourses() {
        return courses;
    }

    public void setCourses(List<Integer> courses) {
        this.courses = courses;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(label);
        dest.writeInt(isDefault);
        dest.writeInt(isArchive);
        dest.writeString(description);
    }
}
