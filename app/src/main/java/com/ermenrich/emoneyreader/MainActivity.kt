package com.ermenrich.emoneyreader

import android.content.Intent
import android.nfc.NfcAdapter
import android.os.Bundle
import android.provider.Settings
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    // Define a constant for the NFC intent filter
    private val nfcIntentFilter = arrayOf(NfcAdapter.ACTION_NDEF_DISCOVERED)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check if NFC is enabled
        val nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        if (nfcAdapter == null) {
            // NFC is not supported on this device
            Toast.makeText(this, "NFC is not supported on this device", Toast.LENGTH_LONG).show()
            finish()
            return
        }
        if (!nfcAdapter.isEnabled) {
            // NFC is not enabled, prompt the user to enable it
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Enable NFC")
                .setMessage("This app requires NFC to be enabled")
                .setCancelable(false)
                .setPositiveButton("Enable") { _, _ ->
                    startActivity(Intent(Settings.ACTION_NFC_SETTINGS))
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                    finish()
                }
            val alert = builder.create()
            alert.show()
        } else {
            // NFC is enabled, continue with the app
            // Create a TextView and set its properties
            val textView = TextView(this)
            val tapNfcCardText = getString(R.string.tap_nfc_card)
            textView.text = tapNfcCardText
            textView.gravity = Gravity.CENTER
            textView.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_gray))
            textView.setTextColor(ContextCompat.getColor(this, R.color.white))
            textView.setPadding(16, 16, 16, 16)
            textView.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            // Create a rounded border using a drawable resource
            textView.background = AppCompatResources.getDrawable(this, R.drawable.rounded_border)

            // Set the margin of the TextView
            val marginLayoutParams = ViewGroup.MarginLayoutParams(textView.layoutParams)
            marginLayoutParams.setMargins(32, 32, 32, 32)
            textView.layoutParams = marginLayoutParams

            setContentView(textView)
        }
    }

    override fun onResume() {
        super.onResume()

    }


    override fun onPause() {
        super.onPause()

        // Disable NFC reading mode for the activity
        val nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        nfcAdapter?.disableForegroundDispatch(this)
    }
}
