@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.android.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.carlosdiestro.needit"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.carlosdiestro.needit"
        minSdk = 28
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
    packaging {
        resources {
            resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {

    implementation(project(":design_system"))
    implementation(project(":local_database"))
    implementation(project(":remote_database"))
    implementation(project(":datastore"))
    implementation(project(":auth"))
    implementation(project(":wish"))
    implementation(project(":user"))
    implementation(project(":friend"))
    implementation(project(":app_settings"))
    implementation(project(":device_storage"))
    implementation(project(":feature:home"))
    implementation(project(":feature:sign_in"))
    implementation(project(":feature:gifts"))
    implementation(project(":feature:friends"))
    implementation(project(":feature:camera"))
    implementation(project(":feature:upsert_wish"))
    implementation(project(":feature:wish_details"))
    implementation(project(":feature:account"))
    implementation(project(":feature:settings"))
    implementation(project(":feature:search"))
    implementation(project(":feature:notifications"))

    // Core
    implementation(libs.androidx.core.ktx)
    implementation(platform(libs.kotlin.bom))

    // Activity Compose
    implementation(libs.androidx.activity.compose)

    // Compose
    val composeBom = platform(libs.compose.bom)
    implementation(composeBom)
    implementation(libs.bundles.compose.ui)

    // UI
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.compose.material3.window.sizeclass)

    // Coil Image Loader
    implementation(libs.coil)

    // Lifecycle & ViewModel
    implementation(libs.bundles.lifecycle)

    // Navigation
    implementation(libs.bundles.navigation)

    // Hilt
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.dagger.hilt.android)
    implementation(libs.androidx.hilt.common)
    ksp(libs.dagger.hilt.android.compiler)
    ksp(libs.androidx.hilt.compiler)

    // Splashscreen
    implementation(libs.androidx.core.splashscreen)

    // Compose Test
    androidTestImplementation(composeBom)
    androidTestImplementation(libs.bundles.compose.test)

    // Android Ui Test
    androidTestImplementation(libs.bundles.ui.test)

    // Unit Test
    testImplementation(libs.junit)
}