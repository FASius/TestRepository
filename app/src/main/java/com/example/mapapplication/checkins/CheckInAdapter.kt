package com.example.mapapplication.model

import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.mapapplication.R
import com.example.mapapplication.databinding.ItemCheckInBinding
import com.example.mapapplication.model.checkin.CheckIn

interface CheckInActionListener {

    fun onCheckInEdit(checkIn : CheckIn)

    fun onCheckInOpen(checkIn : CheckIn)

    fun onCheckInDelete(checkIn :CheckIn)

}

class CheckInAdapter(
    private val actionListener: CheckInActionListener
) : RecyclerView.Adapter<CheckInAdapter.CheckInViewHolder>(), OnClickListener {

    var checkInsList:List<CheckIn> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckInViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCheckInBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)
        binding.moreButton.setOnClickListener(this)

        return CheckInViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CheckInViewHolder, position: Int) {
        val checkIn = checkInsList[position]
        with(holder.binding){
            holder.itemView.tag = checkIn
            moreButton.tag = checkIn

            dateTextView.text = checkIn.date
            locationTextView.text = checkIn.locate
        }
    }

    override fun getItemCount(): Int = checkInsList.size

    class CheckInViewHolder(
        val binding: ItemCheckInBinding
        ) : RecyclerView.ViewHolder(binding.root)

    override fun onClick(view: View) {
        val checkIn = view.tag as CheckIn
        when (view.id){
            R.id.more_button -> {
                showPopupMenu(view)
            }
            else -> {
                actionListener.onCheckInOpen(checkIn)
            }
        }
    }

    private fun showPopupMenu(view: View){
        val menu = PopupMenu(view.context, view)
        val checkIn = view.tag as CheckIn

        menu.menu.add(0, ID_MENU_EDIT, Menu.NONE, view.context.getString(R.string.menu_edit))
        menu.menu.add(0, ID_MENU_DELETE, Menu.NONE, view.context.getString(R.string.menu_delete))

        menu.setOnMenuItemClickListener {
            when(it.itemId){
                ID_MENU_EDIT -> {
                    actionListener.onCheckInEdit(checkIn)
                }
                ID_MENU_DELETE -> {
                    actionListener.onCheckInDelete(checkIn)
                }
            }
            return@setOnMenuItemClickListener true
        }
        menu.show()
    }

    companion object{

        const val ID_MENU_EDIT = 1
        const val ID_MENU_DELETE = 2

    }
}

