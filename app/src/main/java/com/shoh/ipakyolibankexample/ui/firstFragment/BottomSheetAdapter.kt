package com.shoh.ipakyolibankexample.ui.firstFragment

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shoh.ipakyolibankexample.R
import com.shoh.ipakyolibankexample.constants.constants
import com.shoh.ipakyolibankexample.databinding.FirstFragmentBottomSheetSingleBinding
import com.shoh.ipakyolibankexample.model.Currency
import java.util.*

class BottomSheetAdapter(val list: List<Currency>) :
    RecyclerView.Adapter<BottomSheetAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: FirstFragmentBottomSheetSingleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(index: Int) {
            val currentItem = list[index]
            binding.apply {
                if (index % 2 != 0) {
                    root.setBackgroundColor(Color.WHITE)
                }

                currency.text = currentItem.name
                courseSb.text = currentItem.sb
                buying.text = currentItem.buy
                selling.text = currentItem.sell

                when (currentItem.name) {
                    constants.USD -> {
                        currencyLogo.setImageResource(R.drawable.ic_dollar)
                    }
                    constants.EUR -> {
                        currencyLogo.setImageResource(R.drawable.ic_euro)
                    }
                    constants.JPY -> {
                        currencyLogo.setImageResource(R.drawable.ic_yen)
                    }
                }

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = FirstFragmentBottomSheetSingleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        view.root.layoutParams.height = parent.resources.displayMetrics.heightPixels / 12
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = list.size

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

}