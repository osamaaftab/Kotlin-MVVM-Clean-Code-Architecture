package com.osamaaftab.dindinn.domain.usecase

import com.osamaaftab.dindinn.data.ApiErrorHandle
import com.osamaaftab.dindinn.domain.model.Menu
import com.osamaaftab.dindinn.domain.repository.MenuRepository
import com.osamaaftab.dindinn.domain.usecase.base.SingleUseCase
import io.reactivex.Single

class GetMenuUsecase(private val menuRepository: MenuRepository, apiErrorHandle: ApiErrorHandle) :
    SingleUseCase<Any, Menu>(apiErrorHandle) {
    override fun run(input: Any?): Single<Menu> {
        return menuRepository.getMenus()
    }
}