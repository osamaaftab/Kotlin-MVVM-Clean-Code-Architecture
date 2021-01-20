package com.osamaaftab.dindinn.di.module

import com.osamaaftab.dindinn.data.source.remote.ApiService
import org.koin.dsl.module
import retrofit2.Retrofit

val ApiServicesModule = module {

    single { provideApiService(get()) }
}

fun provideApiService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}