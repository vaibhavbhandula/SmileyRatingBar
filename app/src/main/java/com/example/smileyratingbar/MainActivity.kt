package com.example.smileyratingbar

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.vaibhav.ratingbar.RatingSelectListener
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author Vaibhav Bhandula on 05/02/19.
 */
class MainActivity : AppCompatActivity(), RatingSelectListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        smiley_rating?.setRatingSelectListener(this)
    }

    override fun ratingSelected(rating: Int) {
        Log.v("rating", "" + rating)
    }

}
