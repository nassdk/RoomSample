plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlinx-serialization")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
        applicationId("com.test.roomsample")
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        javaCompileOptions {
            annotationProcessorOptions {
                argument(key = "room.schemaLocation", value = "${projectDir}/schemas")
                argument(key = "room.incremental", value = "true")
                argument(key = "room.expandProjection", value = "true")
            }
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}

dependencies {

    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.31")

    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.fragment:fragment-ktx:1.3.3")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")

    implementation("com.google.android.material:material:1.3.0")

    implementation("androidx.room:room-runtime:2.3.0")
    kapt("androidx.room:room-compiler:2.3.0")

    implementation("androidx.room:room-ktx:2.3.0")

    implementation("com.google.dagger:dagger:2.33")
    kapt("com.google.dagger:dagger-compiler:2.33")

    implementation("com.google.code.gson:gson:2.8.6")

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")

    implementation("com.arkivanov.mvikotlin:mvikotlin:2.0.1")
    implementation("com.arkivanov.mvikotlin:mvikotlin-main:2.0.1")
    implementation("com.arkivanov.mvikotlin:mvikotlin-extensions-coroutines:2.0.1")

    implementation("com.squareup.okhttp3:okhttp:4.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.3")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation("com.hannesdorfmann:adapterdelegates4-kotlin-dsl-viewbinding:4.3.0")

    implementation("com.jakewharton.timber:timber:4.7.1")

    implementation("com.github.aartikov.Alligator:alligator:4.1.0")
}