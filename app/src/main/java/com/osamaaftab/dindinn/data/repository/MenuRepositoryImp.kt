package com.osamaaftab.dindinn.data.repository

import com.osamaaftab.dindinn.data.source.remote.ApiService
import com.osamaaftab.dindinn.domain.model.Menu
import com.osamaaftab.dindinn.domain.repository.MenuRepository
import io.reactivex.Single

class MenuRepositoryImp(private val apiService: ApiService) : MenuRepository {
    override fun getMenus(): Single<Menu> {
        return apiService.getMenu()
    }
}