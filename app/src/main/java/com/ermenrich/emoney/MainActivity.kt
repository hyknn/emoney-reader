package com.ermenrich.emoney

import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create a TextView for the message
        val textView = TextView(this)
        textView.text = "Tap your NFC card"
        textView.setTextColor(ContextCompat.getColor(this, android.R.color.white))
        textView.textSize = 24f
        textView.gravity = Gravity.CENTER

        // Set background color, margin, and rounded border
        val backgroundColor = ContextCompat.getColor(this, android.R.color.black)
        val margin = resources.getDimensionPixelSize(R.dimen.margin)
        val cornerRadius = resources.getDimensionPixelSize(R.dimen.corner_radius).toFloat()
        val layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        layoutParams.setMargins(margin, margin, margin, margin)
        textView.layoutParams = layoutParams
        textView.setBackgroundColor(backgroundColor)
        textView.background = ContextCompat.getDrawable(this, R.drawable.rounded_border)
        textView.setPadding(margin, margin, margin, margin)
        textView.clipToOutline = true

        // Set the TextView as the content view of the activity
        setContentView(textView)
    }
}
