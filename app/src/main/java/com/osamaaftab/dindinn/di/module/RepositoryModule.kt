package com.osamaaftab.dindinn.di.module

import com.osamaaftab.dindinn.data.repository.BannerRepositoryImp
import com.osamaaftab.dindinn.data.repository.MenuRepositoryImp
import com.osamaaftab.dindinn.data.source.remote.ApiService
import com.osamaaftab.dindinn.domain.repository.BannerRepository
import com.osamaaftab.dindinn.domain.repository.MenuRepository
import org.koin.dsl.module

val RepositoryModule = module {

    single { provideMenuRepository(get()) }
    single { provideBannerRepository(get()) }

}

fun provideMenuRepository(apiService: ApiService): MenuRepository {
    return MenuRepositoryImp(apiService)
}


fun provideBannerRepository(apiService: ApiService): BannerRepository {
    return BannerRepositoryImp(apiService)
}