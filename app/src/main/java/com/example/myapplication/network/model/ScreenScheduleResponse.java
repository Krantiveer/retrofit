package com.example.myapplication.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class ScreenScheduleResponse implements Parcelable {

    private int id;
    private String name;
    private String orientation;
    private String placedAt;
    private int organizationId;
    private int locationId;
    private String expiresAt;
    private String status;
    private int createdBy;
    private int updatedBy;
    private String updatedDate;
    private List<Schedule> schedules;


    protected ScreenScheduleResponse(Parcel in) {
        id = in.readInt();
        name = in.readString();
        orientation = in.readString();
        placedAt = in.readString();
        organizationId = in.readInt();
        locationId = in.readInt();
        expiresAt = in.readString();
        status = in.readString();
        createdBy = in.readInt();
        updatedBy = in.readInt();
        updatedDate = in.readString();
    }

    public static final Creator<ScreenScheduleResponse> CREATOR = new Creator<ScreenScheduleResponse>() {
        @Override
        public ScreenScheduleResponse createFromParcel(Parcel in) {
            return new ScreenScheduleResponse(in);
        }

        @Override
        public ScreenScheduleResponse[] newArray(int size) {
            return new ScreenScheduleResponse[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getPlacedAt() {
        return placedAt;
    }

    public void setPlacedAt(String placedAt) {
        this.placedAt = placedAt;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(orientation);
        dest.writeString(placedAt);
        dest.writeInt(organizationId);
        dest.writeInt(locationId);
        dest.writeString(expiresAt);
        dest.writeString(status);
        dest.writeInt(createdBy);
        dest.writeInt(updatedBy);
        dest.writeString(updatedDate);
    }
// Generate getters and setters for all fields
}
