package com.begreen.grew.util

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import com.begreen.grew.databinding.DialogLoadingBinding

class LoadingDialog(context: Context) : Dialog(context) {
    private lateinit var binding: DialogLoadingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = DialogLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setCanceledOnTouchOutside(false)
        setCancelable(false)
        window!!.setBackgroundDrawableResource(android.R.color.transparent)
        window!!.setDimAmount(0.0f)
    }

    override fun show() {
        if(!this.isShowing) super.show()
    }
}