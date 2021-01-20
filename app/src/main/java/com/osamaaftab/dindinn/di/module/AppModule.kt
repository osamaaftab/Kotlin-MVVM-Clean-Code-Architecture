package com.osamaaftab.dindinn.di.module

import com.osamaaftab.dindinn.data.ApiErrorHandle
import org.koin.dsl.module

val AppModule = module {

    single { provideApiError() }
}

fun provideApiError(): ApiErrorHandle {
    return ApiErrorHandle()
}