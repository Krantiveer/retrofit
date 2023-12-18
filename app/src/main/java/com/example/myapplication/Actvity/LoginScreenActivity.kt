package com.example.myapplication.Actvity

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.util.StateSet
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Config
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.network.RetrofitClient
import com.example.myapplication.network.api.AppInfo
import com.example.myapplication.network.api.MainApi
import com.example.myapplication.network.model.DeviceInfo
import com.example.myapplication.network.model.GeneratePair
import com.example.myapplication.utils.PreferenceUtils
import com.example.myapplication.utils.ToastMsg
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.AccessController

private lateinit var pairCodeTextView: TextView // Declare as a class property
private lateinit var progress_bar: ProgressBar // Declare as a class property
val handler = Handler()
val handlerqr = Handler()
var randomNumber: String? = null
private const val TAG = "LoginScreenActivity"


class LoginScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE

        pairCodeTextView = findViewById(R.id.paircode)
        progress_bar = findViewById(R.id.progress_bar_load)

        fetchPairAPI();

    }


    fun bt_verified_login(view: View) {
/*
        fetchScreenversionAPI(PreferenceUtils.getInstance().getPairIDPref(applicationContext))
*/
        gotoMainScreen()

    }
/*
    private fun fetchScreenversionAPI(id: String) {
        val retrofitcms = RetrofitClient.getRetrofitInstanceCMS()
        val api = retrofitcms.create(MainApi::class.java)
        val accessToken = "Bearer ";
        Log.i(TAG, "fetchValidateAPI: ")

        val call: Call<Long> = api.getScreenVersion(id)
        call.enqueue(object : Callback<Long?> {
            override fun onResponse(call: Call<Long?>, response: Response<Long?>) {
                if (response.code() == 200) {
                    if (response.body()?.toInt() == -1) {
                        Log.i(
                            TAG,
                            "onResponse:SCREEN_VERSION_CODE -false " + (response.body()
                                ?.toString())
                        )
                        Toast.makeText(
                            applicationContext,
                            "Screen Paired , Please schedule your content",
                            Toast.LENGTH_LONG
                        ).show()

                    } else {

                        //   handlerscreen.removeCallbacksAndMessages(null)


                        PreferenceUtils.getInstance().setSCREEN_VERSION_CODEPref(
                            applicationContext,
                            response.body().toString()
                        )
                        Log.i(
                            TAG,
                            "onResponse:SCREEN_VERSION_CODE else true" + (response.body()
                                ?.toString())
                        )


                    }



                    Log.i(TAG, "success--> " + response.body())


                } else if (response.code() == 401) {

                } else if (response.errorBody() != null) {
                    if (AccessController.getContext() != null) {
                        Toast.makeText(
                            applicationContext,
                            "sorry! Something went wrong. Please try again after some time" + response.errorBody(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    if (AccessController.getContext() != null) {
                        Toast.makeText(
                            applicationContext,
                            "sorry! Something went wrong. Please try again after some time",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            override fun onFailure(call: Call<Long?>, t: Throwable) {
                //   CMHelper.setSnackBar(requireView(), t.getMessage(), 2);
                if (AccessController.getContext() != null) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                } else {
                }
            }
        })

    }
*/


    fun gotoMainScreen() {

        //   handler.removeCallbacksAndMessages(null)
        handlerqr.removeCallbacksAndMessages(null)

        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("dataFromlogin","Login")
        startActivity(intent)
        this.finishAffinity()
        this.overridePendingTransition(R.anim.enter, R.anim.exit)

    }

    fun fetchPairAPI() {
        progress_bar.visibility = View.VISIBLE

        val retrofit = RetrofitClient.getRetrofitInstance()
        val api = retrofit.create(MainApi::class.java)

        //val osVersion = Build.VERSION.RELEASE

        val call = api.getGeneratePairCode("1", "9", "2")
        call.enqueue(object : Callback<GeneratePair?> {
            override fun onResponse(call: Call<GeneratePair?>, response: Response<GeneratePair?>) {
                if (response.code() == 200 && response.body() != null) {
                    if (response.body()?.pairCode != null) {
                        if (!response.body()?.pairCode.isNullOrBlank()) {
                            pairCodeTextView.text = response.body()?.pairCode.toString()
                            fetchValidateAPI(
                                response.body()!!.id,
                                response.body()?.pairCode.toString()
                            );
                            PreferenceUtils.getInstance().setPAIR_CODEPref(
                                applicationContext,
                                response.body()?.pairCode.toString()
                            )

                            PreferenceUtils.getInstance().setPairIDPref(
                                applicationContext,
                                response.body()!!.id.toString()
                            )
                            CallHandlerCall()

                            Log.i(
                                TAG,
                                "PairCode screen id = paircode=>" + response.body()?.pairCode + "id" + response.body()!!.id
                            )
                        }
                    } else {
                        ToastMsg(this@LoginScreenActivity).toastIconError(getString(R.string.internet_toast))

                    }

                } else if (response.code() == 401) {

                    // signOut()
                } else {

                    //      ToastMsg(this@LoginScreenActivity).toastIconError(getString(R.string.error_toast))
                }
                progress_bar.visibility = View.INVISIBLE

            }

            override fun onFailure(call: Call<GeneratePair?>, t: Throwable) {
                // ToastMsg(this@LoginScreenActivity).toastIconError(getString(R.string.error_toast))
                Log.e("DetailsActivityPhando", "onFailure: $t")
                progress_bar.visibility = View.INVISIBLE

            }
        })
    }


    override fun onResume() {
        super.onResume()
        handlerqr.removeCallbacksAndMessages(null)
        //    handler.removeCallbacksAndMessages(null)
        /*     if(BuildConfig.FLAVOR.equalsIgnoreCase("kaafaltv")||BuildConfig.FLAVOR.equalsIgnoreCase("solidtv")){
        }else{*/
    }

    private fun CallHandlerCall() {
        handlerqr.postDelayed(object : Runnable {
            override fun run() {
                Log.i(TAG, "Handler run run:1 $randomNumber")
                handlerqr.postDelayed(this, 15000)
                //    CheckAccessCode(randomNumber)
                fetchValidateAPI(
                    PreferenceUtils.getInstance().getPairIDPref(applicationContext).toInt(),
                    PreferenceUtils.getInstance().getPAIR_CODEPref(applicationContext)
                )
                //Do something after 3 seconds
            }
        }, 3000)
    }

    private fun fetchValidateAPI(id: Int, paircode: String) {
        val retrofit = RetrofitClient.getRetrofitInstance()
        val api = retrofit.create(MainApi::class.java)
        val accessToken = "Bearer ";
        Log.i(TAG, "fetchValidateAPI: ")

        val call: Call<DeviceInfo> = api.validate(paircode, id)
        call.enqueue(object : Callback<DeviceInfo?> {
            override fun onResponse(call: Call<DeviceInfo?>, response: Response<DeviceInfo?>) {
                if (response.code() == 200) {
                    Log.i(
                        TAG,
                        "success--> " + PreferenceUtils.getInstance()
                            .getLoginPref(applicationContext)
                    )

                    PreferenceUtils.getInstance().setLoginPref(applicationContext, "true")
                    Log.i(
                        TAG,
                        "success--> " + PreferenceUtils.getInstance()
                            .getLoginPref(applicationContext)
                    )


                    PreferenceUtils.getInstance()
                        .setAccessTokenNPref(applicationContext, response.body()?.token)
                    gotoMainScreen()
                    // onGetAppInfoSuccess(response.body()!!)


                } else if (response.code() == 401) {

                } else if (response.errorBody() != null) {
                    if (AccessController.getContext() != null) {
                         /* Toast.makeText(
                              applicationContext,
                              "sorry! Something went wrong. Please try again after some time" + response.errorBody(),
                              Toast.LENGTH_SHORT
                          ).show()*/
                    }
                } else {
                    if (AccessController.getContext() != null) {
                        /*Toast.makeText(
                            applicationContext,
                            "sorry! Something went wrong. Please try again after some time",
                            Toast.LENGTH_SHORT
                        ).show()*/
                    }
                }
            }

            override fun onFailure(call: Call<DeviceInfo?>, t: Throwable) {
                //   CMHelper.setSnackBar(requireView(), t.getMessage(), 2);
                if (AccessController.getContext() != null) {
                //    Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                } else {
                }
            }
        })

    }

    /*   private fun CheckAccessCode(accessCode: String?) {
           val retrofit = RetrofitClient.getRetrofitInstance()
           val api = retrofit.create(SendOTPApi::class.java)
           val call = api.getCheckAccessCode(Config.API_KEY, accessCode)
           call.enqueue(object : Callback<User?> {
               override fun onResponse(call: Call<User?>, response: Response<User?>) {
                   if (response.code() == 200) {
                       assert(response.body() != null)
                       if (response.body()!!.status.equals("success", ignoreCase = true)) {
                           if (response.body()!!.access_token != null) {
                               handler.removeCallbacksAndMessages(null)
                               handlerqr.removeCallbacksAndMessages(null)
                               val user = response.body()
                               val db = DatabaseHelper(applicationContext)
                               if (db.userDataCount > 1) {
                                   db.deleteUserData()
                               } else {
                                   if (db.userDataCount == 0) {
                                       db.insertUserData(user)
                                   } else {
                                       db.updateUserData(user, 1)
                                   }
                               }
                               val preferences = getSharedPreferences(
                                   Constants.USER_LOGIN_STATUS,
                                   MODE_PRIVATE
                               ).edit()
                               preferences.putBoolean(Constants.USER_LOGIN_STATUS, true)
                               preferences.apply()
                               PreferenceUtils.getInstance().setAccessTokenNPref(
                                   applicationContext, response.body()!!
                                       .access_token
                               )
                               val intent = Intent(applicationContext, NewMainActivity::class.java)
                               startActivity(intent)
                               finishAffinity()
                               overridePendingTransition(R.anim.enter, R.anim.exit)

                               //save user login time, expire time
                               // updateSubscriptionStatus(user.getUserId());
                               progressBar!!.visibility = View.GONE
                               *//*    ll_send_otp.setVisibility(View.GONE);
                        ll_verify_otp.setVisibility(View.VISIBLE);
                        startTimer();
*//*
                        }
                        //    new ToastMsg(getApplicationContext()).toastIconError("Please try again Access Token Empty");
                        progressBar!!.visibility = View.GONE
                    } else {
                        //   new ToastMsg(getApplicationContext()).toastIconError(response.body().getData());
                        progressBar!!.visibility = View.GONE
                    }
                } else {
                    if (response.code() == 401) {
                        //   CMHelper.setSnackBar(this.getCurrentFocus(), String.valueOf("Please Enter OTP"), 2, 10000);
                        //    new ToastMsg(getApplicationContext()).toastIconError(response.message());
                    } else {
                        //  new ToastMsg(getApplicationContext()).toastIconError("Please Try Again Getting" + response.code());
                    }
                    progressBar!!.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<User?>, t: Throwable) {
                progressBar!!.visibility = View.GONE
                //    new ToastMsg(getApplicationContext()).toastIconError(getString(R.string.error_toast));
            }
        })
    }
*/
    private fun getAlphaNumericString(n: Int): String {

        // chose a Character random from this String
        val AlphaNumericString = ("ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789")

        // create StringBuffer size of AlphaNumericString
        val sb = StringBuilder(n)
        for (i in 0 until n) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            val index = (AlphaNumericString.length
                    * Math.random()).toInt()

            // add Character one by one in end of sb
            sb.append(AlphaNumericString[index])
        }
        return sb.toString()
    }


    fun userappversion() {
        val retrofit = RetrofitClient.getRetrofitInstance()
        val api = retrofit.create(MainApi::class.java)
        val accessToken = "Bearer ";

        val call: Call<AppInfo> = api.getAppInfo(Config.Device_Type, accessToken)
        call.enqueue(object : Callback<AppInfo?> {
            override fun onResponse(call: Call<AppInfo?>, response: Response<AppInfo?>) {
                if (response.code() == 200) {

                    // onGetAppInfoSuccess(response.body()!!)
                    PreferenceUtils.getInstance().setWatermarkLogoUrlPref(
                        applicationContext,
                        response.body()!!.playerLogo
                    )
                    PreferenceUtils.getInstance().setWatermarkEnablePref(
                        applicationContext,
                        response.body()!!.player_logo_enable
                    )


                } else if (response.code() == 401) {

                } else if (response.errorBody() != null) {
                    if (AccessController.getContext() != null) {
                        Toast.makeText(
                            applicationContext,
                            "sorry! Something went wrong. Please try again after some time" + response.errorBody(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    if (AccessController.getContext() != null) {
                        Toast.makeText(
                            applicationContext,
                            "sorry! Something went wrong. Please try again after some time",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            override fun onFailure(call: Call<AppInfo?>, t: Throwable) {
                //   CMHelper.setSnackBar(requireView(), t.getMessage(), 2);
                if (AccessController.getContext() != null) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                } else {
                }
            }
        })

    }


    /*
    private fun fetchapi() {
        val retrofit = RetrofitClient.getRetrofitInstance()
        val api = retrofit.create(MainApi::class.java)
        val accessToken = "Bearer "
        val call = api.maindata(accessToken)
        call.enqueue(object : Callback<RecordResponse.Record> {
            override fun onResponse(
                call: Call<RecordResponse.Record>,
                response: Response<RecordResponse.Record>
            ) {

                if (response.code() == 200) {
                    Log.i("kranti", "onResponse: "+response.body()!!.result)

                    val navItems: RecordResponse.Record? = response.body()
                   // val result:ResultData=response.body()!!.result
                    var resultData =navItems!!.result
                    Toast.makeText(
                        applicationContext,
                        "success"+resultData ,
                        Toast.LENGTH_LONG
                    ).show()
                    */
    /*val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
                        val adapter = MyListAdapter(navItems)
                        recyclerView.setHasFixedSize(true)
                        recyclerView.layoutManager = LinearLayoutManager(this)
                        recyclerView.adapter = adapter
    *//*



                } else if (response.errorBody() != null) {
                    if (AccessController.getContext() != null) {
                        Toast.makeText(
                            applicationContext,
                            "sorry! Something went wrong. Please try again after some time" + response.errorBody(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    //  CMHelper.setSnackBar(requireView(), response.errorBody().toString(), 2);
                } else {
                    if (AccessController.getContext() != null) {
                        Toast.makeText(
                            applicationContext,
                            "sorry! Something went wrong. Please try again after some time",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            override fun onFailure(call: Call<RecordResponse.Record>, t: Throwable) {
                //   CMHelper.setSnackBar(requireView(), t.getMessage(), 2);
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()

                if (AccessController.getContext() != null) {
                } else {
                }
            }
        })
    }
*/
    override fun onBackPressed() {

            // binding.slidingPaneLayout.openPane();
            val dialog: Dialog
            dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.layout_dialog_exit)
            dialog.setCancelable(true)
            val button_no = dialog.findViewById<View>(R.id.button_no) as Button
            button_no.background = getSelectorDrawable()
            button_no.setOnClickListener { dialog.dismiss() }

            val button_yes = dialog.findViewById<View>(R.id.button_yes) as Button
            button_yes.background = getSelectorDrawable()
            button_yes.setOnClickListener {
                dialog.dismiss()
                super.onBackPressed()

                this.finishAffinity()
            }
            dialog.show()
        }

    private fun getSelectorDrawable(): StateListDrawable? {
        val out = StateListDrawable()
        out.addState(
            intArrayOf(android.R.attr.state_focused), createFocusedDrawable(
                Color.parseColor("#EF3C23")
            )
        )
        out.addState(
            StateSet.WILD_CARD,
            createNormalDrawable(Color.parseColor("#80858B"))
        )
        return out
    }
    private fun createFocusedDrawable(color: Int): GradientDrawable? {
        val out = GradientDrawable()
        out.setColor(color)
        return out
    }

    private fun createNormalDrawable(color: Int): GradientDrawable? {
        val out = GradientDrawable()
        out.setColor(color)
        return out
    }

}