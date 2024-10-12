plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("kapt")
    id("kotlin-parcelize")
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.qader.angelatask"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.qader.angelatask"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        // Enabling multidex support.
        multiDexEnabled = true
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
//        jvmTarget = "17"
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    implementation(libs.google.dagger.hilt)
    kapt(libs.google.dagger.hilt.compiler)

    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(libs.google.gson)
    implementation("androidx.navigation:navigation-compose:2.5.3")

    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.6.21")

    implementation("junit:junit:4.13.2")
    implementation("org.mockito:mockito-core:4.0.0")
    implementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")

}
kapt {
    correctErrorTypes = true
}