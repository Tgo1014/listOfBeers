@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Dependencies.compileSdk
    namespace = "tgo1014.listofbeers"
    defaultConfig {
        applicationId = "tgo1014.beerbox"
        minSdk = Dependencies.minSdk
        targetSdk = 33
        versionCode = 1
        versionName = "0.1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
        buildConfigField("String", "BASE_URL", "\"https://api.punkapi.com/v2/\"")
    }
    buildTypes {
        getByName("release") {
            isShrinkResources = true
            isMinifyEnabled = true
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
    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":presentation"))
    implementation(Dependencies.Injection.hilt)
    kapt(Dependencies.Injection.hiltKapt)
    implementation(Dependencies.timber)
    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.serialization)
    implementation(Dependencies.Network.interceptor)
    implementation(Dependencies.Network.kotlinxSerializationConverter)
}