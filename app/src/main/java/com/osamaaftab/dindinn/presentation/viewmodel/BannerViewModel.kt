package com.osamaaftab.dindinn.presentation.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.osamaaftab.dindinn.domain.model.Banner
import com.osamaaftab.dindinn.domain.model.BannerItem
import com.osamaaftab.dindinn.domain.model.ErrorModel
import com.osamaaftab.dindinn.domain.usecase.GetBannerUsecase
import com.osamaaftab.dindinn.domain.usecase.base.UseCaseResponse
import com.osamaaftab.dindinn.presentation.base.BaseViewModel

class BannerViewModel constructor(private val getBannerUsecase: GetBannerUsecase) : BaseViewModel() {

    private val onShow = MutableLiveData<Boolean>()
    private val onError = MutableLiveData<String>()
    private val bannerList = MutableLiveData<List<BannerItem>>()


    private fun getBannerUseCaseResponse() = object : UseCaseResponse<Banner> {
        override fun onSuccess(value: Banner) {
            onShow.value = false
            Log.i(ContentValues.TAG, "result: $value")
            bannerList.value = value.bannerList

        }

        override fun onError(error: Throwable, errorModel: ErrorModel?) {
            Log.i(ContentValues.TAG, "error: $errorModel?.message code")
            onShow.value = false
            onError.value = errorModel?.getErrorMessage().toString()
        }
    }

    fun fetchBanner() {
        getBannerUsecase.execute(compositeDisposable, null, getBannerUseCaseResponse())
    }

    fun getOnShow(): LiveData<Boolean> {
        return onShow
    }

    fun getOnError(): LiveData<String> {
        return onError
    }

    fun getMenuList(): LiveData<List<BannerItem>> {
        return bannerList
    }
}