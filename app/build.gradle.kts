plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "co.uniquindio.seguimientodos"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "co.uniquindio.seguimientodos"
        minSdk = 28
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
    // Compose Navigation
    implementation("androidx.navigation:navigation-compose:2.7.7")
// ViewModels para Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
// Coil (Para la imagen asíncrona mostrada en WelcomeScreen, tal como en la referencia del proyecto)
    implementation("io.coil-kt.coil3:coil-compose:3.0.0-alpha01")
// Material Icons Extended (Requerido para iconos como el ojo de la contraseña y flechas direccionales)
    implementation("androidx.compose.material:material-icons-extended:1.6.0")

}