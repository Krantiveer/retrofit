package com.example.myapplication.network.api;



import com.example.myapplication.network.model.ApiResponse;
import com.example.myapplication.network.model.DeviceInfo;
import com.example.myapplication.network.model.GeneratePair;
import com.example.myapplication.network.model.RecordResponse;
import com.example.myapplication.network.model.ScreenScheduleResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MainApi {
  /*  @GET("v3/b/6458ec108e4aa6225e98d54d")
    Call<RecordResponseParser.RecordResponse>*/
    @GET("b/6458ec108e4aa6225e98d54d")
    Call<RecordResponse.Record> getIndexData();
  @GET("b/6458ec108e4aa6225e98d54d")
  Call<RecordResponse.Record> maindata(
            @Header("Authorization") String token);
  @GET("b/6458ec108e4aa6225e98d54d")
  Call<ApiResponse> getApiResponse(); // Adjust the endpoint accordingly
  @GET("userappversion")
  Call<AppInfo> getAppInfo(
          @Query("app_type") String appType, @Query("device_id") String device_id

  );
  @GET("/cms/api/v1/sdc/screen/code/validate")
  Call<DeviceInfo> validate(
          @Query("code") String code,
          @Query("screenId") Integer screenId
  );

  @POST("/cms/api/v1/sdc/screen/code/generate")
  Call<GeneratePair> getGeneratePairCode();

  @GET("svms/api/v1/svc/screenversion/{id}")
  Call<ApiResponse> getScreenVersion(@Path("id") int id);
  @GET("cms/api/v1/sdc/screen/{screenId}/active-schedule")
  Call<ScreenScheduleResponse> getActiveSchedule(@Path("screenId") String screenId);

  // Replace YourResponseType with the type you expect to receive from the API

}
