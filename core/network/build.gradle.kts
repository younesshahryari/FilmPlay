plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.example.core.network"
    compileSdk = 34
    defaultConfig {
        minSdk = 24

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    packaging {
        resources {
             excludes += "/META-INF/**"
        }
    }
}

dependencies {

    with(libs){
        implementation(ktor.client.core)
        implementation(ktor.client.okhttp)
        implementation(ktor.client.contentnegotiation)
        implementation(ktor.client.logging.jvm)
        implementation(ktor.client.serialization.kotlinx.json)
        implementation(ktor.client.auth)

        implementation(hilt.compose)
        implementation(hilt.android)
        kapt(hilt.compiler)

        implementation(kotlinx.serialization.json)
        implementation(androidx.paging.runtime.ktx)
        implementation(androidx.core.ktx)
        implementation(androidx.appcompat)
        implementation(material)
        testImplementation(junit)
        androidTestImplementation(androidx.junit)
        androidTestImplementation(androidx.espresso.core)
    }


}