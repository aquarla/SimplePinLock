package net.aquarla.simplepinlock

import android.app.Application
import android.content.Context
import android.graphics.Point
import android.os.Build
import android.view.*
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.LinearLayout

/**
 * Adapter class for PinButton GridView
 */
class PinButtonAdapter(val context : Context) : BaseAdapter(), Util {
    val list = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "-2", "0", "-1")

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return Integer.parseInt(list[position]).toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View?
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        view = inflater.inflate(R.layout.pin_button, parent, false)

        if (view != null) {
            val screenWidth = getRealSize().x.toFloat()
            val screenWidthDp = px2dp(context, screenWidth)
            var buttonSize = dp2px(context,
                    if (screenWidthDp >= 390) 80.0f else (screenWidthDp - 150) / 3.0f
            ).toInt()

            val text = getItem(position) as String
            val button = view.findViewById<Button>(R.id.button)
            if (text == "-2") {
                button.visibility = View.INVISIBLE
                button.isEnabled = false
                button.setOnClickListener {
                    false
                }
            } else if (text == "-1") {
                // 戻る
                button.layoutParams = LinearLayout.LayoutParams(buttonSize, buttonSize)
                button.text = "DELETE"
                button.textSize = 16.0f
                button.setBackgroundResource(android.R.color.transparent)
                button.setPadding(0,0,0,0)
                button.setOnClickListener {
                    (context as SimplePinLockActivity).onDeleteButtonClicked()
                }
            } else {
                button.layoutParams = LinearLayout.LayoutParams(buttonSize, buttonSize)
                button.text = text
                button.setOnClickListener {
                    (context as SimplePinLockActivity).onPinButtonClicked(text)
                }
            }
        }

        return view!!
    }

    /*
     * Get real size of device (as pixel)
     */
    fun getRealSize(): Point {
        val windowManager = context.getSystemService(Application.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val point = Point(0, 0)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            // Android 4.2~
            display.getRealSize(point)
            return point

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            // Android 3.2~
            try {
                String::class.java
                val getRawWidth = Display::class.java.getMethod("getRawWidth")
                val getRawHeight = Display::class.java.getMethod("getRawHeight")
                val width = getRawWidth.invoke(display) as Int
                val height = getRawHeight.invoke(display) as Int
                point.set(width, height)
                return point

            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

        return point
    }
}