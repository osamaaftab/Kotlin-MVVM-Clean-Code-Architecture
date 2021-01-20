package com.osamaaftab.dindinn.di.module

import com.osamaaftab.dindinn.presentation.viewmodel.BannerViewModel
import com.osamaaftab.dindinn.presentation.viewmodel.MenuViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ActivityModule = module {
    viewModel { MenuViewModel(get()) }
    viewModel { BannerViewModel(get()) }

}