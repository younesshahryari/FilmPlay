plugins {
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.secrets.gradle) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
}
