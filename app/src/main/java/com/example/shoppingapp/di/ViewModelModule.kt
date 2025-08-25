package com.example.shoppingapp.di

import androidx.lifecycle.ViewModel
import com.example.shoppingapp.ui.viewmodel.MainViewModel
import dagger.Binds
import dagger.BindsInstance
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @ClassKey(MainViewModel::class)
    @IntoMap
    abstract fun provideMainViewModel(@BindsInstance mainViewModel: MainViewModel): ViewModel

}