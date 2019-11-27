package io.github.sititou70.itarchadv2019.aidl_sample

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val TAG: String = this::class.java.simpleName
    private var mService: IHighAndLowService? = null
    private val mConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            mService = IHighAndLowService.Stub.asInterface(service)
            Log.d(TAG, "service connected")
        }

        override fun onServiceDisconnected(className: ComponentName) {
            mService = null
            Log.d(TAG, "service disconnected")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Intent(this, HighAndLowService::class.java).also { intent ->
            bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
        }
    }

    fun checkNumber(view: View) {
        Log.d(TAG, "check number called")

        var number: Int
        try {
            number = Integer.parseInt(findViewById<EditText>(R.id.NumberInput).text.toString())
        } catch (e: NumberFormatException) {
            return
        }

        val ret = mService?.check(number)
        if (ret === null) return

        val text_view = findViewById<TextView>(R.id.CheckResultView)
        val ret_string = ret.toString()
        if (ret_string == "EQUAL") {
            text_view.text = "" + number + "\nCORRECT number"
        } else {
            text_view.text = "your number is " + ret_string
        }
    }

    fun randomize(view: View) {
        Log.d(TAG, "randomize called")

        findViewById<TextView>(R.id.CheckResultView).text = ""
        mService?.randomize()
    }
}
