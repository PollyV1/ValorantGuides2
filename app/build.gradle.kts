plugins {
    alias(libs.plugins.android.application) // Ensure 'libs.plugins.android.application' is correctly defined in your build script
    alias(libs.plugins.jetbrains.kotlin.android) // Ensure 'libs.plugins.jetbrains.kotlin.android' is correctly defined in your build script
}

android {
    namespace = "com.polly.valorantguides"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.polly.valorantguides"
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

    buildFeatures {
        viewBinding = true
        //noinspection DataBindingWithoutKapt
        dataBinding = true
    }
}

dependencies {
    implementation(libs.retrofit) // Ensure 'libs.retrofit' is correctly defined in your build script
    implementation(libs.converter.gson) // Ensure 'libs.converter.gson' is correctly defined in your build script
    implementation(libs.androidx.core.ktx) // Ensure 'libs.androidx.core.ktx' is correctly defined in your build script
    implementation(libs.androidx.appcompat) // Ensure 'libs.androidx.appcompat' is correctly defined in your build script
    implementation(libs.material) // Ensure 'libs.material' is correctly defined in your build script
    implementation(libs.androidx.activity) // Ensure 'libs.androidx.activity' is correctly defined in your build script
    implementation(libs.androidx.constraintlayout) // Ensure 'libs.androidx.constraintlayout' is correctly defined in your build script
    testImplementation(libs.junit) // Ensure 'libs.junit' is correctly defined in your build script
    androidTestImplementation(libs.androidx.junit) // Ensure 'libs.androidx.junit' is correctly defined in your build script
    androidTestImplementation(libs.androidx.espresso.core) // Ensure 'libs.androidx.espresso.core' is correctly defined in your build script
    implementation(libs.picasso)
    implementation(libs.androidx.recyclerview)
}
