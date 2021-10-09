package com.begreen.grew.src.main.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.begreen.grew.R
import com.begreen.grew.config.BaseFragment
import com.begreen.grew.databinding.FragmentMyPageBinding
import com.begreen.grew.src.main.HomeFragmentView
import com.begreen.grew.src.main.HomeService
import com.begreen.grew.src.main.model.MyPageResponse
import com.begreen.grew.src.main.model.NewsReponse
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(
    FragmentMyPageBinding::bind,
    R.layout.fragment_my_page
), HomeFragmentView {

    var tree:Int?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Inflate the layout for this fragment

        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transform(CenterCrop(), RoundedCorners(35))

        Glide
            .with(requireContext())
            .load(R.drawable.profile)
            .apply(requestOptions)
            .into(binding.imgProfile) //멤버 프로필

        showLoadingDialog(requireContext())
        HomeService(this).tryGetMyPage("이유리")

        binding.imgScrap.setOnClickListener {
            val intent = Intent(requireContext(), MyNewsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onGetNewsSuccess(response: NewsReponse) {
        TODO("Not yet implemented")
    }

    override fun onGetNewsFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onGetMyPageSuccess(response: MyPageResponse) {
        dismissLoadingDialog()

        binding.tvTreeNum.text = response.result.tree.toString()
        binding.tvActNum.text = response.result.activity.toString()

        tree = when (response.result.stage) {
            1 -> {
                R.drawable.ic_trees_05_1
            }
            2 -> {
                R.drawable.ic_trees_04_1
            }
            else -> {
                R.drawable.ic_tree_03_1
            }
        }

        Glide
            .with(requireContext())
            .load(tree)
            .into(binding.imgTree) //트리 이미지
    }

    override fun onGetMyPageFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }
}