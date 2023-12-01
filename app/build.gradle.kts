plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

    dependencies {
        //Glide
        implementation ("com.github.bumptech.glide:glide:4.13.2")
        implementation("androidx.core:core-ktx:1.9.0")
        implementation("androidx.appcompat:appcompat:1.6.1")
        implementation("com.google.android.material:material:1.10.0")
        implementation("androidx.constraintlayout:constraintlayout:2.1.4")
        implementation("com.squareup.retrofit2:retrofit:2.9.0")
        implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-RC1") // Add this line for HttpLoggingInterceptor
        implementation("com.squareup.retrofit2:converter-gson:2.9.0")
        implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")
        implementation ("androidx.cardview:cardview:1.0.0")
        implementation ("androidx.recyclerview:recyclerview:1.3.2")
        // leanback

        implementation ("androidx.leanback:leanback:1.1.0-rc02")
        implementation ("androidx.leanback:leanback-preference:1.0.0")
        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.1.5")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

//exo player
/*
        implementation ("com.google.android.exoplayer:exoplayer:2.19.0")
        implementation ("com.google.android.exoplayer:exoplayer-dash:2.19.0")

        implementation ("com.google.android.exoplayer:exoplayer-hls:2.19.0")
        implementation ("com.google.android.exoplayer:extension-rtmp:2.19.0")
        implementation ("com.google.android.exoplayer:extension-cast:2.19.0")
        implementation ("com.google.android.exoplayer:exoplayer-smoothstreaming:2.19.0")
        implementation ("com.google.android.exoplayer:exoplayer")
        implementation ("com.google.android.exoplayer:exoplayer-rtsp:2.18.4")
*/

        implementation ("com.google.android.exoplayer:exoplayer-ui:2.19.1")
        implementation ("com.google.android.exoplayer:exoplayer-core:2.19.1")



    }
