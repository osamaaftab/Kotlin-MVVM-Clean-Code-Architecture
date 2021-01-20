package com.osamaaftab.dindinn.presentation.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {
    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun dispose() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    override fun onCleared() {
        super.onCleared()
        dispose()
    }
}