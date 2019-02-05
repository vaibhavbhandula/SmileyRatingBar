package com.rating.smileyratingbar

import android.text.TextUtils
import android.view.View
import java.util.*

/**
 * @author Vaibhav Bhandula on 05/02/19.
 */
internal object Utils {

    /**
     * to check if the current layout direction is LTR or RTL
     *
     * @return boolean
     */
    internal fun isRTL(): Boolean = TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == View.LAYOUT_DIRECTION_RTL
}
