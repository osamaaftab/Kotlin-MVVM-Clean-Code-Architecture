package com.osamaaftab.dindinn.domain.repository

import com.osamaaftab.dindinn.domain.model.Menu
import io.reactivex.Single


interface MenuRepository {
    fun getMenus(): Single<Menu>
}
