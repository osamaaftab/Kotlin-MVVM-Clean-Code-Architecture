package com.osamaaftab.dindinn.domain.usecase.base

import com.osamaaftab.dindinn.domain.model.ErrorModel

interface UseCaseResponse<T> {

    fun onSuccess(value: T)

    fun onError(error: Throwable, errorModel: ErrorModel?)
}