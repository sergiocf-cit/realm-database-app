package com.sergio.realmdatabaseapp

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sergio.realmdatabaseapp.databinding.UserItemBinding

class UserViewHolder(private val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) {
        binding.user = user;

        binding.updateName.setOnClickListener {
            Log.i("UserViewHolder", Thread.currentThread().name)
            binding.user = User(user.id, binding.listNameText.text.toString())
        }
    }

    companion object {
        fun from(parent: ViewGroup): UserViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = UserItemBinding.inflate(layoutInflater, parent, false)

            return UserViewHolder(binding)
        }
    }
}