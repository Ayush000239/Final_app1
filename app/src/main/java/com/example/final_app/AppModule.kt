package com.example.final_app

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // ViewModel definitions
    viewModel { MainViewModel() }
    viewModel { DetailViewModel(get()) }


}