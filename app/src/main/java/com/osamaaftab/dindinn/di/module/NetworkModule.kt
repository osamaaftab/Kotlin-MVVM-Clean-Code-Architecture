package com.osamaaftab.dindinn.di.module

import android.content.Context
import com.osamaaftab.dindinn.data.MockClient
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val NetWorkModule = module {

    single { providesRetrofit(get()) }
    single { providesOkHttpClient(get()) }
    single { createMoshiConverterFactory() }
    single { createMoshi() }
}


fun providesRetrofit(
    okHttpClient: OkHttpClient
): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://test-app-of.azurewebsites.net")
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
}

fun createMoshi(): Moshi {
    return Moshi.Builder().build()
}

fun createMoshiConverterFactory(): MoshiConverterFactory {
    return MoshiConverterFactory.create()
}

fun providesOkHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(MockClient(context)).build()
}