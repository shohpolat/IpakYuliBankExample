package com.shoh.ipakyolibankexample.ui.previewFragment

import com.shoh.ipakyolibankexample.base.BaseView
import com.shoh.ipakyolibankexample.model.Transfer
import moxy.viewstate.strategy.alias.AddToEndSingle

interface PreviewView : BaseView {

    @AddToEndSingle
    fun setData(transfer: Transfer)

}