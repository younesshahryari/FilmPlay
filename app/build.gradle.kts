plugins {
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.secrets.gradle)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.app"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        buildConfig = true
    }
    packaging {
        resources {
             excludes += "/META-INF/**"
        }
    }
}

dependencies {

    implementation(projects.core.ui)
    implementation(projects.core.data)
    implementation(projects.core.model)
    implementation(projects.feature.movie)
    implementation(projects.feature.tvshow)
    implementation(libs.androidx.adaptive.android)

    with(libs) {
        implementation(material)
        implementation(androidx.splashscreen)
        implementation(androidx.ui)
        implementation(androidx.core.ktx)
        implementation(androidx.material3)
        implementation(compose.navigation)
        implementation(androidx.ui.graphics)
        implementation(androidx.activity.compose)
        implementation(androidx.ui.tooling.preview)
        implementation(androidx.lifecycle.runtime.ktx)
        implementation(accompanist.systemuicontroller)
        implementation(platform(androidx.compose.bom))

        kapt(hilt.compiler)
        implementation(coil)
        implementation(timber)
        implementation(hilt.compose)
        implementation(hilt.android)
        implementation(bundles.arrow)
        implementation(kotlin.immutable)
        implementation(kotlinx.serialization.json)

        debugImplementation(androidx.ui.tooling)
        debugImplementation(androidx.ui.test.manifest)

        testImplementation(junit)
        androidTestImplementation(androidx.junit)
        androidTestImplementation(androidx.espresso.core)
        androidTestImplementation(androidx.ui.test.junit4)
        androidTestImplementation(platform(androidx.compose.bom))
    }

}
