package com.osamaaftab.dindinn.domain.usecase

import com.osamaaftab.dindinn.data.ApiErrorHandle
import com.osamaaftab.dindinn.domain.model.Banner
import com.osamaaftab.dindinn.domain.repository.BannerRepository
import com.osamaaftab.dindinn.domain.usecase.base.SingleUseCase
import io.reactivex.Single


class GetBannerUsecase(private val bannerRepository: BannerRepository, apiErrorHandle: ApiErrorHandle) :
    SingleUseCase<Any, Banner>(apiErrorHandle) {
    override fun run(input: Any?): Single<Banner> {
        return bannerRepository.getBanners()
    }
}