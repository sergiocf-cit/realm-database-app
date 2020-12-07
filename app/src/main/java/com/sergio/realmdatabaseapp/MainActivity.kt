package com.sergio.realmdatabaseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.sergio.realmdatabaseapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding;
    private lateinit var adapter: UserAdapter;
    private var nextId = 1

    private val data = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding =  DataBindingUtil.setContentView(this, R.layout.activity_main)
        initAdapter()
        initAddButton()

    }

    private fun initAddButton() {
        activityMainBinding.addButton.setOnClickListener {
            Log.i("MainActivity", Thread.currentThread().name)

            data.add(User(nextId++, activityMainBinding.addNameText.text.toString()))
            activityMainBinding.addNameText.text.clear()
            activityMainBinding.addNameText.clearFocus()
            adapter.data = data
        }
    }

    private fun initAdapter() {
        adapter = UserAdapter()
        activityMainBinding.userList.adapter = adapter
    }
}