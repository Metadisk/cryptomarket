package com.metadisk.cryptomarket.data.util

import android.widget.TextView
import androidx.core.content.ContextCompat
import com.metadisk.cryptomarket.R


object UIHelper {

    fun showChangePercent(textView: TextView, _change: Double?) {
        val changePercent = "%.2f %%".format(_change).trimParanthesis()

        textView.text = changePercent
        val context = textView.context
        if (changePercent.contains("-")) {
            textView.setTextColor(
                ContextCompat.getColor(context, R.color.red)
            )

        } else {
            textView.setTextColor(
                ContextCompat.getColor(context, R.color.green)
            )

        }
    }
}