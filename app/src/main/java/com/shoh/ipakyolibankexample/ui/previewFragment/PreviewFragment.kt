package com.shoh.ipakyolibankexample.ui.previewFragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.shoh.ipakyolibankexample.R
import com.shoh.ipakyolibankexample.application.AppApplication
import com.shoh.ipakyolibankexample.databinding.PreviewFragmentBinding
import com.shoh.ipakyolibankexample.model.Transfer
import com.shoh.ipakyolibankexample.ui.main.Main
import com.shoh.ipakyolibankexample.util.NumberFormatterUtil
import moxy.MvpAppCompatFragment
import javax.inject.Inject

class PreviewFragment : MvpAppCompatFragment(R.layout.preview_fragment), PreviewView {

    @Inject
    lateinit var numberFormatterUtil: NumberFormatterUtil

    private lateinit var binding: PreviewFragmentBinding

    val args by navArgs<PreviewFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = PreviewFragmentBinding.bind(view)

        (requireActivity().application as AppApplication).component.getFragmentComponentFactory()
            .create().inject(this)

        setLayout()
        setData(args.transfer)

        (requireActivity() as Main).binding.titleText.text = "Подтверждение"

    }

    private fun setLayout() {

        binding.totalAmountLayout.bottomLine.isVisible = false
        binding.totalAmountLayout.date.textSize = 25f
        binding.totalAmountLayout.date.setTextColor(resources.getColor(R.color.green, null))

        binding.dateLayout.dateText.text = getString(R.string.date)
        binding.sendingCardLayout.dateText.text = getString(R.string.sending_card)
        binding.receivingCardLayout.dateText.text = getString(R.string.receiving_card)
        binding.holderNameLayout.dateText.text = getString(R.string.holder_name)
        binding.foreignAmountLayout.dateText.text = getString(R.string.foreign_amount)
        binding.uzbekAmountLayout.dateText.text = getString(R.string.uzbek_amount)
        binding.serviceChargeLayout.dateText.text = getString(R.string.service_charge)
        binding.totalAmountLayout.dateText.text = getString(R.string.total)
    }

    override fun setData(transfer: Transfer) {

        binding.dateLayout.date.text = transfer.date
        binding.sendingCardLayout.date.text = numberFormatterUtil.hideMiddle4(transfer.sendingCard)
        binding.receivingCardLayout.date.text = numberFormatterUtil.hideMiddle4(transfer.receivingCard)
        binding.holderNameLayout.date.text = transfer.cardHolder
        val transferAmount = transfer.foreignAmount + " USD"
        val uzbekAmount = transfer.uzbekAmount + " UZS"
        val totalAmount = transfer.uzbekAmount + " UZS"
        binding.foreignAmountLayout.date.text = transferAmount
        binding.uzbekAmountLayout.date.text = uzbekAmount
        binding.serviceChargeLayout.date.text = transfer.serviceCharge
        binding.totalAmountLayout.date.text = totalAmount

    }


}