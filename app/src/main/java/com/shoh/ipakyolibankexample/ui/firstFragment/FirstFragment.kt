package com.shoh.ipakyolibankexample.ui.firstFragment

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.text.InputFilter
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.shoh.ipakyolibankexample.R
import com.shoh.ipakyolibankexample.application.AppApplication
import com.shoh.ipakyolibankexample.constants.constants
import com.shoh.ipakyolibankexample.databinding.FirstFragmentCardsDialogBinding
import com.shoh.ipakyolibankexample.databinding.FirstFragmentBinding
import com.shoh.ipakyolibankexample.model.Card
import com.shoh.ipakyolibankexample.model.Transfer
import com.shoh.ipakyolibankexample.ui.main.Main
import com.shoh.ipakyolibankexample.util.NumberFormatterUtil
import com.shoh.ipakyolibankexample.util.Spacer
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class FirstFragment : MvpAppCompatFragment(R.layout.first_fragment), MainView {

    private lateinit var binding: FirstFragmentBinding

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>

    var isBottomSheetOpen = false

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @Inject
    lateinit var numberFormatterUtil: NumberFormatterUtil

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FirstFragmentBinding.bind(view)

        (requireActivity().application as AppApplication).component.getFragmentComponentFactory()
            .create().inject(this)


        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheetInclude.bottomSheet)
        bottomSheetBehavior.isHideable = true
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        (requireActivity() as Main).binding.titleText.text = getString(R.string.conversion)

        binding.include.cardview1.setOnClickListener {
            presenter.getSellingOrBuyingCards()
        }

        binding.include.cardview2.setOnClickListener {
            presenter.getExchangingIntoCards()
        }

        setHasOptionsMenu(true)

        binding.include.exchangeCardview.setOnClickListener {

            presenter.replace_cards()
            if (!presenter.isSelling) {
                binding.include.cardview1Title.text = getString(R.string.buy_text)
            } else {
                binding.include.cardview1Title.text = getString(R.string.convert_from_text)
            }

        }

        binding.bottomSheetInclude.removeImg.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        }

        binding.include.convertButton.setOnClickListener {

            val sellingOrBuyingCard = presenter.getSellingOrBuyingCard()
            val exchangingCard = presenter.getExchangingCard()
            val transfer = Transfer(
                numberFormatterUtil.currentDate(constants.date_format_full),
                sellingOrBuyingCard.cardNumber,
                exchangingCard.cardNumber,
                sellingOrBuyingCard.cardHolder,
                binding.include.editText.text.toString(),
                binding.include.convertedAmount.text.toString(),
                "0"
            )

            val action = FirstFragmentDirections.actionMainActivityToPreviewFragment(transfer)
            findNavController().navigate(action)

        }


        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {

                when (newState) {

                    BottomSheetBehavior.STATE_HIDDEN -> {
                        binding.bottomSheetInclude.root.setBackgroundColor(Color.TRANSPARENT)
                        makeCardsClickable()
                        isBottomSheetOpen = false
                    }

                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        binding.bottomSheetInclude.root.setBackgroundColor(
                            resources.getColor(
                                R.color.black_30,
                                null
                            )
                        )

                        makeCardsUnClickable()
                        isBottomSheetOpen = true
                    }

                    BottomSheetBehavior.STATE_DRAGGING -> {
                        makeCardsUnClickable()


                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        makeCardsUnClickable()

                        isBottomSheetOpen = true

                    }
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                        makeCardsUnClickable()

                        isBottomSheetOpen = true
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                        makeCardsUnClickable()
                        isBottomSheetOpen = true

                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

        })


        binding.include.editText.doOnTextChanged { text, start, before, count ->

            if (text!!.contains(".") && !text.endsWith(".")) {
                enableExchangeButton()
                val arr = text.split(".")
                val total = numberFormatterUtil.formatNumber(
                    (text.toString().toDouble() * constants.currency.toInt())
                )
                binding.include.convertedAmount.text = total

                if (arr[1].length == 2) {
                    binding.include.editText.filters = arrayOf(InputFilter.LengthFilter(3))
                }
                if (arr[1].length == 1) {
                    binding.include.editText.filters = arrayOf(InputFilter.LengthFilter(7))
                }

            } else {

                binding.include.editText.filters = arrayOf(InputFilter.LengthFilter(7))

                if (text.isNotBlank() && text.isNotEmpty() && !text.endsWith(".")) {
                    enableExchangeButton()
                    val convertedAmount = numberFormatterUtil.formatNumber(
                        (text.toString().toDouble() * constants.currency.toInt()).toInt()
                    )
                    binding.include.convertedAmount.text = convertedAmount.toString()

                } else if (text.isEmpty() || text.isBlank()) {
                    binding.include.convertedAmount.text = 0.toString()
                    disableExchangeButton()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.courses -> {

                showBottomSheet()

            }

        }
        return true
    }

    private fun enableExchangeButton() {
        binding.include.convertButton.isEnabled = true
    }

    private fun disableExchangeButton() {
        binding.include.convertButton.isEnabled = false
    }

    private fun makeCardsClickable() {
        binding.include.cardview1.isClickable = true
        binding.include.cardview2.isClickable = true
    }

    private fun makeCardsUnClickable() {
        binding.include.cardview1.isClickable = false
        binding.include.cardview2.isClickable = false
    }

    override fun showUserCards(list: List<Card>) {

        println("dialog showing")

        val dialog = Dialog(requireContext())
        val dialogbinding = FirstFragmentCardsDialogBinding.inflate(layoutInflater)
        dialog.setContentView(dialogbinding.root)


        val width = resources.displayMetrics.widthPixels * 0.95
        val height = resources.displayMetrics.heightPixels * 0.85

        dialog.window!!.setBackgroundDrawableResource(R.drawable.round_corner_2)
        dialog.window!!.setLayout(width.toInt(), height.toInt())

        dialogbinding.root.setOnClickListener {
            dialog.dismiss()
        }

        dialogbinding.recyclerview.apply {
            adapter = CardsAdapter(list, numberFormatterUtil).apply {
                setOnClickListener(object : CardsAdapter.OnClickListener {
                    override fun onClick(card: Card) {

                        presenter.OnCardSelected(card)
                        dialog.dismiss()

                    }

                })
            }
            setHasFixedSize(true)
        }

        dialog.create()
        dialog.show()

    }

    override fun showBottomSheet() {

        binding.bottomSheetInclude.recyclerview.apply {
            adapter = BottomSheetAdapter(presenter.getCurrencies())
            setHasFixedSize(true)
        }

        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

    }

    override fun setSelectedCard(card: Card) {

        var balance = numberFormatterUtil.formatNumber(card.cardBalance)

        when (card.cardType) {

            constants.UZKARD -> {
                (binding.include.cardview2.findViewById<ImageView>(R.id.cardview_2_logo)).setImageResource(
                    R.drawable.uzkard
                )
                balance += " UZS"
                setUzbekCardInfo(card, balance)
            }
            constants.HUMO -> {
                (binding.include.cardview2.findViewById<ImageView>(R.id.cardview_2_logo)).setImageResource(
                    R.drawable.humo
                )
                balance += " UZS"
                setUzbekCardInfo(card, balance)

            }


            constants.VISA_CARD -> {
                (binding.include.cardview1.findViewById<ImageView>(R.id.cardview_1_logo)).setImageResource(
                    R.drawable.ic_visa
                )
                balance += " UZS"
                setForeignCardInfo(card, balance)
            }
            constants.MASTER_CARD -> {
                (binding.include.cardview1.findViewById<ImageView>(R.id.cardview_1_logo)).setImageResource(
                    R.drawable.ic_mastercard
                )
                balance += " UZS"
                setForeignCardInfo(card, balance)

            }

            constants.UNION_CARD -> {
                (binding.include.cardview1.findViewById<ImageView>(R.id.cardview_1_logo)).setImageResource(
                    R.drawable.union
                )
                balance += " UZS"
                setForeignCardInfo(card, balance)

            }


        }


    }

    override fun setUzbekCardInfo(card: Card, balance: String) {
        binding.include.cardview2.findViewById<TextView>(R.id.cardview_2_name).text =
            card.cardHolder
        binding.include.cardview2.findViewById<TextView>(R.id.cardview_2_balance).text =
            balance
        binding.include.cardview2.findViewById<TextView>(R.id.cardview_2_number).text =
            numberFormatterUtil.hideFirst4(card.cardNumber)
    }

    override fun setIntoExchangingCard(card: Card) {

        var balance = numberFormatterUtil.formatNumber(card.cardBalance)

        when (card.cardType) {

            constants.UZKARD -> {
                (binding.include.cardview2.findViewById<ImageView>(R.id.cardview_2_logo)).setImageResource(
                    R.drawable.uzkard
                )
                balance += " UZS"
            }
            constants.HUMO -> {
                (binding.include.cardview2.findViewById<ImageView>(R.id.cardview_2_logo)).setImageResource(
                    R.drawable.humo
                )
                balance += " UZS"

            }
            constants.VISA_CARD -> {
                (binding.include.cardview2.findViewById<ImageView>(R.id.cardview_2_logo)).setImageResource(
                    R.drawable.ic_visa
                )
                balance += " UZS"
            }
            constants.MASTER_CARD -> {
                (binding.include.cardview2.findViewById<ImageView>(R.id.cardview_2_logo)).setImageResource(
                    R.drawable.ic_mastercard
                )
                balance += " UZS"

            }

            constants.UNION_CARD -> {
                (binding.include.cardview2.findViewById<ImageView>(R.id.cardview_2_logo)).setImageResource(
                    R.drawable.union
                )
                balance += " UZS"

            }
        }

        binding.include.cardview2.findViewById<TextView>(R.id.cardview_2_name).text =
            card.cardHolder
        binding.include.cardview2.findViewById<TextView>(R.id.cardview_2_balance).text =
            balance
        binding.include.cardview2.findViewById<TextView>(R.id.cardview_2_number).text =
            numberFormatterUtil.hideFirst4(card.cardNumber)

    }

    override fun setIntoSellingOrBuyingCard(card: Card) {

        var balance = numberFormatterUtil.formatNumber(card.cardBalance)

        when (card.cardType) {

            constants.UZKARD -> {
                (binding.include.cardview1.findViewById<ImageView>(R.id.cardview_1_logo)).setImageResource(
                    R.drawable.uzkard
                )
                balance += " UZS"
            }
            constants.HUMO -> {
                (binding.include.cardview1.findViewById<ImageView>(R.id.cardview_1_logo)).setImageResource(
                    R.drawable.humo
                )
                balance += " UZS"

            }
            constants.VISA_CARD -> {
                (binding.include.cardview1.findViewById<ImageView>(R.id.cardview_1_logo)).setImageResource(
                    R.drawable.ic_visa
                )
                balance += " UZS"
            }
            constants.MASTER_CARD -> {
                (binding.include.cardview1.findViewById<ImageView>(R.id.cardview_1_logo)).setImageResource(
                    R.drawable.ic_mastercard
                )
                balance += " UZS"

            }

            constants.UNION_CARD -> {
                (binding.include.cardview1.findViewById<ImageView>(R.id.cardview_1_logo)).setImageResource(
                    R.drawable.union
                )
                balance += " UZS"

            }
        }

        binding.include.cardview1.findViewById<TextView>(R.id.cardview_1_name).text =
            card.cardHolder
        binding.include.cardview1.findViewById<TextView>(R.id.cardview_1_balance).text =
            balance
        binding.include.cardview1.findViewById<TextView>(R.id.cardview_1_number).text =
            numberFormatterUtil.hideFirst4(card.cardNumber)

    }

    override fun setForeignCardInfo(card: Card, balance: String) {
        binding.include.cardview1.findViewById<TextView>(R.id.cardview_1_name).text =
            card.cardHolder
        binding.include.cardview1.findViewById<TextView>(R.id.cardview_1_balance).text =
            balance
        binding.include.cardview1.findViewById<TextView>(R.id.cardview_1_number).text =
            numberFormatterUtil.hideFirst4(card.cardNumber)
    }

    override fun hideBottomSheet() {

        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

    }

}