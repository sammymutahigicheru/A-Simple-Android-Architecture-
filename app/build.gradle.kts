plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-android-extensions")
}

android {
    compileSdkVersion(30)

    defaultConfig {
        applicationId = "com.sammy.androidarchitecture"
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunner = "com.sammy.androidarchitecture.MockTestRunner"
    }

    buildTypes {
        getByName("release") {
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
    buildFeatures {
        dataBinding = true
    }
    configurations.all {
        resolutionStrategy.force("com.google.code.findbugs:jsr305:3.0.0")
    }
}

dependencies {
    //app dependencies
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.30")
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    api("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
    //loggin-interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")

    //Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.0")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.3.0")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")

    //Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.2")

    //Hilt
    implementation("com.google.dagger:hilt-android:2.32-alpha")
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    kapt("com.google.dagger:hilt-android-compiler:2.32-alpha")
    kapt("androidx.hilt:hilt-compiler:1.0.0-alpha03")

    //Room
    implementation("androidx.room:room-runtime:2.2.6")
    implementation("androidx.room:room-ktx:2.2.6")
    kapt("androidx.room:room-compiler:2.2.6")

    //Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.3")

    //Glide
    implementation("com.github.bumptech.glide:glide:4.12.0")
    kapt("com.github.bumptech.glide:compiler:4.12.0")

    //Timber
    implementation("com.jakewharton.timber:timber:4.7.1")
    /*
    * JUnit
    * */
    // Required -- JUnit 4 framework
    testImplementation ("junit:junit:4.13.2")
    // Optional -- Robolectric environment
    testImplementation ("androidx.test:core:1.3.0")
    // Optional -- Mockito framework
    testImplementation ("org.mockito:mockito-core:2.19.0")
   /*
   * Tests
   * */
    androidTestImplementation ("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
    //Hilt
    // For Robolectric tests.
    testImplementation ("com.google.dagger:hilt-android-testing:2.32-alpha")
    // ...with Kotlin.
    kaptTest ("com.google.dagger:hilt-android-compiler:2.32-alpha")


    // For instrumented tests.
    androidTestImplementation ("com.google.dagger:hilt-android-testing:2.32-alpha")
    // ...with Kotlin.
    kaptAndroidTest ("com.google.dagger:hilt-android-compiler:2.32-alpha")

    //fragment tests
    debugImplementation ("androidx.fragment:fragment-testing:1.3.0")

}