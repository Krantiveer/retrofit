package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Spannable
import android.text.SpannableString
import android.text.style.AbsoluteSizeSpan
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import com.example.myapplication.Actvity.handlerqr
import com.example.myapplication.Screens.HorizontalView
import com.example.myapplication.Screens.SplitHalfHorizontalView
import com.example.myapplication.Screens.SplitThirdHorizontalView
import com.example.myapplication.Screens.VerticalView
import com.example.myapplication.network.RetrofitClient
import com.example.myapplication.network.api.MainApi
import com.example.myapplication.network.model.ApiResponse
import com.example.myapplication.network.model.Content
import com.example.myapplication.network.model.ScreenScheduleResponse
import com.example.myapplication.utils.PreferenceUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.AccessController


private const val TAG = "LoginScreenActivity"
val handlerscreen = Handler()

class MainActivity : AppCompatActivity() {
    var fullText = "NO SCHEDULES \n Please schedule your playlist"
    lateinit var ll_verify_otp: LinearLayout
    lateinit var progress_bar_load_main: ProgressBar
    lateinit var datafrom:String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var textView = findViewById<TextView>(R.id.paircode_main)
        ll_verify_otp = findViewById(R.id.ll_verify_otp)

        progress_bar_load_main = findViewById<ProgressBar>(R.id.progress_bar_load_main)
        progress_bar_load_main.visibility=View.VISIBLE
        ll_verify_otp.visibility=View.INVISIBLE
        val spannableString = SpannableString(fullText)
        spannableString.setSpan(
            AbsoluteSizeSpan(40, true),
            0,
            12,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(
            AbsoluteSizeSpan(18, true),
            13,
            fullText.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        textView.text = spannableString
        CallHandlerCall()

        fetchScreenversionAPI(PreferenceUtils.getInstance().getPairIDPref(applicationContext))
        datafrom= intent.getStringExtra("dataFrom").toString()
        if (datafrom != null) {
            // Do something with the received data
            // For example, log it or use it in your app logic
            fetchActiveScheduleAPI(
                PreferenceUtils.getInstance().getPairIDPref(applicationContext)
            )
        }
        // gotoHorizontalScreen()
    }

    private fun CallHandlerCall() {
        handlerscreen.postDelayed(object : Runnable {
            override fun run() {
                handlerscreen.postDelayed(this, 15000)
                //    CheckAccessCode(randomNumber)
                fetchScreenversionAPIHandler(
                    PreferenceUtils.getInstance().getPairIDPref(applicationContext)
                )

                //Do something after 3 seconds
            }
        }, 2000)
    }

    private fun fetchActiveScheduleAPI(id: String) {
        val retrofit = RetrofitClient.getRetrofitInstance()
        val api = retrofit.create(MainApi::class.java)
        val accessToken = "Bearer ";
        Log.i(TAG, "fetchValidateAPI: ")

        val call: Call<ScreenScheduleResponse> = api.getActiveSchedule(id)
        call.enqueue(object : Callback<ScreenScheduleResponse?> {
            override fun onResponse(
                call: Call<ScreenScheduleResponse?>,
                response: Response<ScreenScheduleResponse?>
            ) {
                if (response.code() == 200) {
               //     Toast.makeText(applicationContext,"startDateTime -"+response.body()!!.schedules.get(0).startDateTime+" endDateTime -"+response.body()!!.schedules.get(0).endDateTime,Toast.LENGTH_LONG).show()
                    Log.i(
                        TAG,
                        "success--> "
                    )

                 /*   Toast.makeText(applicationContext, "Sucess Activation", Toast.LENGTH_SHORT)
                        .show()
*/
                    onGetActiveScheduleSuccess(response.body()!!)



                } else if (response.code() == 400) {
                    progress_bar_load_main.visibility=View.INVISIBLE
                    ll_verify_otp.visibility=View.VISIBLE

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

            override fun onFailure(call: Call<ScreenScheduleResponse?>, t: Throwable) {
                //   CMHelper.setSnackBar(requireView(), t.getMessage(), 2);
                if (AccessController.getContext() != null) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                } else {
                }
            }
        })

    }

    fun restartApp(context: Context) {
        val packageManager = context.packageManager
        val intent = packageManager.getLaunchIntentForPackage(context.packageName)
        val componentName = intent?.component
        val mainIntent = Intent.makeRestartActivityTask(componentName)
        context.startActivity(mainIntent)
        Runtime.getRuntime().exit(0)
    }

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
                        ll_verify_otp.visibility = View.VISIBLE
                        progress_bar_load_main.visibility = View.INVISIBLE

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
                     /*   ll_verify_otp.visibility = View.INVISIBLE
                        progress_bar_load_main.visibility = View.VISIBLE
*/
                        fetchActiveScheduleAPI(
                            PreferenceUtils.getInstance().getPairIDPref(applicationContext)
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

    private fun fetchScreenversionAPIHandler(id: String) {
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
                        /*   Toast.makeText(
                               applicationContext,
                               "Screen Paired , Please schedule your content",
                               Toast.LENGTH_LONG
                           ).show()*/

                    } else {

                        //   handlerscreen.removeCallbacksAndMessages(null)
                        if (PreferenceUtils.getInstance()
                                .getSCREEN_VERSION_CODEPref(applicationContext).contentEquals(
                                response.body()?.toString()
                            )
                        ) {
                            Log.i(
                                TAG,
                                "onResponse:SCREEN_VERSION_CODE --no data assign" + (response.body()
                                    ?.toString())
                            )

                        } else {
                            PreferenceUtils.getInstance().setSCREEN_VERSION_CODEPref(
                                applicationContext,
                                response.body().toString()
                            )
                            /*
                                                        val intent = Intent(applicationContext, MainActivity::class.java)
                                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                                                        startActivity(intent)
                                                        finish()
                            */


                            Log.i(
                                TAG,
                                "onResponse:SCREEN_VERSION_CODE else true" + (response.body()
                                    ?.toString())
                            )

                            fetchActiveScheduleAPI(
                                PreferenceUtils.getInstance().getPairIDPref(applicationContext)
                            )
                        }

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

    private fun onGetActiveScheduleSuccess(body: ScreenScheduleResponse) {
        when (body.schedules.get(0).playlists.get(0).layout.layoutId) {
            1 -> {
                gotoVerticalScreen(body.schedules.get(0).playlists.get(0).layout.zones.get(0).contents)
            }

            2 -> {
                gotoVerticalSplitScreen(
                    body.schedules.get(0).playlists.get(0).layout.zones.get(0).contents,
                    body.schedules.get(0).playlists.get(0).layout.zones.get(1).contents
                )
            }

            3 -> print("randomVal == 3")
            4 -> print("randomVal == 4")
            5 -> print("randomVal == 5")
            6 -> print("randomVal == 6")
            7 -> print("randomVal == 7")
            8 -> print("randomVal == 8")
            9 -> {
                gotoHorizontalScreen(body.schedules.get(0).playlists.get(0).layout.zones.get(0).contents)
            }

            10 -> {
                gotoSplitHorizontalScreen(
                    body.schedules.get(0).playlists.get(0).layout.zones.get(0).contents,
                    body.schedules.get(0).playlists.get(0).layout.zones.get(1).contents
                )
            }

            11 -> {
                gotoThirdSplitHorizontalScreen(
                    body.schedules.get(0).playlists.get(0).layout.zones.get(
                        0
                    ).contents,
                    body.schedules.get(0).playlists.get(0).layout.zones.get(1).contents,
                    body.schedules.get(0).playlists.get(0).layout.zones.get(2).contents
                )
            }

            12 -> print("randomVal == 12")
            13 -> print("randomVal == 13")
            14 -> print("randomVal == 14")
            15 -> print("randomVal == 15")
            else -> {
                print("x is neither 1 nor 2")
            }
        }
        Log.i(
            TAG,
            "onGetAppInfoSuccess:--> " + body.schedules.get(0).playlists.get(0).layout.layoutId
        )

    }

    fun gotoVerticalScreen(content_data: List<Content>) {
        //   handler.removeCallbacksAndMessages(null)
        handlerqr.removeCallbacksAndMessages(null)
        //  handlerscreen.removeCallbacksAndMessages(null)
        val intent = Intent(this, VerticalView::class.java)
        intent.putParcelableArrayListExtra("CONTENT_LIST", ArrayList(content_data))
        startActivity(intent)

    }

    fun gotoVerticalSplitScreen(content_data: List<Content>, content_data_second: List<Content>) {
        //   handler.removeCallbacksAndMessages(null)
        handlerqr.removeCallbacksAndMessages(null)
        //  handlerscreen.removeCallbacksAndMessages(null)
        val intent = Intent(this, SplitHalfHorizontalView::class.java)
        intent.putParcelableArrayListExtra("CONTENT_LIST", ArrayList(content_data))
        intent.putParcelableArrayListExtra("CONTENT_LIST_TWO", ArrayList(content_data_second))
        startActivity(intent)
    }

    fun gotoHorizontalScreen(content_data: List<Content>) {
        //   handler.removeCallbacksAndMessages(null)
        handlerqr.removeCallbacksAndMessages(null)
        //  handlerscreen.removeCallbacksAndMessages(null)
        val intent = Intent(this, HorizontalView::class.java)
        intent.putParcelableArrayListExtra("CONTENT_LIST", ArrayList(content_data))
        startActivity(intent)

    }

    fun gotoSplitHorizontalScreen(content_data: List<Content>, content_data_second: List<Content>) {
        //   handler.removeCallbacksAndMessages(null)
        handlerqr.removeCallbacksAndMessages(null)
        //  handlerscreen.removeCallbacksAndMessages(null)
        val intent = Intent(this, SplitHalfHorizontalView::class.java)
        intent.putParcelableArrayListExtra("CONTENT_LIST", ArrayList(content_data))
        intent.putParcelableArrayListExtra("CONTENT_LIST_TWO", ArrayList(content_data_second))
        startActivity(intent)

    }

    fun gotoThirdSplitHorizontalScreen(
        content_data: List<Content>,
        content_data_second: List<Content>,
        content_data_third: List<Content>
    ) {
        //   handler.removeCallbacksAndMessages(null)
        handlerqr.removeCallbacksAndMessages(null)
        //  handlerscreen.removeCallbacksAndMessages(null)
        val intent = Intent(this, SplitThirdHorizontalView::class.java)
        intent.putParcelableArrayListExtra("CONTENT_LIST", ArrayList(content_data))
        intent.putParcelableArrayListExtra("CONTENT_LIST_TWO", ArrayList(content_data_second))
        intent.putParcelableArrayListExtra("CONTENT_LIST_Third", ArrayList(content_data_third))
        startActivity(intent)

    }

    override fun onResume() {
        super.onResume()
        // handlerscreen.removeCallbacksAndMessages(null)
        //    handler.removeCallbacksAndMessages(null)
        /*     if(BuildConfig.FLAVOR.equalsIgnoreCase("kaafaltv")||BuildConfig.FLAVOR.equalsIgnoreCase("solidtv")){
        }else{*/
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
                        *//*val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
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
*//*
private open fun fetchDataFromApi() {
    val apiClient = ApiClient()
    apiClient.fetchDataFromApi(object : Callback<ApiResponse> {
        override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
            if (response.isSuccessful) {
                val dataList: List<ApiResponse.Record.Result.IndexItem> = response.body()!!
                    .record.result.index
                dataAdapter = DataAdapter(dataList)
                recyclerView.setAdapter(dataAdapter)
            } else {
                // Handle unsuccessful responses here
            }
        }

        override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
            // Handle failure here
        }
    })
}
*/

    private fun fetchapi() {
        val retrofit = RetrofitClient.getRetrofitInstance()
        val api = retrofit.create(MainApi::class.java)
        val accessToken = "Bearer "
        val call = api.getApiResponse()
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(
                call: Call<ApiResponse>, response: Response<ApiResponse>
            ) {

                if (response.code() == 200) {
                    Log.i("kranti", "onResponse: " + response.body())/* val dataList: List<ApiResponse.Record.Result.IndexItem> =
                        response.body()!!.record.result.index
*/
                    val navItems: ApiResponse? = response.body()
                    // val result:ResultData=response.body()!!.result

                    Toast.makeText(
                        applicationContext, "success", Toast.LENGTH_LONG
                    ).show()/*val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
                    val adapter = MyListAdapter(navItems)
                    recyclerView.setHasFixedSize(true)
                    recyclerView.layoutManager = LinearLayoutManager(this)
                    recyclerView.adapter = adapter
*/


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

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                //   CMHelper.setSnackBar(requireView(), t.getMessage(), 2);
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()

                if (AccessController.getContext() != null) {
                } else {
                }
            }
        })
    }

    /*
    private void loadRows(List<FeaturesGenreAndMovie> homeContents, ArrayList<Video> slideArrayList) {

        HomeBannerAdapter adapter = new HomeBannerAdapter(slideArrayList, getContext());
        adapter.setSendInterfacedata(description -> setTextViewBanner(description));
        adapter.setSendInterfaceClick(() -> releasePlayer());
        recyclerViewBannerTop.setAdapter(adapter);

        HomeBannerSecAdapter homeBannerSecAdapter = new HomeBannerSecAdapter(homeContents, getContext());

        homeBannerSecAdapter.setSendInterfacedata(description -> setTextViewBanner(description));
        homeBannerSecAdapter.setSendInterfaceClick(() -> releasePlayer());

        recyclerViewBannerBottom.setAdapter(homeBannerSecAdapter);
    }*//*
    private void loadRowsnew(List<FeaturesGenreAndMovie> homeContents, ArrayList<Video> slideArrayList) {

        HomeBannerAdapter adapter = new HomeBannerAdapter(slideArrayList, getContext());
        adapter.setSendInterfacedata(description -> setTextViewBanner(description));
        adapter.setSendInterfaceClick(() -> releasePlayer());
        recyclerViewBannerTop.setAdapter(adapter);
*//*
        HomeBannerSecAdapter homeBannerSecAdapter = new HomeBannerSecAdapter(homeContents, getContext());

        homeBannerSecAdapter.setSendInterfacedata(description -> setTextViewBanner(description));
        homeBannerSecAdapter.setSendInterfaceClick(() -> releasePlayer());

        recyclerViewBannerBottom.setAdapter(homeBannerSecAdapter);*//*

    }
*//*
    private fun loadRows(homeContents: List<RecordResponse.Record>) {
        val homeBannerSecAdapter = HomeBannerSecAdapter(homeContents, getContext())

        homeBannerSecAdapter.setSendInterfaceClick { releasePlayer() }
        recyclerViewBannerBottom.setAdapter(homeBannerSecAdapter)
    }*/

}