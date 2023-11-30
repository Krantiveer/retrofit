package com.example.myapplication.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SmartCollection implements Parcelable {
    private String id;
    private String label;
    private String smart;
    private List<Integer> courses;
    @SerializedName("is_default")
    private int isDefault;
    @SerializedName("is_archive")
    private int isArchive;
    private String description;

    // Getters for the fields
    // Add setters if needed

    protected SmartCollection(Parcel in) {
        id = in.readString();
        label = in.readString();
        smart = in.readString();
        isDefault = in.readInt();
        isArchive = in.readInt();
        description = in.readString();
    }

    public static final Creator<SmartCollection> CREATOR = new Creator<SmartCollection>() {
        @Override
        public SmartCollection createFromParcel(Parcel in) {
            return new SmartCollection(in);
        }

        @Override
        public SmartCollection[] newArray(int size) {
            return new SmartCollection[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSmart() {
        return smart;
    }

    public void setSmart(String smart) {
        this.smart = smart;
    }

    public List<Integer> getCourses() {
        return courses;
    }

    public void setCourses(List<Integer> courses) {
        this.courses = courses;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(label);
        dest.writeString(smart);
        dest.writeInt(isDefault);
        dest.writeInt(isArchive);
        dest.writeString(description);
    }
}
