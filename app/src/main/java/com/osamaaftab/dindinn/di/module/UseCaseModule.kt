package com.osamaaftab.dindinn.di.module

import com.osamaaftab.dindinn.data.ApiErrorHandle
import com.osamaaftab.dindinn.domain.repository.BannerRepository
import com.osamaaftab.dindinn.domain.repository.MenuRepository
import com.osamaaftab.dindinn.domain.usecase.GetBannerUsecase
import com.osamaaftab.dindinn.domain.usecase.GetMenuUsecase
import org.koin.dsl.module

val UseCaseModule = module {
    single { ProvideGetMenuUsecase(get(), provideApiError()) }
    single { ProvideGetBannerUsecase(get(), provideApiError()) }

}

fun ProvideGetMenuUsecase(
    menuRepository: MenuRepository,
    apiErrorHandle: ApiErrorHandle
): GetMenuUsecase {
    return GetMenuUsecase(menuRepository, apiErrorHandle)
}

fun ProvideGetBannerUsecase(
    bannerRepository: BannerRepository,
    apiErrorHandle: ApiErrorHandle
): GetBannerUsecase {
    return GetBannerUsecase(bannerRepository, apiErrorHandle)
}