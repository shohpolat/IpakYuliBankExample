package com.shoh.ipakyolibankexample.application

import android.app.Application
import com.shoh.ipakyolibankexample.di.AppComponent
import com.shoh.ipakyolibankexample.di.DaggerAppComponent

class AppApplication : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.factory().create()

    }

}