package com.osamaaftab.dindinn.domain.usecase.base

import com.osamaaftab.dindinn.data.ApiErrorHandle
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<I, O>(private val apiErrorHandle: ApiErrorHandle) :
    UseCase<I, Single<O>>() {

    fun execute(
        compositeDisposable: CompositeDisposable,
        param: I?,
        onResponse: UseCaseResponse<O>
    ) {
        compositeDisposable.add(
            run(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    onResponse.onSuccess(it)
                }, {
                    onResponse.onError(it, apiErrorHandle.traceErrorException(it))
                })
        )
    }
}