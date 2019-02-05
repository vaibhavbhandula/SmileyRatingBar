package com.rating.smileyratingbar

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout

/**
 * @author Vaibhav Bhandula on 05/02/19.
 */
class SmileyRatingBar : LinearLayout, RatingClickListener {

    /**
     * Rating Enum
     */
    enum class Rating(private val rating: Int) {
        TERRIBLE(0),
        BAD(1),
        OKAY(2),
        GOOD(3),
        GREAT(4),
        NONE(-1);

        fun getRating(): Int = rating
    }

    private var ratingList: ArrayList<RatingEmoji> = ArrayList()
    private var oldRating: Rating = Rating.NONE

    private var ratingSelectListener: RatingSelectListener? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    /**
     * set Rating Listener
     * @param ratingSelectListener RatingSelectListener
     */
    fun setRatingSelectListener(ratingSelectListener: RatingSelectListener?) {
        this.ratingSelectListener = ratingSelectListener
    }

    /**
     * setName for the emoji
     * @param emoji Rating Emoji
     * @param text Name for the Emoji
     */
    fun setNameForEmoji(emoji: Rating, text: String?) {
        if (emoji == Rating.NONE || emoji.getRating() < 0 || emoji.getRating() >= ratingList.size) {
            return
        }
        ratingList[emoji.getRating()].setEmojiText(text)
    }

    /**
     * set Typeface for the font
     * @param typeface Typeface
     */
    fun setTypeface(typeface: Typeface?) {
        RatingEmoji.setTypeFace(typeface ?: Typeface.DEFAULT)
        init()
    }

    /**
     * get current rating value
     * @return Integer
     */
    fun getRating(): Int = oldRating.getRating() + 1

    private fun init() {
        orientation = LinearLayout.HORIZONTAL
        layoutDirection = if (Utils.isRTL()) View.LAYOUT_DIRECTION_RTL else View.LAYOUT_DIRECTION_LTR
        setUpSmileys()
    }

    private fun setUpSmileys() {
        removeAllViews()
        var ratingEmoji = RatingEmoji(context)
        ratingEmoji.setRatingEmoji(Rating.TERRIBLE, R.drawable.terrible_selected, R.drawable.terrible_unselected)
        ratingEmoji.setRatingSelectListener(this)
        ratingList.add(ratingEmoji)
        ratingEmoji = RatingEmoji(context)
        ratingEmoji.setRatingEmoji(Rating.BAD, R.drawable.bad_selected, R.drawable.bad_unselected)
        ratingEmoji.setRatingSelectListener(this)
        ratingList.add(ratingEmoji)
        ratingEmoji = RatingEmoji(context)
        ratingEmoji.setRatingEmoji(Rating.OKAY, R.drawable.ok_selected, R.drawable.ok_unselected)
        ratingEmoji.setRatingSelectListener(this)
        ratingList.add(ratingEmoji)
        ratingEmoji = RatingEmoji(context)
        ratingEmoji.setRatingEmoji(Rating.GOOD, R.drawable.good_selected, R.drawable.good_unselected)
        ratingEmoji.setRatingSelectListener(this)
        ratingList.add(ratingEmoji)
        ratingEmoji = RatingEmoji(context)
        ratingEmoji.setRatingEmoji(Rating.GREAT, R.drawable.great_selected, R.drawable.great_unselected)
        ratingEmoji.setRatingSelectListener(this)
        ratingList.add(ratingEmoji)
        ratingList.forEach { addView(it) }
    }

    override fun ratingClicked(rating: Rating) {
        if (oldRating != Rating.NONE) {
            ratingList[oldRating.getRating()].unSelectRatingEmoji()
        }
        ratingList[rating.getRating()].selectRatingEmoji()
        oldRating = rating
        ratingSelectListener?.ratingSelected(rating.getRating() + 1)
    }
}
