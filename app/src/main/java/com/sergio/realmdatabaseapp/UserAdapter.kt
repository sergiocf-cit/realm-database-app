package com.sergio.realmdatabaseapp

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class UserAdapter: RecyclerView.Adapter<UserViewHolder>() {
    var data = listOf<User>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

}