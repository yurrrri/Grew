package com.begreen.grew.src.splash

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.WindowManager
import androidx.annotation.RequiresApi
import com.begreen.grew.config.BaseActivity
import com.begreen.grew.databinding.ActivitySplashBinding
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        if (intent.extras!=null){
//            postId = intent.extras!!.getInt("postId")
//            Log.d("postId", postId.toString())
//        }
        //postId = intent.extras.getInt("")

        // Hide the status bar.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        try {
            val info = packageManager.getPackageInfo(
                packageName, PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.e(
                    "MY KEY HASH:",
                    Base64.encodeToString(md.digest(), Base64.DEFAULT)
                )
            }
        } catch (e: PackageManager.NameNotFoundException) {
        } catch (e: NoSuchAlgorithmException) {
        }
    }
}