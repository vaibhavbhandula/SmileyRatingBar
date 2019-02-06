package com.rating.smileyratingbar

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.support.v7.content.res.AppCompatResources
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView

/**
 * @author Vaibhav Bhandula on 05/02/19.
 */
internal class RatingEmoji : ConstraintLayout {

    private var tvText: TextView? = null
    private var ivEmoji: ImageView? = null
    private var selectedEmoji: Int? = null
    private var unSelectedEmoji: Int? = null
    private var rating: SmileyRatingBar.Rating = SmileyRatingBar.Rating.NONE
    private var ratingClickListener: RatingClickListener? = null

    private val unselectedImageSize: Int by lazy {
        context.resources.getDimension(R.dimen.unselected_size).toInt()
    }

    private val selectedImageSize: Int by lazy {
        context.resources.getDimension(R.dimen.selected_size).toInt()
    }

    private val blackColor: Int by lazy {
        ContextCompat.getColor(context, R.color.black)
    }

    private val lightColor: Int by lazy {
        ContextCompat.getColor(context, R.color.text_color_light)
    }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        if (isInEditMode) {
            return
        }
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        layoutInflater.inflate(R.layout.rating_emoji_layout, this, true)
        tvText = findViewById(R.id.tv_text)
        ivEmoji = findViewById(R.id.iv_emoji)
        tvText?.setTextColor(lightColor)
        tvText?.typeface = typeface
        val params: LayoutParams? = ivEmoji?.layoutParams as LayoutParams?
        params?.width = unselectedImageSize
        params?.height = unselectedImageSize
        ivEmoji?.layoutParams = params
        setOnClickListener { ratingSelected() }
    }

    internal fun setRatingEmoji(rating: SmileyRatingBar.Rating, selectedEmoji: Int, unSelectedEmoji: Int) {
        this.rating = rating
        this.selectedEmoji = selectedEmoji
        this.unSelectedEmoji = unSelectedEmoji
        ivEmoji?.setImageDrawable(getDrawable(unSelectedEmoji))
    }

    internal fun setEmojiText(text: String?) {
        tvText?.text = text ?: ""
    }

    internal fun changeTypeface() {
        tvText?.typeface = typeface
    }

    internal fun selectRatingEmoji() {
        tvText?.setTextColor(blackColor)
        ivEmoji?.setImageDrawable(getDrawable(selectedEmoji!!))
        val params: LayoutParams? = ivEmoji?.layoutParams as LayoutParams?
        params?.width = selectedImageSize
        params?.height = selectedImageSize
        ivEmoji?.layoutParams = params
    }

    internal fun unSelectRatingEmoji() {
        tvText?.setTextColor(lightColor)
        ivEmoji?.setImageDrawable(getDrawable(unSelectedEmoji!!))
        val params: LayoutParams? = ivEmoji?.layoutParams as LayoutParams?
        params?.width = unselectedImageSize
        params?.height = unselectedImageSize
        ivEmoji?.layoutParams = params
    }

    internal fun setRatingSelectListener(ratingClickListener: RatingClickListener) {
        this.ratingClickListener = ratingClickListener
    }

    private fun ratingSelected() {
        ratingClickListener?.ratingClicked(rating)
    }

    private fun getDrawable(resourceId: Int): Drawable? = AppCompatResources.getDrawable(context, resourceId)

    companion object {

        var typeface: Typeface = Typeface.DEFAULT

        fun setTypeFace(typeface: Typeface) {
            this.typeface = typeface
        }
    }
}
