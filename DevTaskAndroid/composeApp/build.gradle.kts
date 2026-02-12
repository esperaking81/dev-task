plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.serialization.plugin)
    alias(libs.plugins.ksp)
}

kotlin {
    androidTarget()

    // If you want to prepare for iOS now (even if not using it yet):
    // iosArm64()
    // iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.androidx.room.runtime)
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.ktor.core)
            implementation(libs.ktor.content.client.negociation)
            implementation(libs.datastore.preferences)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.jetbrains.navigation3.ui)

            // Datastore
            implementation(libs.datastore)
            implementation(libs.datastore.preferences)
        }

        androidMain.dependencies {
            implementation(libs.androidx.core.ktx)
            implementation(libs.androidx.activity.compose)
            implementation(libs.ktor.client.cio) // Android specific engine
            implementation(libs.ktor.client.logging) // Android specific engine
        }
    }
}

android {
    namespace = "dev.espera.devtask"
    compileSdk = 36

    defaultConfig {
        applicationId = "dev.espera.devtask"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
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
}

dependencies {
    add("kspAndroid", libs.androidx.room.compiler)
}

