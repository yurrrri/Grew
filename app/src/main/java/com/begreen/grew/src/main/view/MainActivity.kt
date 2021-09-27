package com.begreen.grew.src.main.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.begreen.grew.R
import com.begreen.grew.config.ApplicationClass
import com.begreen.grew.config.BaseActivity
import com.begreen.grew.databinding.ActivityMainBinding
import com.begreen.grew.src.login.LoginActivity

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNavigationBar()

        binding.btnSearch.setOnClickListener {
            val editor = ApplicationClass.sSharedPreferences.edit()
            editor.remove(ApplicationClass.X_ACCESS_TOKEN)
            editor.apply()

            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
    }

    private fun initNavigationBar() {

        binding.tabBottom.itemIconTintList = null

        binding.tabBottom.run {
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.bottom_action -> {
                        changeFragment(ActionFragment())
                    }
                    R.id.bottom_home -> {
                        changeFragment(HomeFragment())
                    }
                    R.id.bottom_mypage -> {
                        changeFragment(MyPageFragment())
                    }
                }
                true
            }
            selectedItemId = R.id.bottom_home
        }
    }
    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager .beginTransaction() .replace(R.id.outerFrame, fragment) .commit()
    }
}