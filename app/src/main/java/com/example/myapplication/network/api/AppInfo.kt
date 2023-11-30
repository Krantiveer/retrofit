package com.example.myapplication.network.api

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.SerializedName

/**
 * Created by TrilokiNath on 19-09-2017.
 */
class AppInfo protected constructor(`in`: Parcel) : Parcelable {
    @SerializedName("app_type")
    var appType: String?

    @SerializedName("app_version")
    private var currentVersion: String?

    @SerializedName("force_update")
    private var forceUpdate: String?

    @SerializedName("npawAccountKey")
    var npawAccountKey: String?

    @SerializedName("player_logo")
    var playerLogo: String? = null

    @SerializedName("isnpawEnable")
    var isnpawEnable: Boolean?

    @SerializedName("player_logo_enable")
    var player_logo_enable: String? = null

    @SerializedName("websiteurl")
    var websiteurl: String? = null

    @SerializedName("is_review")
    var is_review: Int? = null
    var enable_mobile_login: String? = null
    var enable_email_login: String? = null
    var enable_qr_login: String? = null
    var coupons: String? = null

    init {
        appType = `in`.readString()
        currentVersion = `in`.readString()
        forceUpdate = `in`.readString()
        npawAccountKey = `in`.readString()
        val tmpIsnpawEnable = `in`.readByte()
        isnpawEnable = if (tmpIsnpawEnable.toInt() == 0) null else tmpIsnpawEnable.toInt() == 1
    }

    override fun toString(): String {
        return "AppInfo{" +
                "appType='" + appType + '\'' +
                ", currentVersion='" + currentVersion + '\'' +
                ", forceUpdate='" + forceUpdate + '\'' +
                '}'
    }

    fun getCurrentVersion(): Int {
        return try {
            currentVersion!!.toInt()
        } catch (e: NumberFormatException) {
            e.printStackTrace()
            -1
        }
    }

    fun setCurrentVersion(currentVersion: String?) {
        this.currentVersion = currentVersion
    }

    fun isForceUpdate(): Boolean {
        return "1" == forceUpdate
    }

    fun setForceUpdate(forceUpdate: String?) {
        this.forceUpdate = forceUpdate
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(appType)
        dest.writeString(currentVersion)
        dest.writeString(forceUpdate)
        dest.writeString(npawAccountKey)
        dest.writeByte((if (isnpawEnable == null) 0 else if (isnpawEnable as Boolean) 1 else 2).toByte())
    }

    fun getForceUpdate(): String? {
        return forceUpdate
    }

    companion object {
        val CREATOR: Creator<AppInfo?> = object : Creator<AppInfo?> {
            override fun createFromParcel(`in`: Parcel): AppInfo? {
                return AppInfo(`in`)
            }

            override fun newArray(size: Int): Array<AppInfo?> {
                return arrayOfNulls(size)
            }
        }
    }
}