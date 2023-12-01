package com.example.myapplication;

public class Config {

   public static final String Device_Type = "androidtv";
    public static final String DOMAIN = "https://cms-api.digitalsigns.ai/";
    public static final String Publisher_id = "86";
    public static final String API_SERVER_URL = DOMAIN;
    public static final String API_SERVER_URL_SVMS = "http://svms-api.digitalsigns.ai/";
    //copy your api key from php admin dashboard & paste below
    public static final String API_KEY = "75xi3uer76tb7krer3mjgqei";

    //Phone authentication
    public static final boolean ENABLE_PHONE_LOGIN = true;
    //Google authentication
    public static final boolean ENABLE_GOOGLE_LOGIN = false;

    public static final String VideoURLTypeHls = "hls";
 /*   public static final boolean LOGIN_REQUIRED = true;
    public static final boolean ENABLE_EMAIL_LOGIN = true;
    public static final boolean ENABLE_MOBILE_LOGIN = true;
    public static final boolean ENABLE_QR_LOGIN = true;*/

    public static final String NPAW_APPNAME = "MitwaTV";

    public static final boolean CouponCodeEnable = true;
    public static final boolean DirectVideoPlayEnable = false;
    public static final boolean DirectLoginEnable = false;


    /*String API_KEY = "se_team_9b3431b0";*/ String SECRET = "bcdee06e8490949422c071437da5c5ed";

    String DEEPLINK_KEY = "deeplink";
    String PASSTHROUGH_KEY = "passthrough";
    String IS_DEFERRED_KEY = "isDeferred";

}
