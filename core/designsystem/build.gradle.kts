plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.example.core.designsystem"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/**"
        }
    }
}

dependencies {
    with(libs) {
        implementation(platform(androidx.compose.bom))
        implementation(androidx.adaptive.android)
        implementation(androidx.material3.adaptive.navigation.suite.android)
        implementation(accompanist.systemuicontroller)
        implementation(androidx.ui)
        implementation(androidx.material3)
        implementation(androidx.core.ktx)
        implementation(androidx.appcompat)
        testImplementation(junit)
        androidTestImplementation(androidx.junit)
        androidTestImplementation(androidx.espresso.core)
    }
}