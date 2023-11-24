@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.android.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.carlosdiestro.datastore"
    compileSdk = 34

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation(project(":user"))
    implementation(project(":app_settings"))

    implementation(libs.androidx.core.ktx)

    // Hilt
    implementation(libs.dagger.hilt.android)
    ksp(libs.dagger.hilt.android.compiler)

    // Datastore
    implementation(libs.androidx.datastore.preferences)

    // Android Ui Test
    androidTestImplementation(libs.bundles.ui.test)

    // Unit Test
    testImplementation(libs.junit)
}