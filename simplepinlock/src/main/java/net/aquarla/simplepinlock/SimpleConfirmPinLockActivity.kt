package net.aquarla.simplepinlock

import android.os.Bundle
import android.widget.TextView

/*
 * Base class of activity for confirming PIN.
 */
abstract class SimpleConfirmPinLockActivity : SimplePinLockActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val messageTextView = findViewById<TextView>(R.id.messageTextView)
        messageTextView.text = getString(R.string.simplepinlock_request_pin)
    }

    override fun onPinInputFinished() {
        if (isPinCorrect(getPin())) {
            setResult(PIN_CONFIRMED)
            finish()
        } else {
            clearPin()
            val messageTextView = findViewById<TextView>(R.id.messageTextView)
            messageTextView.text = getString(R.string.simplepinlock_invalid_pin)
        }
    }

    /*
     * return true if pin is correct
     */
    abstract fun isPinCorrect(pin: String) : Boolean
}