package com.sjianjun.coroutine.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sjianjun.app.R
import com.sjianjun.coroutine.dialog
import com.sjianjun.coroutine.launch
import kotlinx.coroutines.delay
import sjj.alog.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dialog(this) {
            setContentView(R.layout.activity_main)
            launch {
                try {
                    Log.e("delay before:" + Thread.currentThread())
                    delay(10000)
                    Log.e("delay after:" + Thread.currentThread())
                } catch (e: Exception) {
                    Log.e("error:" + Thread.currentThread(), e)
//                throw e
                }
            }
        }
    }
}