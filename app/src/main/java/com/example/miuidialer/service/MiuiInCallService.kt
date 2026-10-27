package com.example.miuidialer.service

import android.telecom.Call
import android.telecom.InCallService
import android.content.Intent
import com.example.miuidialer.ui.call.InCallActivity

class MiuiInCallService : InCallService() {

    companion object {
        var currentCall: Call? = null
            private set
    }

    override fun onCallAdded(call: Call) {
        super.onCallAdded(call)
        currentCall = call
        
        val intent = Intent(this, InCallActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
    }

    override fun onCallRemoved(call: Call) {
        super.onCallRemoved(call)
        if (currentCall == call) {
            currentCall = null
        }
    }
}
