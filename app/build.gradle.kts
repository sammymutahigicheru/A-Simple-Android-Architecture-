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
}

dependencies {
    //app dependencies
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.20")
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.2.1")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    api("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
    //loggin-interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")

    //Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.2.0")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.2.0")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")

    //Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")

    //Hilt
    implementation("com.google.dagger:hilt-android:2.28.1-alpha")
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha02")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    kapt("com.google.dagger:hilt-android-compiler:2.28.1-alpha")
    kapt("androidx.hilt:hilt-compiler:1.0.0-alpha02")

    //Room
    implementation("androidx.room:room-runtime:2.2.5")
    implementation("androidx.room:room-ktx:2.2.5")
    kapt("androidx.room:room-compiler:2.2.5")

    //Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.2")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.2")

    //Glide
    implementation("com.github.bumptech.glide:glide:4.11.0")
    kapt("com.github.bumptech.glide:compiler:4.11.0")

    //Timber
    implementation("com.jakewharton.timber:timber:4.7.1")
    /*
    * Unit Tests
    * */
    testImplementation("junit:junit:4.+")
    testImplementation("com.google.truth:truth:1.0.1")
    // For Robolectric tests.
    testImplementation("com.google.dagger:hilt-android-testing:2.28-alpha")
    // ...with Kotlin.
    kaptTest("com.google.dagger:hilt-android-compiler:2.28-alpha")
    testImplementation("io.mockk:mockk:1.10.0")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.8.1")
    testImplementation("org.robolectric:robolectric:4.4-beta-1")


    /*
    * Instrumentation Tests
    *
    * */
    androidTestImplementation("androidx.test:core:1.0.0")

    // AndroidJUnitRunner and JUnit Rules
    androidTestImplementation("androidx.test:runner:1.1.0")
    androidTestImplementation("androidx.test:rules:1.1.0")

    // Assertions
    androidTestImplementation("androidx.test.ext:junit:1.0.0")
    androidTestImplementation("androidx.test.ext:truth:1.0.0")
    androidTestImplementation("com.google.truth:truth:0.42")

    // Espresso dependencies
    androidTestImplementation("androidx.test.espresso:espresso-core:3.1.0")
    androidTestImplementation("androidx.test.espresso:espresso-contrib:3.1.0")
    androidTestImplementation("androidx.test.espresso:espresso-intents:3.1.0")
    androidTestImplementation("androidx.test.espresso:espresso-accessibility:3.1.0")
    androidTestImplementation("androidx.test.espresso:espresso-web:3.1.0")
    androidTestImplementation("androidx.test.espresso.idling:idling-concurrent:3.1.0")
    //espresso idling resource
    androidTestImplementation("androidx.test.espresso:espresso-idling-resource:3.1.0")
    androidTestImplementation("com.jakewharton.espresso:okhttp3-idling-resource:1.0.0")

    //Navigation and Fragments
    debugImplementation("androidx.fragment:fragment-testing:1.2.5")
    androidTestImplementation("androidx.navigation:navigation-testing:2.3.2")

    //Arch test
    androidTestImplementation("androidx.arch.core:core-testing:2.1.0")

    //Hilt
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.28-alpha")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.28-alpha")

    //Mockito
    androidTestImplementation("org.mockito:mockito-core:2.19.0")
    androidTestImplementation("org.mockito:mockito-android:2.19.0")
    androidTestImplementation("com.squareup.okhttp3:mockwebserver:4.8.1")

}