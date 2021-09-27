package com.begreen.grew.src.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.begreen.grew.R
import com.begreen.grew.config.BaseActivity
import com.begreen.grew.databinding.ActivityChooseProblemBinding

class ChooseProblemActivity : BaseActivity<ActivityChooseProblemBinding>(ActivityChooseProblemBinding::inflate) {

    var categoryList = arrayListOf<String>()
    private val isClickedList = Array(5){0}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.constraintlayout1.isEnabled = true
        binding.constraintlayout1.isClickable = true

        binding.btnTrash.setOnClickListener {
            if (isClickedList[0]==0) {
                isClickedList[0]=1
                binding.btnTrash.setBackgroundResource(R.drawable.ic_login_click)
                binding.tvTrash.setTextColor(resources.getColor(R.color.grew_green))
                categoryList.add("쓰레기 처리")
            }
            else{
                isClickedList[0]=0
                binding.btnTrash.setBackgroundResource(R.drawable.ic_login_unclick)
                binding.tvTrash.setTextColor(resources.getColor(R.color.gray))
                categoryList.remove("쓰레기 처리")
            }
        }

        binding.btnAirPolution.setOnClickListener {
            if (isClickedList[1]==0) {
                isClickedList[1]=1
                binding.btnAirPolution.setBackgroundResource(R.drawable.ic_login_click)
                binding.tvAirPolution.setTextColor(resources.getColor(R.color.grew_green))
                categoryList.add("대기 오염")
            }
            else{
                isClickedList[1]=0
                binding.btnAirPolution.setBackgroundResource(R.drawable.ic_login_unclick)
                binding.tvAirPolution.setTextColor(resources.getColor(R.color.gray))
                categoryList.remove("쓰레기 처리")
            }
        }

        binding.btnTempChange.setOnClickListener {
            if (isClickedList[2]==0) {
                isClickedList[2]=1
                binding.btnTempChange.setBackgroundResource(R.drawable.ic_login_click)
                binding.tvTempChange.setTextColor(resources.getColor(R.color.grew_green))
                categoryList.add("기후 변화")
            }
            else{
                isClickedList[2]=0
                binding.btnTempChange.setBackgroundResource(R.drawable.ic_login_unclick)
                binding.tvTempChange.setTextColor(resources.getColor(R.color.gray))
                categoryList.remove("기후 변화")
            }
        }

        binding.btnWaterPolution.setOnClickListener {
            if (isClickedList[3]==0) {
                isClickedList[3]=1
                binding.btnWaterPolution.setBackgroundResource(R.drawable.ic_login_click)
                binding.tvWaterPolution.setTextColor(resources.getColor(R.color.grew_green))
                categoryList.add("수질 오염")
            }
            else{
                isClickedList[3]=0
                binding.btnWaterPolution.setBackgroundResource(R.drawable.ic_login_unclick)
                binding.tvWaterPolution.setTextColor(resources.getColor(R.color.gray))
                categoryList.remove("수질 오염")
            }
        }

        binding.btnEcoSystem.setOnClickListener {
            if (isClickedList[4]==0) {
                isClickedList[4]=1
                binding.btnEcoSystem.setBackgroundResource(R.drawable.ic_login_click)
                binding.tvEcoSystem.setTextColor(resources.getColor(R.color.grew_green))
                categoryList.add("생태계 보전")
            }
            else{
                isClickedList[4]=0
                binding.btnEcoSystem.setBackgroundResource(R.drawable.ic_login_unclick)
                binding.tvEcoSystem.setTextColor(resources.getColor(R.color.gray))
                categoryList.remove("생태계 보전")
            }
        }

        binding.btnProblemNext.setOnClickListener {
            val toAction = Intent(this, ChooseActionActivity::class.java)
            toAction.putExtra("accessToken", intent.getStringExtra("accessToken"))
            toAction.putStringArrayListExtra("category", categoryList)

            Log.d("kakao", categoryList.size.toString())
            Log.d("kakao", intent.getStringExtra("accessToken").toString())

            startActivity(toAction)
            finish()
        }
    }
}