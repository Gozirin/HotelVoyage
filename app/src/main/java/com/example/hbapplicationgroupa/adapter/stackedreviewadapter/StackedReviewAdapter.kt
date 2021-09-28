package com.example.hbapplicationgroupa.adapter.stackedreviewadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.model.StackedReviewModel
import com.mikhaellopez.circularimageview.CircularImageView

/**
 * This is the adapter for the id: hotelDescOverlapRv RecyclerView in HotelDescription Fragment
 */
class StackedReviewAdapter : RecyclerView.Adapter<StackedReviewAdapter.StackedReviewViewHolder>(){
    var stackedImageList : ArrayList<StackedReviewModel> = arrayListOf()
    class StackedReviewViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val img : CircularImageView = view.findViewById(R.id.stackedReviewIv)

        fun bindData(image: StackedReviewModel){
            img.setImageResource(image.img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StackedReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.hotel_desc_stacked_review_recyclerview, parent, false)
        return StackedReviewViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: StackedReviewViewHolder, position: Int) {
        holder.bindData(stackedImageList[position])
    }

    override fun getItemCount(): Int {
        return stackedImageList.size
    }
}