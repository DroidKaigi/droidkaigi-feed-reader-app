package io.github.droidkaigi.feeder

object Versions {
    const val compose = "1.2.0-alpha04"
    const val coroutines = "1.6.0-native-mt"
}

object Dep {
    object Compose {
        const val activity = "androidx.activity:activity-compose:1.5.0-alpha03"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout-compose:1.0.0"
        const val navigation = "androidx.navigation:navigation-compose:2.4.0-beta01"
        const val livedata = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
        const val runtime = "androidx.compose.runtime:runtime:${Versions.compose}"
        const val foundation = "androidx.compose.foundation:foundation:${Versions.compose}"
        const val layout = "androidx.compose.foundation:foundation-layout:${Versions.compose}"
        const val ui = "androidx.compose.ui:ui:${Versions.compose}"
        const val test = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
        const val tooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        const val util = "androidx.compose.ui:ui-util:${Versions.compose}"
        const val material = "androidx.compose.material:material:${Versions.compose}"
        const val iconsExtended =
            "androidx.compose.material:material-icons-extended:${Versions.compose}"
        const val animation = "androidx.compose.animation:animation:${Versions.compose}"
        const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:1.0.0-alpha03"
    }

    object Jetpack {
        const val appcompat = "androidx.appcompat:appcompat:1.3.0"
        const val startup = "androidx.startup:startup-runtime:1.0.0"
        const val browser = "androidx.browser:browser:1.3.0"
        const val dataStore = "androidx.datastore:datastore-preferences:1.0.0-alpha06"

        const val core = "androidx.core:core-ktx:1.6.0"
        const val fragment = "androidx.fragment:fragment-ktx:1.3.0"
        const val activity = "androidx.activity:activity-ktx:1.2.0"
        const val lifecycle = "androidx.lifecycle:lifecycle-livedata-ktx:2.3.0"
        const val hiltCommon = "androidx.hilt:hilt-common:1.0.0-alpha03"
        const val hiltLifeCycleViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"

        object Test {
            const val ext = "androidx.test.ext:junit:1.1.2"
            const val espresso = "androidx.test.espresso:espresso-core:3.3.0"
        }
    }

    object MultiplatformSettings {
        const val settings = "com.russhwolf:multiplatform-settings:0.8.1"
        const val coroutines = "com.russhwolf:multiplatform-settings-coroutines:0.8.1"
        const val datastore = "com.russhwolf:multiplatform-settings-datastore:0.8.1"
        const val test = "com.russhwolf:multiplatform-settings-test:0.8.1"
    }

    object Kotlin {
        // bom import does not working...
        const val plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.20-RC"
        const val serializationPlugin = "org.jetbrains.kotlin:kotlin-serialization:1.6.20-RC"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:1.6.20-RC"
        const val stdlibJdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.6.20-RC"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:1.6.20-RC"
    }

    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    }

    object Serialization {
        const val core = "org.jetbrains.kotlinx:kotlinx-serialization-core:1.3.2"
    }

    object KotlinTest {
        const val common = "org.jetbrains.kotlin:kotlin-test-common"
        const val annotationCommon = "org.jetbrains.kotlin:kotlin-test-annotations-common"
        const val junit = "org.jetbrains.kotlin:kotlin-test-junit"
    }

    object Ktor {
        const val core = "io.ktor:ktor-client-core:2.0.0-beta-1"
        const val json = "io.ktor:ktor-client-json:2.0.0-beta-1"
        const val logging = "io.ktor:ktor-client-logging:2.0.0-beta-1"
        const val okHttp = "io.ktor:ktor-client-okhttp:2.0.0-beta-1"
        const val ios = "io.ktor:ktor-client-ios:2.0.0-beta-1"
        const val serialization = "io.ktor:ktor-client-serialization:2.0.0-beta-1"
        const val mock = "io.ktor:ktor-client-mock:2.0.0-beta-1"
    }

    object PlayServices {
        const val plugin = "com.google.gms:google-services:4.3.5"
    }

    const val datetime = "org.jetbrains.kotlinx:kotlinx-datetime:0.1.1"
    const val ktlint = "com.pinterest:ktlint:0.42.1"
    const val exhaustivePlugin = "app.cash.exhaustive:exhaustive-gradle:0.2.0"

    object Dagger {
        const val plugin = "com.google.dagger:hilt-android-gradle-plugin:2.40.5"
        const val hiltAndroid = "com.google.dagger:hilt-android:2.40.5"
        const val hiltAndroidTesting = "com.google.dagger:hilt-android-testing:2.40.5"
        const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:2.40.5"
    }

    object Accompanist {
        const val insets = "com.google.accompanist:accompanist-insets:0.24.3-alpha"
        const val pager = "com.google.accompanist:accompanist-pager:0.24.3-alpha"
        const val pagerIndicators = "com.google.accompanist:accompanist-pager-indicators:" +
            "0.24.3-alpha"
        const val systemuicontroller =
            "com.google.accompanist:accompanist-systemuicontroller:0.24.3-alpha"
    }

    object FirebaseCrashlytics {
        const val plugin = "com.google.firebase:firebase-crashlytics-gradle:2.5.2"
        const val android = "com.google.firebase:firebase-crashlytics:17.4.1"
    }

    const val coil = "io.coil-kt:coil-compose:2.0.0-rc01"

    const val firebaseAuth = "dev.gitlive:firebase-auth:1.2.0"
    const val firebaseMessaging = "com.google.firebase:firebase-messaging-ktx:21.0.1"

    const val playServicesOssLicensesPlugin = "com.google.android.gms:oss-licenses-plugin:0.10.5"
    const val playServicesOssLicenses = "com.google.android.gms:play-services-oss-licenses:17.0.0"

    const val buildKonfig = "com.codingfeline.buildkonfig:buildkonfig-gradle-plugin:0.11.0"

    object SQLDelight {
        const val plugin = "com.squareup.sqldelight:gradle-plugin:1.5.3"
        const val androidDriver = "com.squareup.sqldelight:android-driver:1.5.3"
        const val coroutinesExtensions = "com.squareup.sqldelight:coroutines-extensions:1.5.3"
    }

    object Flipper {
        const val flipper = "com.facebook.flipper:flipper:0.82.1"
        const val networkPlugin = "com.facebook.flipper:flipper-network-plugin:0.76.0"
        const val soLoader = "com.facebook.soloader:soloader:0.10.1"
    }

    object Koin {
        const val core = "io.insert-koin:koin-core:3.0.1-beta-1"
        const val test = "io.insert-koin:koin-test:3.0.1-beta-1"
        const val ktor = "io.insert-koin:koin-ktor:3.0.1-beta-1"
    }

    const val desugarJdkLibs = "com.android.tools:desugar_jdk_libs:1.1.5"

    const val napier = "io.github.aakira:napier:2.1.0"
}
