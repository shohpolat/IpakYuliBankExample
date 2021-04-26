package com.shoh.ipakyolibankexample.di

import com.shoh.ipakyolibankexample.ui.firstFragment.FirstFragment
import com.shoh.ipakyolibankexample.ui.previewFragment.PreviewFragment
import dagger.Subcomponent

@Subcomponent
@FragmentScope
interface FragmentComponent {

    fun inject(firstfragment: FirstFragment)

    fun inject(previewFragment: PreviewFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): FragmentComponent
    }

}