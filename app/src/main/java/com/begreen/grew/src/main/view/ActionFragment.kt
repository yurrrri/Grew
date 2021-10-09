package com.begreen.grew.src.main.view

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.begreen.grew.R
import com.begreen.grew.config.ApplicationClass
import com.begreen.grew.config.BaseFragment
import com.begreen.grew.databinding.FragmentActivityBinding
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import java.io.File
import java.io.IOException

class ActionFragment : BaseFragment<FragmentActivityBinding>(
    FragmentActivityBinding::bind,
    R.layout.fragment_activity
) {

    private val REQUEST_TAKE_PHOTO = 1

    private var takePicListener: PermissionListener = object : PermissionListener {
        override fun onPermissionGranted() {
            takePictureIntent()
        }

        override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
            showCustomToast("카메라 권한을 허용하지 않으셨습니다")
        }
    }
    val editor = ApplicationClass.sSharedPreferences.edit()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (ApplicationClass.sSharedPreferences.getInt("count", -1)==1){
            binding.imgGraph.setImageResource(R.drawable.graph_before)
        } else {
            binding.imgGraph.setImageResource(R.drawable.grew_after)
        }

        binding.imgRecommend.setOnClickListener {
            val webView = WebViewFragment()
            val bundle = Bundle()
            bundle.putString("url","https://www.keiti.re.kr:8445/site/keiti/ex/board/View.do?cbIdx=235&bcIdx=32829")
            webView.arguments = bundle
            switchContent(webView)
        }

        binding.imgMyActivity.setOnClickListener{
            val webView = WebViewFragment()
            val bundle = Bundle()
            bundle.putString("url","https://www.asiae.co.kr/article/2021082205275490426")
            webView.arguments = bundle
            switchContent(webView)
        }

        //카메라 연결
        binding.imgRecommend.setOnLongClickListener {
            //카메라 권한 받고
            TedPermission.with(requireContext())
                .setPermissionListener(takePicListener)
                .setPermissions(
                    Manifest.permission.CAMERA
                )
                .check()
            false
        }

        binding.imgDialog.setOnClickListener {
            val fragment = MyPageFragment()
            parentFragmentManager.beginTransaction().replace(R.id.outerFrame, fragment) .commit()
        }
    }

    // 사진 찍는 인텐트
    private fun takePictureIntent() {
        Log.d("test", "takePictureIntent")
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE) //사진 인텐트
        if (takePictureIntent.resolveActivity(requireContext().packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK){
            binding.imgGraph.setImageResource(R.drawable.grew_after)
            binding.imgGrayBackground.visibility = View.VISIBLE
            binding.imgDialog.visibility = View.VISIBLE

            editor.putInt("count", 2)
            editor.apply()
        }
    }

    private fun switchContent(fragment: Fragment) {
        if (context is MainActivity) {
            val mainActivity = context as MainActivity
            val frag: Fragment = fragment
            mainActivity.changeFragment(frag)
        }
    }
}