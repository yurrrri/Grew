package com.begreen.grew.src.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.begreen.grew.config.ApplicationClass
import com.begreen.grew.config.BaseActivity
import com.begreen.grew.databinding.ActivityLoginBinding
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    private var accessToken:String?=null
    private var name:String?=null
    val editor = ApplicationClass.sSharedPreferences.edit()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 로그인 공통 callback 구성
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                accessToken = null
                showCustomToast("카카오 로그인 실패! 다시 시도해주세요")
                Log.e("kakao", "로그인 실패", error)
            }
            else if (token != null) {
                //토큰 같이 넘기기

                accessToken = token.accessToken
                val toRegister = Intent(this, ChooseProblemActivity::class.java)
                toRegister.putExtra("accessToken", accessToken)

                Log.i("kakao", "로그인 성공 ${token.accessToken}")

                UserApiClient.instance.me { user, error ->
                    if (error != null) {
                        Log.e("kakao", "사용자 정보 요청 실패", error)
                    }
                    else if (user != null) {
                        Log.i("kakao", "사용자 정보 요청 성공" +
                                "\n회원번호: ${user.id}" +
                                "\n이메일: ${user.kakaoAccount?.email}" +
                                "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                                "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}")
                        name = user.kakaoAccount?.profile?.nickname
                        editor.putString("name", name)
                        editor.apply()
                    }
                }

                startActivity(toRegister)
                finish()
            }
        }

        binding.loginBtnKakao.setOnClickListener {
            // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)
            } else {
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        }
    }
}