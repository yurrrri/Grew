package com.begreen.grew.src.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.begreen.grew.config.BaseActivity
import com.begreen.grew.databinding.ActivityLoadingBinding
import com.begreen.grew.src.main.view.MainActivity

class LoadingActivity : BaseActivity<ActivityLoadingBinding>(ActivityLoadingBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            val toMain = Intent(this, MainActivity::class.java)
            startActivity(toMain)
            finish()
        }, 2000)
    }
}