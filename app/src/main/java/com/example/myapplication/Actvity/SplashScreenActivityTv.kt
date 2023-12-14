package com.example.myapplication.Actvity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.VideoView
import com.example.myapplication.Config
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.network.RetrofitClient
import com.example.myapplication.utils.PreferenceUtils

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.AccessController

@SuppressLint("CustomSplashScreen")
class SplashScreenActivityTv : Activity() {

    lateinit var sharedPreferences: SharedPreferences
    var accestoken: String? = null
    var subscribe: String? = null

    // private var userProfile: UserProfile? = null
    private var is_reviewdata: Integer? = null
    private lateinit var appversion: TextView
    private lateinit var sdkversion: TextView

    private val TAG = SplashScreenActivityTv::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        appversion = findViewById<TextView>(R.id.appversion)
        sdkversion = findViewById<TextView>(R.id.sdkversion)
        appversion.text = "APK version: " + Config.APKVersion
        sdkversion.text = "SDK Version (Android): 34"

        openHomeFun()

        /*       val retrofit = RetrofitClient.getRetrofitInstance()
               val api = retrofit.create(Dashboard::class.java)
               val accessToken = "Bearer " + PreferenceUtils.getInstance().getAccessTokenPref(
                   applicationContext
               )
               val call: Call<AppInfo> = api.getAppInfo(Config.Device_Type, accessToken)
               call.enqueue(object : Callback<AppInfo?> {
                   override fun onResponse(call: Call<AppInfo?>, response: Response<AppInfo?>) {
                       if (response.code() == 200) {
                           Log.i(
                               TAG,
                               "onResponse:review -- " + response.body()!!.is_review +"is_review"+response.body()!!.websiteurl + response.body()!!.enable_mobile_login + response.body()!!.enable_email_login + response.body()!!.enable_qr_login
                           )
                           PreferenceUtils.getInstance().setWebsiteUrlPref(
                               this@SplashScreenActivityTv,
                               response.body()!!.websiteurl
                           )
                           PreferenceUtils.getInstance().setENABLE_EMAIL_LOGINPref(
                               this@SplashScreenActivityTv,
                               response.body()!!.enable_email_login
                           )
                           PreferenceUtils.getInstance().setENABLE_MOBILE_LOGINPref(
                               this@SplashScreenActivityTv,
                               response.body()!!.enable_mobile_login
                           )
                           PreferenceUtils.getInstance().setENABLE_QR_LOGINPref(
                               this@SplashScreenActivityTv,
                               response.body()!!.enable_qr_login
                           )
                           PreferenceUtils.getInstance().setENABLE_CouponsPref(
                               this@SplashScreenActivityTv,
                               response.body()!!.coupons
                           )

                           if(response.body()!!.is_review.equals(1)){
                               PreferenceUtils.getInstance().setLOGIN_DISABLEPref(
                                   applicationContext, "1"
                               )
                               gotomainscreen()
                           }else{

                               PreferenceUtils.getInstance().setLOGIN_DISABLEPref(
                                   applicationContext, "xyz"
                               )
                               if (BuildConfig.FLAVOR.equals(
                                       "mitwa_tv",
                                       ignoreCase = true
                                   ) || BuildConfig.FLAVOR.equals(
                                       "uvtv",
                                       ignoreCase = true
                                   ) || BuildConfig.FLAVOR.equals(
                                       "adnott",
                                       ignoreCase = true
                                   ) || BuildConfig.FLAVOR.equals("amuzi", ignoreCase = true) || BuildConfig.FLAVOR.equals(
                                       "darshott",
                                       ignoreCase = true
                                   ) || BuildConfig.FLAVOR.equals("omtvott", ignoreCase = true)
                                   || BuildConfig.FLAVOR.equals("fastone", ignoreCase = true)
                                   || BuildConfig.FLAVOR.equals("vtv", ignoreCase = true)
                                   || BuildConfig.FLAVOR.equals("solidtv", ignoreCase = true)
                                   || BuildConfig.FLAVOR.equals("vyasott", ignoreCase = true)
                               ) {
                                   findViewById<LinearLayout>(R.id.splash_screen_ll).visibility == View.INVISIBLE

                                   val path = "android.resource://" + packageName + "/" + R.raw.splashvideio
                                   findViewById<VideoView>(R.id.imageView).setVideoURI(Uri.parse(path))
                                   findViewById<VideoView>(R.id.imageView).start()
                                   findViewById<VideoView>(R.id.imageView).setOnCompletionListener {
                                       openHome()

                                   }

                               }else {
                                   findViewById<LinearLayout>(R.id.splash_screen_ll).visibility = View.VISIBLE
                                   if (BuildConfig.FLAVOR.equals("candor", ignoreCase = true) || BuildConfig.FLAVOR.equals(
                                           "naaptolott"
                                       ) || BuildConfig.FLAVOR.equals("omtvott") || BuildConfig.FLAVOR.equals("fastone") || BuildConfig.FLAVOR.equals(
                                           "xploreindia"
                                       )
                                   ) {
                                       findViewById<ImageView>(R.id.splash_img_view).visibility = View.INVISIBLE

                                   }
                                   openHomeFun()

                               }
                           }


                       } else if (response.code() == 401) {

                           // signOut();
                       } else if (response.errorBody() != null) {
                           //  CMHelper.setSnackBar(requireView(), response.errorBody().toString(), 2);
                       } else {
                           if (AccessController.getContext() != null) {
                           }
                       }
                   }

                   override fun onFailure(call: Call<AppInfo?>, t: Throwable) {
                       //   CMHelper.setSnackBar(requireView(), t.getMessage(), 2);
                       if (AccessController.getContext() != null) {
                           //      Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                       } else {
                       }
                   }
               })
               if (BuildConfig.FLAVOR.contentEquals("solidtv")) {
                   getUserProfileDataFromServer()
               }*/


    }

    private fun openHomeFun() {
        Handler().postDelayed({
            if (this != null) {
                if (PreferenceUtils.getInstance().getLoginPref(applicationContext)
                        .contentEquals("true")
                ) {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("dataFrom","SplashScreen")
                    startActivity(intent)
                } else {
                    val intent = Intent(this, LoginScreenActivity::class.java)
                    startActivity(intent)
                }
                this.finishAffinity()
                this.overridePendingTransition(R.anim.enter, R.anim.exit)
            }
        }, 1000)

    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        Log.e("viewAllActivity", "***** keyCode =" + keyCode + "event :" + event)
        when (keyCode) {
            KeyEvent.KEYCODE_BACK -> return false
            KeyEvent.KEYCODE_DPAD_CENTER -> return false
            KeyEvent.KEYCODE_DPAD_LEFT -> return false
            KeyEvent.KEYCODE_DPAD_RIGHT -> return false
            KeyEvent.KEYCODE_DPAD_UP -> {
                Log.e("SPLASH ACTIVITY", "movieIndex : ")
                return false
            }

            KeyEvent.KEYCODE_DPAD_UP_LEFT -> return false
            KeyEvent.KEYCODE_DPAD_UP_RIGHT -> return false
            KeyEvent.KEYCODE_DPAD_DOWN -> return false
            KeyEvent.KEYCODE_DPAD_DOWN_LEFT -> return false
            KeyEvent.KEYCODE_DPAD_DOWN_RIGHT -> return false
        }
        return super.onKeyDown(keyCode, event)
    }
}