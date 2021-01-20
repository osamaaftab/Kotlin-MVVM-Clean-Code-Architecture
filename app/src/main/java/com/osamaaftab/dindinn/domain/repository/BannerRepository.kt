package com.osamaaftab.dindinn.domain.repository

import com.osamaaftab.dindinn.domain.model.Banner
import com.osamaaftab.dindinn.domain.model.Menu
import io.reactivex.Single

interface BannerRepository {
    fun getBanners(): Single<Banner>

}