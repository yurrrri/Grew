package com.begreen.grew.src.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.begreen.grew.R
import com.begreen.grew.config.ApplicationClass
import com.begreen.grew.config.BaseActivity
import com.begreen.grew.databinding.ActivityChooseActionBinding
import com.begreen.grew.src.main.view.HomeFragment
import com.begreen.grew.src.main.view.MainActivity

class ChooseActionActivity : BaseActivity<ActivityChooseActionBinding>(ActivityChooseActionBinding::inflate) {

    var actionList = arrayListOf<String>()
    private val isClickedList = Array(6){0}
    val editor = ApplicationClass.sSharedPreferences.edit()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.constraintlayout2.isEnabled = true
        binding.constraintlayout2.isClickable = true

        binding.btnBunri.setOnClickListener {
            if (isClickedList[0]==0) {
                isClickedList[0]=1
                binding.btnBunri.setBackgroundResource(R.drawable.ic_login_click)
                binding.tvBunri.setTextColor(resources.getColor(R.color.grew_green))
            }
            else{
                isClickedList[0]=0
                binding.btnBunri.setBackgroundResource(R.drawable.ic_login_unclick)
                binding.tvBunri.setTextColor(resources.getColor(R.color.gray))
            }
        }

        binding.btnCampaign.setOnClickListener {
            if (isClickedList[1]==0) {
                isClickedList[1]=1
                binding.btnCampaign.setBackgroundResource(R.drawable.ic_login_click)
                binding.tvCampaign.setTextColor(resources.getColor(R.color.grew_green))
            }
            else{
                isClickedList[1]=0
                binding.btnCampaign.setBackgroundResource(R.drawable.ic_login_unclick)
                binding.tvCampaign.setTextColor(resources.getColor(R.color.gray))
            }
        }

        binding.btnContest.setOnClickListener {
            if (isClickedList[2]==0) {
                isClickedList[2]=1
                binding.btnContest.setBackgroundResource(R.drawable.ic_login_click)
                binding.tvContest.setTextColor(resources.getColor(R.color.grew_green))
            }
            else{
                isClickedList[2]=0
                binding.btnContest.setBackgroundResource(R.drawable.ic_login_unclick)
                binding.tvContest.setTextColor(resources.getColor(R.color.gray))
            }
        }

        binding.btnEcoFriendly.setOnClickListener {
            if (isClickedList[3]==0) {
                isClickedList[3]=1
                binding.btnEcoFriendly.setBackgroundResource(R.drawable.ic_login_click)
                binding.tvEcoFriendly.setTextColor(resources.getColor(R.color.grew_green))
            }
            else{
                isClickedList[3]=0
                binding.btnEcoFriendly.setBackgroundResource(R.drawable.ic_login_unclick)
                binding.tvEcoFriendly.setTextColor(resources.getColor(R.color.gray))
            }
        }

        binding.btnAction.setOnClickListener {
            if (isClickedList[4]==0) {
                isClickedList[4]=1
                binding.btnAction.setBackgroundResource(R.drawable.ic_login_click)
                binding.tvAction.setTextColor(resources.getColor(R.color.grew_green))
            }
            else{
                isClickedList[4]=0
                binding.btnAction.setBackgroundResource(R.drawable.ic_login_unclick)
                binding.tvAction.setTextColor(resources.getColor(R.color.gray))
            }
        }

        binding.btnFlogging.setOnClickListener {
            if (isClickedList[5]==0) {
                isClickedList[5]=1
                binding.btnFlogging.setBackgroundResource(R.drawable.ic_login_click)
                binding.tvFlogging.setTextColor(resources.getColor(R.color.grew_green))
            }
            else{
                isClickedList[5]=0
                binding.btnFlogging.setBackgroundResource(R.drawable.ic_login_unclick)
                binding.tvFlogging.setTextColor(resources.getColor(R.color.gray))
            }
        }

        binding.btnActionNext.setOnClickListener {

//            editor.putString(ApplicationClass.kakaoToken, intent.getStringExtra("accessToken"))
//            editor.putString("name", intent.getStringExtra("name"))
//            editor.apply()

            val toAction = Intent(this, MainActivity::class.java)
            toAction.putExtra("accessToken", intent.getStringExtra("accessToken"))
            toAction.putStringArrayListExtra("category", intent.getStringArrayListExtra("category"))

            val bundle = Bundle()
            bundle.putStringArrayList("category", intent.getStringArrayListExtra("category"))
            val homeFragment = HomeFragment()
            homeFragment.arguments = bundle

            Log.d("kakao", intent.getStringArrayListExtra("category")?.size.toString())
            Log.d("kakao", intent.getStringExtra("accessToken").toString())

            editor.putString(ApplicationClass.kakaoToken, intent.getStringExtra("accessToken"))
            editor.apply()

            startActivity(toAction)
            finish()
        }
    }
}