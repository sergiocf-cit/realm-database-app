package com.sergio.realmdatabaseapp.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import java.util.*

const val LIST_ID = "LIST_ID"
const val TAG = "LogWorker"

class LogWorker(context: Context, parameters: WorkerParameters) :
    CoroutineWorker(context, parameters) {

    override suspend fun doWork(): Result {
        for (i in 1..100) {
            Log.i(TAG, Thread.currentThread().name + " ${Arrays.toString( inputData.getIntArray(LIST_ID))}" )

            Thread.sleep(500)
        }

        return Result.success()
    }
}