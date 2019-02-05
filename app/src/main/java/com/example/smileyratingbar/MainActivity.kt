package com.example.smileyratingbar

import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.rating.smileyratingbar.RatingSelectListener
import com.rating.smileyratingbar.SmileyRatingBar
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author Vaibhav Bhandula on 05/02/19.
 */
class MainActivity : AppCompatActivity(), RatingSelectListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        smiley_rating?.setRatingSelectListener(this)
        smiley_rating?.setNameForEmoji(SmileyRatingBar.Rating.GOOD, "good")
        smiley_rating?.setNameForEmoji(SmileyRatingBar.Rating.BAD, "bad")
        smiley_rating?.setTypeface(Typeface.SERIF)
        Log.v("current_rating", "" + smiley_rating?.getRating())
    }

    override fun ratingSelected(rating: Int) {
        Log.v("rating", "" + rating)
    }
}
