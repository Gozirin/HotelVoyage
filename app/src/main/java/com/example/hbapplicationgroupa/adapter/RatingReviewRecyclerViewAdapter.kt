package com.example.hbapplicationgroupa.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.borjabravo.readmoretextview.ReadMoreTextView
import com.example.hbapplicationgroupa.R
import com.mikhaellopez.circularimageview.CircularImageView

class RatingReviewRecyclerViewAdapter() : RecyclerView.Adapter<RatingReviewRecyclerViewAdapter.RatingViewHolder>() {

    class RatingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val raterImage: CircularImageView = view.findViewById(R.id.ratingReview_iv)
        private val raterName: TextView = view.findViewById(R.id.ratingReviewName_tv)
        private val raterReview: ReadMoreTextView = view.findViewById(R.id.ratingReview_tv)
        private val starOne: ImageView = view.findViewById(R.id.ratingStarOne_iv)
        private val starTwo: ImageView = view.findViewById(R.id.ratingStarTwo_iv)
        private val starThree: ImageView = view.findViewById(R.id.ratingStarThree_iv)
        private val starFour: ImageView = view.findViewById(R.id.ratingStarFour_iv)
        private val starFive: ImageView = view.findViewById(R.id.ratingStarFive_iv)

        fun bindReviewData(){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.rating_review_recyclerview_layout, parent, false)
        return RatingViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: RatingViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}