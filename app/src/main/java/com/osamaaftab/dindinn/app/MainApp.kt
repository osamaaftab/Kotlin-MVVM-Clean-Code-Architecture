package com.osamaaftab.dindinn.app

import android.app.Application
import com.osamaaftab.dindinn.di.module.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApp : Application() {


    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApp)
            modules(
                listOf(
                    AppModule,
                    ActivityModule,
                    UseCaseModule,
                    NetWorkModule,
                    RepositoryModule,
                    ApiServicesModule
                )
            )
        }
    }
}