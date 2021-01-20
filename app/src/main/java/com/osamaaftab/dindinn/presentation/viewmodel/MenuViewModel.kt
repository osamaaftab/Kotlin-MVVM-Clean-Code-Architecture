package com.osamaaftab.dindinn.presentation.viewmodel

import android.content.ContentValues
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.osamaaftab.dindinn.domain.model.ErrorModel
import com.osamaaftab.dindinn.domain.model.Menu
import com.osamaaftab.dindinn.domain.model.MenuItem
import com.osamaaftab.dindinn.domain.usecase.GetMenuUsecase
import com.osamaaftab.dindinn.domain.usecase.base.UseCaseResponse
import com.osamaaftab.dindinn.presentation.base.BaseViewModel


class MenuViewModel constructor(private val getMenuUsecase: GetMenuUsecase) : BaseViewModel() {
    private val onShow = MutableLiveData<Boolean>()
    private val onDestroyCallbacks = MutableLiveData<Boolean>()
    private val onNotifyCounter = MutableLiveData<Int>()
    private val onError = MutableLiveData<String>()
    private val menuList = MutableLiveData<List<MenuItem>>()
    private val tempList = ArrayList<MenuItem>()
    private val selectedList = MutableLiveData<List<MenuItem>>()

    private fun getMenuUseCaseResponse() = object : UseCaseResponse<Menu> {
        override fun onSuccess(value: Menu) {
            onShow.value = false
            Log.i(ContentValues.TAG, "result: $value")
            menuList.value = value.menuList

        }

        override fun onError(error: Throwable, errorModel: ErrorModel?) {
            Log.i(ContentValues.TAG, "error: $errorModel?.message code")
            onShow.value = false
            onError.value = errorModel?.getErrorMessage().toString()
        }
    }

    fun onDestoryCallBack(boolean: Boolean) {
        onDestroyCallbacks.value = boolean
    }

    fun getCallBack(): LiveData<Boolean> {
        return onDestroyCallbacks
    }

    fun fetchMenu(type: String?) {
        getMenuUsecase.execute(compositeDisposable, type, getMenuUseCaseResponse())
    }

    fun getOnShow(): LiveData<Boolean> {
        return onShow
    }

    fun getOnNotifyCounter(): LiveData<Int> {
        return onNotifyCounter
    }

    fun getOnError(): LiveData<String> {
        return onError
    }

    fun getSelectedList(): LiveData<List<MenuItem>> {
        return selectedList
    }

    fun onIncreaseCounter(view: View, price: String, menuItem: MenuItem) {
        onNotifyCounter.value = 1
        tempList.add(menuItem)
        selectedList.value = tempList
        val button = view as AppCompatButton
        button.text = "Added + 1"
        Handler().postDelayed({ button.text = "$price$" }, 1000)
    }

    fun getMenuList(): LiveData<List<MenuItem>> {
        return menuList
    }
}