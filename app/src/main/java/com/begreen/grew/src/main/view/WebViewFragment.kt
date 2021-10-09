package com.begreen.grew.src.main.view

import android.os.Bundle
import android.view.View
import com.begreen.grew.R
import com.begreen.grew.config.BaseFragment
import com.begreen.grew.databinding.FragmentWebviewBinding
import android.webkit.WebView
import android.webkit.WebViewClient


class WebViewFragment : BaseFragment<FragmentWebviewBinding>(
    FragmentWebviewBinding::bind,
    R.layout.fragment_webview
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments?.getString("url", null)!=null){
            binding.webview.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {

                    return false
                }
            }
            binding.webview.loadUrl(arguments?.getString("url", null)!!)
        }
    }
}