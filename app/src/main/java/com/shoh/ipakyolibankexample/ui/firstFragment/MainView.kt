package com.shoh.ipakyolibankexample.ui.firstFragment

import com.shoh.ipakyolibankexample.base.BaseView
import com.shoh.ipakyolibankexample.model.Card
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.AddToEndSingleTagStrategy
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.AddToEnd
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

interface MainView : BaseView {

    @OneExecution
    fun showUserCards(list: List<Card>)

    @AddToEndSingle
    fun showBottomSheet()

    @AddToEndSingle
    fun setSelectedCard(card: Card)

    @AddToEndSingle
    fun setUzbekCardInfo(card: Card, balance: String)

    @AddToEndSingle
    fun setForeignCardInfo(card: Card, balance: String)

    @AddToEndSingle
    fun setIntoSellingOrBuyingCard(card: Card)

    @AddToEndSingle
    fun setIntoExchangingCard(card: Card)

    @AddToEndSingle
    fun hideBottomSheet()


}