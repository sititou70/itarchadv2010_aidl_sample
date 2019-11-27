package io.github.sititou70.itarchadv2019.aidl_sample

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlin.random.Random

class HighAndLowService : Service() {
    private val TAG: String = this::class.java.simpleName
    private var answer: Int = 0

    private fun _randomize() {
        this.answer = Random.nextInt(1, 100)
    }

    private val binder = object : IHighAndLowService.Stub() {
        override fun check(
            number: Int
        ): String {
            if (number > answer) return "HIGH"
            if (number < answer) return "LOW"
            return "EQUAL"
        }

        override fun randomize() {
            _randomize()
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.d(TAG, "onBind")
        _randomize()
        return binder
    }
}
