plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.registrationpage"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.registrationpage"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    }


        viewBinding {
            enable = true
        }
    
}

dependencies {

    dependencies {
        implementation(libs.livedata)
        implementation(libs.viewmodel)
        implementation(libs.appcompat)
        implementation(libs.material)
        implementation(libs.activity)
        implementation(libs.constraintlayout)

        // Unit testing dependencies
        testImplementation(libs.junit)
        androidTestImplementation(libs.ext.junit)
        androidTestImplementation(libs.espresso.core)
        androidTestImplementation(libs.test.runner)
        androidTestImplementation(libs.test.rules)
        testImplementation(libs.arch.core)
        testImplementation(libs.mockito.core)
        testImplementation(libs.mockito.inline)
        testImplementation(libs.org.robolectric)
    }
}