@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.android.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.google.play.services)
    alias(libs.plugins.androidx.room)
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
    room {
        schemaDirectory(path = "$projectDir/schemas")
    }
    ksp {
        arg("room.generateKotlin", "true")
    }
}

dependencies {
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

    // Room Database
    implementation(libs.bundles.room)
    ksp(libs.androidx.room.compiler)

    // Hilt
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.dagger.hilt.android)
    ksp(libs.dagger.hilt.android.compiler)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.play.services)

    // CameraX
    implementation(libs.bundles.camera)

    // Splashscreen
    implementation(libs.androidx.core.splashscreen)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.bundles.firebase)

    // Google
    implementation(libs.play.services.auth)

    // Preferences Data Store
    implementation(libs.androidx.datastore.preferences)

    // Compose Test
    androidTestImplementation(composeBom)
    androidTestImplementation(libs.bundles.compose.test)

    // Android Ui Test
    androidTestImplementation(libs.bundles.ui.test)

    // Unit Test
    testImplementation(libs.junit)
}