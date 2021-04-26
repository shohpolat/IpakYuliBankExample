package com.shoh.ipakyolibankexample.ui.firstFragment

import com.shoh.ipakyolibankexample.base.BasePresenter
import com.shoh.ipakyolibankexample.constants.constants
import com.shoh.ipakyolibankexample.model.Card
import com.shoh.ipakyolibankexample.model.Currency
import moxy.InjectViewState

@InjectViewState
class MainPresenter : BasePresenter<MainView>() {

    private val uzbekCards = ArrayList<Card>()
    private val foreignCards = ArrayList<Card>()
    private val currencies = ArrayList<Currency>()

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

        addUzbekCards()
        addForeignCards()
        addCurrencies()
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
            viewState.showUserCards(foreignCards)
        } else {
            viewState.showUserCards(uzbekCards)
        }
    }

    fun getExchangingIntoCards() {
        clickedCard = 1
        if (isSelling) {
            viewState.showUserCards(uzbekCards)
        } else {
            viewState.showUserCards(foreignCards)
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

    private fun addCurrencies() {

        currencies.add(Currency(constants.USD, "10 528.59", "10 520.30", "10 456.67"))
        currencies.add(Currency(constants.JPY, "10 528.59", "10 520.30", "10 456.67"))
        currencies.add(Currency(constants.EUR, "10 528.59", "10 520.30", "10 456.67"))

        currencies.add(Currency(constants.USD, "10 528.59", "10 520.30", "10 456.67"))
        currencies.add(Currency(constants.JPY, "10 528.59", "10 520.30", "10 456.67"))
        currencies.add(Currency(constants.EUR, "10 528.59", "10 520.30", "10 456.67"))

        currencies.add(Currency(constants.USD, "10 528.59", "10 520.30", "10 456.67"))
        currencies.add(Currency(constants.JPY, "10 528.59", "10 520.30", "10 456.67"))
        currencies.add(Currency(constants.EUR, "10 528.59", "10 520.30", "10 456.67"))

        currencies.add(Currency(constants.USD, "10 528.59", "10 520.30", "10 456.67"))
        currencies.add(Currency(constants.JPY, "10 528.59", "10 520.30", "10 456.67"))
        currencies.add(Currency(constants.EUR, "10 528.59", "10 520.30", "10 456.67"))

    }

    private fun addForeignCards() {

        foreignCards.add(
            Card(
                "11",
                "Visa",
                "Abdusamatov S",
                constants.VISA_CARD,
                "43218765",
                100
            )
        )
        foreignCards.add(
            Card(
                "12",
                "Master",
                "Abdusamatov S",
                constants.MASTER_CARD,
                "87654321",
                200
            )
        )
        foreignCards.add(
            Card(
                "13",
                "Visa",
                "Olimjonov F",
                constants.VISA_CARD,
                "22223487",
                300
            )
        )

        foreignCards.add(
            Card(
                "14",
                "Union",
                "Abdurahmonov Sh",
                constants.UNION_CARD,
                "22223335",
                400
            )
        )

    }

    private fun addUzbekCards() {
        uzbekCards.add(
            Card(
                "14",
                "Humo",
                "Abdusamatov Shohrux",
                constants.HUMO,
                "12345678",
                1000000
            )
        )
        uzbekCards.add(
            Card(
                "1",
                "Uzkard",
                "Abdusamatov Shohruxbek",
                constants.UZKARD,
                "87654321",
                554
            )
        )
        uzbekCards.add(Card("1", "Humo", "Olimjonov Farrux", constants.HUMO, "12347586", 1200000))
        uzbekCards.add(Card("1", "Humo", "Olimjonov Farrux", constants.HUMO, "12347586", 1200000))
        uzbekCards.add(Card("1", "Humo", "Olimjonov Farrux", constants.HUMO, "12347586", 1200000))
        uzbekCards.add(Card("1", "Humo", "Olimjonov Farrux", constants.HUMO, "12347586", 1200000))
        uzbekCards.add(Card("1", "Humo", "Olimjonov Farrux", constants.HUMO, "12347586", 1200000))
        uzbekCards.add(Card("1", "Humo", "Olimjonov Farrux", constants.HUMO, "12347586", 1200000))
        uzbekCards.add(Card("1", "Humo", "Olimjonov Farrux", constants.HUMO, "12347586", 1200000))
        uzbekCards.add(Card("1", "Humo", "Olimjonov Farrux", constants.HUMO, "12347586", 1200000))
        uzbekCards.add(Card("1", "Humo", "Olimjonov Farrux", constants.HUMO, "12347586", 1200000))
        uzbekCards.add(Card("1", "Humo", "Olimjonov Farrux", constants.HUMO, "12347586", 1200000))
        uzbekCards.add(Card("1", "Humo", "Olimjonov Farrux", constants.HUMO, "12347586", 1200000))
        uzbekCards.add(
            Card(
                "1",
                "Uzkard",
                "Olimjonov Farruxbek",
                constants.UZKARD,
                "12348756",
                655
            )
        )

    }

    fun getCurrencies(): List<Currency> = currencies

}