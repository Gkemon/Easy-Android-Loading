package com.gk.emon.lovelyLoading

import android.R
import android.util.Log
import java.util.*
import kotlin.math.roundToInt

/**
 * Created by Hemant chand on 05/07/17.
 */
object ColorTransparentUtils {
    // This default color int
    private const val defaultColorID = R.color.black
    private const val defaultColor = "000000"
    private const val TAG = "ColorTransparentUtils"

    /**
     * This method convert numver into hexa number or we can say transparent code
     *
     * @param trans number of transparency you want
     * @return it return hex decimal number or transparency code
     */
    private fun convert(trans: Int): String {
        val hexString = Integer.toHexString((255 * trans / 100.toFloat()).roundToInt())
        return (if (hexString.length < 2) "0" else "") + hexString
    }

    fun transparentColor(colorCode: Int, trans: Int): String {
        return convertIntoColor(colorCode, trans)
    }

    /**
     * Convert color code into transparent color code
     *
     * @param colorCode color code
     * @param transCode transparent number
     * @return transparent color code
     */
    private fun convertIntoColor(colorCode: Int, transCode: Int): String {
        // convert color code into hex string and remove starting 2 digit
        var color = defaultColor
        try {
            color = Integer.toHexString(colorCode).toUpperCase(Locale.US).substring(2)
        } catch (ignored: Exception) {
        }
        return if (!color.isEmpty() && transCode < 100) {
            if (color.trim { it <= ' ' }.length == 6) {
                "#" + convert(transCode) + color
            } else {
                Log.d(TAG, "Color is already with transparency")
                convert(transCode) + color
            }
        } else "#" + Integer.toHexString(defaultColorID).toUpperCase(Locale.US).substring(2)
        // if color is empty or any other problem occur then we return default color;
    }
}