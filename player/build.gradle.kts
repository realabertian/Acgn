plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
}
//apply plugin: 'com.android.library'

android {
    compileSdkVersion(29)
    buildToolsVersion("29.0.3")

    defaultConfig {
        minSdkVersion(28)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        ndk {
            abiFilters("arm64-v8a", "x86_64")
//            abiFilters 'x86_64'
        }

        externalNativeBuild {
            cmake {
                cppFlags("-std=c++11")
            }
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro")
        }

        this.create("pre-release") {

        }
    }

    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
            version = "3.10.2"
        }
    }

    splits {
        abi {
            reset()
            include("x86_64", "arm64-v8a") //select ABIs to build APKs for
//            include 'x86_64' //select ABIs to build APKs for
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation("androidx.appcompat:appcompat:1.1.0")
    testImplementation("junit:junit:4.13")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
    implementation("androidx.core:core-ktx:1.3.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.72")
}