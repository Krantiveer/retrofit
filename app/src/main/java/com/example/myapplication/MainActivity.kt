package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Actvity.handlerqr
import com.example.myapplication.Screens.HorizontalView
import com.example.myapplication.network.RetrofitClient
import com.example.myapplication.network.api.MainApi
import com.example.myapplication.network.model.ApiResponse
import com.example.myapplication.network.model.ScreenScheduleResponse
import com.example.myapplication.network.model.MyListData
import com.example.myapplication.utils.CMHelper
import com.example.myapplication.utils.PreferenceUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.AccessController

private const val TAG = "LoginScreenActivity"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // fetchapi()

        fetchScreenversionAPI(PreferenceUtils.getInstance().getPairIDPref(applicationContext))
        // gotoHorizontalScreen()


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
                    Log.i(
                        TAG,
                        "success--> " )

                    Toast.makeText(applicationContext, "Sucess Activation", Toast.LENGTH_SHORT).show()

                    onGetActiveScheduleSuccess(response.body()!!)


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

            override fun onFailure(call: Call<ScreenScheduleResponse?>, t: Throwable) {
                //   CMHelper.setSnackBar(requireView(), t.getMessage(), 2);
                if (AccessController.getContext() != null) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                } else {
                }
            }
        })

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
                        Toast.makeText(
                            applicationContext, "Sucess-->" + response.body(), Toast.LENGTH_LONG
                        ).show()
                    } else {
                        fetchActiveScheduleAPI(PreferenceUtils.getInstance().getPairIDPref(applicationContext))

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
        body.schedules.get(0).playlists.get(0).layout.layoutId
        Log.i(TAG, "onGetAppInfoSuccess:--> " + body.schedules.get(0).playlists.get(0).layout.layoutId)
    }

    fun gotoHorizontalScreen() {
        //   handler.removeCallbacksAndMessages(null)
        handlerqr.removeCallbacksAndMessages(null)
        val intent = Intent(this, HorizontalView::class.java)
        startActivity(intent)

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