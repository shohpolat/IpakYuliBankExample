package com.shoh.ipakyolibankexample.ui.firstFragment

import com.shoh.ipakyolibankexample.base.BasePresenter
import com.shoh.ipakyolibankexample.constants.constants
import com.shoh.ipakyolibankexample.model.Card
import com.shoh.ipakyolibankexample.model.Currency
import com.shoh.ipakyolibankexample.repository.Repository
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class MainPresenter : BasePresenter<MainView>() {


    @Inject
    lateinit var repository: Repository

    private var exchanging_from_card =
        Card("10", "Visa", "cardholder name 1", constants.VISA_CARD, "12345567", 426)

    private var exchanging_to_card =
        Card("0", "Uzkard", "cardholder name 2", constants.UZKARD, "12346681", 95000000)

    var isSelling = true
    private var clickedCard = -1

    fun getSellingOrBuyingCard() = exchanging_from_card
    fun getExchangingCard() = exchanging_to_card

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()


        viewState.setIntoSellingOrBuyingCard(exchanging_from_card)
        viewState.setIntoExchangingCard(exchanging_to_card)

    }

    fun replace_cards() {

        if (isSelling) {

            isSelling = false
            val temp = exchanging_from_card
            exchanging_from_card = exchanging_to_card
            viewState.setSelectedCard(exchanging_to_card)
            exchanging_to_card = temp
            viewState.setIntoSellingOrBuyingCard(exchanging_from_card)
            viewState.setIntoExchangingCard(exchanging_to_card)

        } else {

            isSelling = true
            val temp = exchanging_from_card
            exchanging_from_card = exchanging_to_card
            viewState.setSelectedCard(exchanging_to_card)
            exchanging_to_card = temp
            viewState.setIntoSellingOrBuyingCard(exchanging_from_card)
            viewState.setIntoExchangingCard(exchanging_to_card)

        }
    }

    fun getSellingOrBuyingCards() {
        clickedCard = 0
        if (isSelling) {
            viewState.showUserCards(repository.getForeignCards())
        } else {
            viewState.showUserCards(repository.getUzbekCards())
        }
    }

    fun getExchangingIntoCards() {
        clickedCard = 1
        if (isSelling) {
            viewState.showUserCards(repository.getUzbekCards())
        } else {
            viewState.showUserCards(repository.getForeignCards())
        }
    }

    fun OnCardSelected(card: Card) {
        when (clickedCard) {
            0 -> {
                exchanging_from_card = card
                viewState.setIntoSellingOrBuyingCard(card)
            }
            1 -> {
                exchanging_to_card = card
                viewState.setIntoExchangingCard(card)
            }
        }
    }

    fun getCurrencies(): List<Currency> = repository.getCurrencies()

}