package com.example.mapapplication.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mapapplication.R
import com.example.mapapplication.databinding.ItemCheckinBinding
import com.example.mapapplication.model.checkin.CheckIn

class CheckInAdapter : RecyclerView.Adapter<CheckInAdapter.CheckInViewHolder>() {

    var checkInsList:List<CheckIn> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckInViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCheckinBinding.inflate(inflater, parent, false)
        return CheckInViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CheckInViewHolder, position: Int) {
        val checkIn = checkInsList[position]
        with(holder.binding){
            dateTextView.text = checkIn.date
            locationTextView.text = checkIn.locate
        }
    }

    override fun getItemCount(): Int = checkInsList.size


    class CheckInViewHolder(
        val binding: ItemCheckinBinding
        ) : RecyclerView.ViewHolder(binding.root)
}

