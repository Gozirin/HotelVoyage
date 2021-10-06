package com.example.hbapplicationgroupa.adapter.ratingreviewadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.borjabravo.readmoretextview.ReadMoreTextView
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.models.model.RatingReviewModel
import com.mikhaellopez.circularimageview.CircularImageView

/**
 * RecyclerView Adapter for rating_reviewsRecyclerView
 */
class RatingReviewRecyclerViewAdapter() : RecyclerView.Adapter<RatingReviewRecyclerViewAdapter.RatingViewHolder>() {
    var reviewDataList : ArrayList<RatingReviewModel> = arrayListOf()
    class RatingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val raterImage: CircularImageView = view.findViewById(R.id.ratingReview_iv)
        private val raterName: TextView = view.findViewById(R.id.ratingReviewName_tv)
        private val ratingDate: TextView = view.findViewById(R.id.ratingReviewDate_tv)
        private val raterReview: ReadMoreTextView = view.findViewById(R.id.ratingReview_tv)
        private val starOne: ImageView = view.findViewById(R.id.ratingStarOne_iv)
        private val starTwo: ImageView = view.findViewById(R.id.ratingStarTwo_iv)
        private val starThree: ImageView = view.findViewById(R.id.ratingStarThree_iv)
        private val starFour: ImageView = view.findViewById(R.id.ratingStarFour_iv)
        private val starFive: ImageView = view.findViewById(R.id.ratingStarFive_iv)

        //This method binds RatingViewHolder properties with RatingReviewModel data
        fun bindReviewData(reviewData : RatingReviewModel){
            raterImage.setImageResource(reviewData.reviewImage)
            raterName.text = reviewData.reviewName
            ratingDate.text = reviewData.reviewDate
            raterReview.text = reviewData.reviewBody
            if (reviewData.starOne != null){
                starOne.setImageResource(reviewData.starOne)
            }
            if (reviewData.starTwo != null){
                starTwo.setImageResource(reviewData.starTwo)
            }
            if (reviewData.starThree != null){
                starThree.setImageResource(reviewData.starThree)
            }
            if (reviewData.starFour != null){
                starFour.setImageResource(reviewData.starFour)
            }
            if (reviewData.starFive != null){
                starFive.setImageResource(reviewData.starFive)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.rating_review_recyclerview_layout, parent, false)
        return RatingViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: RatingViewHolder, position: Int) {
        holder.bindReviewData(reviewDataList[position])
    }

    override fun getItemCount(): Int {
        return reviewDataList.size
    }
}