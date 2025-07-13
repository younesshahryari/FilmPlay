plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.core.ui"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        vectorDrawables {
            useSupportLibrary = true
        }
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
}

dependencies {
    implementation(projects.core.designsystem)
    implementation(projects.core.model)
    with(libs) {
        implementation(platform(androidx.compose.bom))
        implementation(coil)
        implementation(androidx.core.ktx)
        implementation(androidx.appcompat)
        implementation(androidx.material3)
        implementation(androidx.paging.runtime.ktx)
        implementation(androidx.paging.compose)
        implementation(kotlinx.serialization.json)
        implementation(material)
        testImplementation(junit)
        androidTestImplementation(androidx.junit)
        androidTestImplementation(androidx.espresso.core)
    }

}