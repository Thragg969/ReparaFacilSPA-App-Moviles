plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.example.reparafacilspa"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.reparafacilspa"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    packaging {
        resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
}

dependencies {
    dependencies {
        // Compose
        implementation(platform("androidx.compose:compose-bom:2024.10.01"))
        implementation("androidx.activity:activity-compose:1.9.3")
        implementation("androidx.compose.ui:ui")
        implementation("androidx.compose.ui:ui-tooling-preview")
        implementation("androidx.compose.material3:material3:1.3.0")
        implementation("androidx.navigation:navigation-compose:2.8.3")

        // Material XML (para el theme)
        implementation("com.google.android.material:material:1.12.0")
        implementation("androidx.appcompat:appcompat:1.7.0")
        implementation("androidx.core:core-ktx:1.13.1")

        // DataStore (persistencia simple)
        implementation("androidx.datastore:datastore-preferences:1.1.1")

        // Retrofit
        implementation("com.squareup.okhttp3:okhttp:4.12.0")
        implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
        implementation("com.squareup.retrofit2:retrofit:2.11.0")
        implementation("com.squareup.retrofit2:converter-gson:2.11.0")

        // Coil (avatar)
        implementation("io.coil-kt:coil-compose:2.7.0")

        // Coroutines
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")
        // para usar await() en ubicación
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.9.0")

        // Google Location
        implementation("com.google.android.gms:play-services-location:21.3.0")

        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.2.1")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    }

}

