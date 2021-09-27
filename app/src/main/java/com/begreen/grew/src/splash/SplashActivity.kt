package com.begreen.grew.src.splash

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.annotation.RequiresApi
import com.begreen.grew.src.main.view.MainActivity
import com.begreen.grew.config.ApplicationClass
import com.begreen.grew.config.BaseActivity
import com.begreen.grew.databinding.ActivitySplashBinding
import com.begreen.grew.src.login.LoginActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {

    val sf = ApplicationClass.sSharedPreferences

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hide the status bar.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        //카카오 토큰이 있으면 -> 메인화면으로
        if (sf.getString(ApplicationClass.kakaoToken, null)!=null){
            Handler(Looper.getMainLooper()).postDelayed({
                val toMain = Intent(this, MainActivity::class.java)
                startActivity(toMain)
                finish()
            }, 2000)
        }
        else{ //없으면 ->로그인화면으로
            Handler(Looper.getMainLooper()).postDelayed({
                val toMain = Intent(this, LoginActivity::class.java)
                startActivity(toMain)
                finish()
            }, 2000)
        }

        //debug Key Hash
//        try {
//            val info = packageManager.getPackageInfo(
//                packageName, PackageManager.GET_SIGNATURES
//            )
//            for (signature in info.signatures) {
//                val md: MessageDigest = MessageDigest.getInstance("SHA")
//                md.update(signature.toByteArray())
//                Log.e(
//                    "MY KEY HASH:",
//                    Base64.encodeToString(md.digest(), Base64.DEFAULT)
//                )
//            }
//        } catch (e: PackageManager.NameNotFoundException) {
//        } catch (e: NoSuchAlgorithmException) {
//        }
    }
}