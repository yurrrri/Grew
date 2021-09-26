package com.begreen.grew.src.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.begreen.grew.R
import com.begreen.grew.config.BaseActivity
import com.begreen.grew.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNavigationBar()
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