object Versions {
    const val androidGradlePlugin = "4.0.0"
    const val constraintLayout = "2.0.0-beta7"
    const val coreKtx = "1.0.0"
    const val dagger = "2.28.1"
    const val fragment = "1.3.0-alpha07"
    const val glide = "4.11.0"
    const val kotlin = "1.3.41"
    const val lifecycle = "2.2.0"
    const val navigation = "2.3.0"
    const val okHttpLoggingInterceptor = "4.7.2"
    const val paging = "2.1.0-rc01"
    const val retrofit = "2.9.0"
    const val room = "2.2.5"
    const val rxAndroid = "2.0.1"
    const val rxjava2 = "2.2.9"
    const val support = "1.1.0"
    const val work = "2.1.0-alpha01"
    const val preference = "1.1.0-rc01"
    const val viewpager = "2:1.0.0"
    const val rxjava2debug = "1.4.0"
}

//使用 BuildVerions 或者单独文件 AndroidConfigs
object BuildVersions {
    const val minSdk = 21
    const val compileSdk = 29
    const val targetSdk = 29
    const val buildTools = "29.0.3"
    const val versionCode = 1
    const val versionName = "1.0"
}

object Kotlin {
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val test = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    const val plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val allOpen = "org.jetbrains.kotlin:kotlin-allopen:${Versions.kotlin}"
}

object DataBinding {
    const val compiler = "com.android.databinding:compiler:${Versions.androidGradlePlugin}"
}

object Support {
    const val annotations = "androidx.annotation:annotation:${Versions.support}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.support}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.support}"
    const val cardView = "androidx.cardview:cardview:${Versions.support}"
    const val design = "com.google.android.material:material:${Versions.support}"
    const val v4 = "androidx.legacy:legacy-support-v4:${Versions.support}"
    const val coreUtils = "androidx.legacy:legacy-support-core-utils:${Versions.support}"
    const val swipeRefresh = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.support}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val fragmentRuntime = "androidx.fragment:fragment:${Versions.fragment}"
    const val fragmentRuntimeKtx = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    const val fragmentTesting = "androidx.fragment:fragment-testing:${Versions.fragment}"
}

object Lifecycle {
    const val runtime = "androidx.lifecycle:lifecycle-runtime:${Versions.lifecycle}"
    const val extensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val java8 = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
    const val compiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
}

object Navigation {
    const val runtime = "androidx.navigation:navigation-runtime:${Versions.navigation}"
    const val runtimeKtx = "androidx.navigation:navigation-runtime-ktx:${Versions.navigation}"
    const val fragment = "androidx.navigation:navigation-fragment:${Versions.navigation}"
    const val fragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val ui = "androidx.navigation:navigation-ui:${Versions.navigation}"
    const val uiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val safeArgsPlugin =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
}

object Paging {
    const val paging = "androidx.paging:paging-runtime:${Versions.paging}"
    const val pagingKtx = "androidx.paging:paging-runtime-ktx:${Versions.paging}"
    const val pagingRxjava = "androidx.paging:paging-rxjava2:${Versions.paging}"
    const val pagingRxjavaKtx = "androidx.paging:paging-rxjava2-ktx:${Versions.paging}"

    //testImplementation
    const val pagingCommon = "androidx.paging:paging-common:${Versions.paging}"
    const val pagingCommonKtx = "aandroidx.paging:paging-common-ktx:${Versions.paging}"
}

object ViewPager {
    const val viewpager2 = "androidx.viewpager2:viewpager:${Versions.viewpager}"
}

object Preference {
    const val core = "androidx.preference:preference:${Versions.preference}"
    const val ktx = "androidx.preference:preference-ktx:${Versions.preference}"
}


object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.room}"
    const val compiler = "androidx.room:room-compiler:${Versions.room}"
    const val rxjava2 = "androidx.room:room-rxjava2:${Versions.room}"
    const val testing = "androidx.room:room-testing:${Versions.room}"
}

object Work {
    const val runtime = "androidx.work:work-runtime:${Versions.work}"
    const val testing = "androidx.work:work-testing:${Versions.work}"
    const val firebase = "androidx.work:work-firebase:${Versions.work}"
    const val runtime_ktx = "androidx.work:work-runtime-ktx:${Versions.work}"
}

object Dagger {
    const val runtime = "com.google.dagger:dagger:${Versions.dagger}"
    const val android = "com.google.dagger:dagger-android:${Versions.dagger}"
    const val androidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    const val compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val androidSupportCompiler =
        "com.google.dagger:dagger-android-processor:${Versions.dagger}"
}

object RxJava {
    const val rxjava2 = "io.reactivex.rxjava2:rxjava:${Versions.rxjava2}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    const val rxjava2debug = "com.akaita.java:rxjava2-debug:${Versions.rxjava2debug}"
}

object Retrofit {
    const val runtime = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val scalars = "com.squareup.retrofit2:converter-scalars:${Versions.retrofit}"
    const val protobuf = "com.squareup.retrofit2:converter-protobuf:${Versions.retrofit}"
    const val mock = "com.squareup.retrofit2:retrofit-mock:${Versions.retrofit}"
    const val rxjava2 = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
}

object OkHttp {
    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpLoggingInterceptor}"
}


object Glide {
    const val runtime = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
}