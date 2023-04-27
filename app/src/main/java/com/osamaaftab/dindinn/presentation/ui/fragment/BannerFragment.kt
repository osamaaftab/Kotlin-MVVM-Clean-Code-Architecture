package com.osamaaftab.dindinn.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import coil.api.load
import com.osamaaftab.dindinn.R
import com.osamaaftab.dindinn.databinding.FragmentBannerImageBinding

class BannerFragment : Fragment() {

    lateinit var fragmentBannerImageBinding: FragmentBannerImageBinding

    companion object {
        private const val URL  = "URL"

        @JvmStatic
        fun newInstance(url: String) = BannerFragment().apply {
            arguments = Bundle().apply {
                putString(URL, url)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBannerImageBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_banner_image, container, false)
        fragmentBannerImageBinding.galleryImage.load(arguments?.getString(URL))
        return fragmentBannerImageBinding.root
    }
}