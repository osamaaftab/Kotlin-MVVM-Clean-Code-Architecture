package com.osamaaftab.dindinn.data.repository

import com.osamaaftab.dindinn.data.source.remote.ApiService
import com.osamaaftab.dindinn.domain.model.Banner
import com.osamaaftab.dindinn.domain.repository.BannerRepository
import io.reactivex.Single

class BannerRepositoryImp(private val apiService: ApiService) : BannerRepository {
    override fun getBanners(): Single<Banner> {
        return apiService.getBanner()
    }
}