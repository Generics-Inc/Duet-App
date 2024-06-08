plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.jetpack.compose.compiler)
}

android {
    namespace = "inc.generics.duet"
    compileSdk = 34

    defaultConfig {
        applicationId = "inc.generics.duet"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        multiDexEnabled = true

        addManifestPlaceholders(mapOf(
            "VKIDRedirectHost" to "vk.com",
            "VKIDRedirectScheme" to "vk51923090",
            "VKIDClientID" to 51923090,
            "VKIDClientSecret" to "bkbTxdCGBcIz1pBjdekq"
        ))
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
        isCoreLibraryDesugaringEnabled = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":core:presentation"))
    implementation(project(":core:utils-data"))
    implementation(project(":animated-navbar"))

    implementation(project(":data-sources:duet-local"))
    implementation(project(":data-sources:duet-api"))

    //features and features-data <----
    implementation(project(":features:no-active-group"))

    implementation(project(":features:authorization"))
    implementation(project(":data:authorization-data"))

    implementation(project(":features:create-new-group"))
    implementation(project(":data:create-new-group-data"))

    implementation(project(":features:group-without-partner"))
    implementation(project(":data:group-without-partner-data"))

    implementation(project(":features:requests"))
    implementation(project(":data:requests-data"))

    implementation(project(":features:join-to-group"))
    implementation(project(":data:join-to-group-data"))

    implementation(project(":features:group-left-by-partner"))
    implementation(project(":data:group-left-by-partner-data"))

    implementation(project(":features:profile"))
    implementation(project(":data:profile-data"))

    implementation(project(":features:movie"))
    //----/>

    implementation(project(":android-utils"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    implementation(libs.androidx.runtime.livedata)

    implementation(libs.di.koin.core)
    implementation(libs.di.koin.android)
    implementation(libs.di.koin.androidx.compose)

    implementation(libs.coil.compose)

    implementation(libs.androidx.navigation.compose)

    coreLibraryDesugaring(libs.desugar.android)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}