package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.network.RetrofitClient
import com.example.myapplication.network.api.MainApi
import com.example.myapplication.network.model.ApiResponse
import com.example.myapplication.network.model.MyListData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.AccessController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // fetchapi()

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
/*
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
                call: Call<ApiResponse>,
                response: Response<ApiResponse>
            ) {

                if (response.code() == 200) {
                    Log.i("kranti", "onResponse: "+response.body())
                   /* val dataList: List<ApiResponse.Record.Result.IndexItem> =
                        response.body()!!.record.result.index
*/
                    val navItems: ApiResponse? = response.body()
                   // val result:ResultData=response.body()!!.result

                    Toast.makeText(
                        applicationContext,
                        "success" ,
                        Toast.LENGTH_LONG
                    ).show()
                    /*val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
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
    }*/
    /*
    private void loadRowsnew(List<FeaturesGenreAndMovie> homeContents, ArrayList<Video> slideArrayList) {

        HomeBannerAdapter adapter = new HomeBannerAdapter(slideArrayList, getContext());
        adapter.setSendInterfacedata(description -> setTextViewBanner(description));
        adapter.setSendInterfaceClick(() -> releasePlayer());
        recyclerViewBannerTop.setAdapter(adapter);
*/
    /*
        HomeBannerSecAdapter homeBannerSecAdapter = new HomeBannerSecAdapter(homeContents, getContext());

        homeBannerSecAdapter.setSendInterfacedata(description -> setTextViewBanner(description));
        homeBannerSecAdapter.setSendInterfaceClick(() -> releasePlayer());

        recyclerViewBannerBottom.setAdapter(homeBannerSecAdapter);*/
    /*

    }
*/
/*
    private fun loadRows(homeContents: List<RecordResponse.Record>) {
        val homeBannerSecAdapter = HomeBannerSecAdapter(homeContents, getContext())

        homeBannerSecAdapter.setSendInterfaceClick { releasePlayer() }
        recyclerViewBannerBottom.setAdapter(homeBannerSecAdapter)
    }*/

}