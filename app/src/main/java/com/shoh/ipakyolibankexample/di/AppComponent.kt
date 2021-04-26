package com.shoh.ipakyolibankexample.di

import dagger.Component
import javax.inject.Singleton

@Component
@Singleton
interface AppComponent {

    fun getFragmentComponentFactory(): FragmentComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(): AppComponent
    }

}