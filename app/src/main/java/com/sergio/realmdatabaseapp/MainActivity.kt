package com.sergio.realmdatabaseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.work.*
import com.sergio.realmdatabaseapp.databinding.ActivityMainBinding
import com.sergio.realmdatabaseapp.worker.LIST_ID
import com.sergio.realmdatabaseapp.worker.LogWorker
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

const val TAG = "MainActivity"

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
        initLogWorker()
        intCoroutine()
    }

    private fun initAddButton() {
        activityMainBinding.addButton.setOnClickListener {
            Log.i(TAG, Thread.currentThread().name)

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

    private fun initLogWorker() {
        val myData: Data = workDataOf(LIST_ID to data.map { it.id }.toIntArray())

        val workerRequest = OneTimeWorkRequestBuilder<LogWorker>()
            .setInputData(myData)
            .build()

        WorkManager
            .getInstance(this)
            .enqueue(workerRequest)

    }

    private fun intCoroutine() {
        GlobalScope.launch {
            for (i in 1..100) {
                Log.i(TAG, Thread.currentThread().name + " $data" )
                delay(500)
            }
        }
    }
}