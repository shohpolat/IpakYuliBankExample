package com.shoh.ipakyolibankexample.ui.firstFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shoh.ipakyolibankexample.R
import com.shoh.ipakyolibankexample.constants.constants
import com.shoh.ipakyolibankexample.databinding.FirstFragmentCustomCardBinding
import com.shoh.ipakyolibankexample.model.Card
import com.shoh.ipakyolibankexample.util.NumberFormatterUtil
import javax.inject.Inject

class CardsAdapter(val list: List<Card>,val numberFormatterUtil: NumberFormatterUtil) : RecyclerView.Adapter<CardsAdapter.ViewHolder>() {

    private lateinit var listener: OnClickListener

    inner class ViewHolder(val binding: FirstFragmentCustomCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.cardview1ExpandImg.visibility = View.GONE
        }

        fun bind(index: Int) {
            val currentCard = list[index]
            binding.apply {

                var balance = numberFormatterUtil.formatNumber(currentCard.cardBalance)
                when (currentCard.cardType) {

                    constants.VISA_CARD -> {
                        binding.cardview1Logo.setImageResource(R.drawable.ic_visa)
                        balance += " USD"
                    }
                    constants.MASTER_CARD -> {
                        binding.cardview1Logo.setImageResource(R.drawable.ic_mastercard)
                        balance += " USD"

                    }
                    constants.UZKARD -> {
                        binding.cardview1Logo.setImageResource(R.drawable.uzkard)
                        balance += " UZS"

                    }
                    constants.HUMO -> {
                        binding.cardview1Logo.setImageResource(R.drawable.humo)
                        balance += " UZS"

                    }

                    constants.UNION_CARD -> {
                        binding.cardview1Logo.setImageResource(R.drawable.union)
                        balance += " UZS"

                    }
                }


                binding.cardview1Balance.text = balance
                binding.cardview1Name.text = currentCard.cardHolder
                binding.cardview1Number.text = numberFormatterUtil.hideFirst4(currentCard.cardNumber)

                root.setOnClickListener { listener.onClick(currentCard) }

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view =
            FirstFragmentCustomCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)

//        view.root.layoutParams.height =
//            (parent.resources.displayMetrics.heightPixels / 10)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = list.size


    interface OnClickListener {
        fun onClick(card: Card)
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.listener = onClickListener
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

}