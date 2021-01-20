package com.osamaaftab.dindinn.data.source.remote

import com.osamaaftab.dindinn.domain.model.Banner
import com.osamaaftab.dindinn.domain.model.Menu
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @GET("/api/v1/getMenu")
    fun getMenu(): Single<Menu>

    @GET("/api/v1/getBanner")
    fun getBanner(): Single<Banner>
}