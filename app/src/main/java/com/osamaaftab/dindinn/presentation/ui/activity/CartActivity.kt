package com.osamaaftab.dindinn.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.osamaaftab.dindinn.R
import com.osamaaftab.dindinn.databinding.ActivityCartBinding
import com.osamaaftab.dindinn.domain.model.MenuItem
import com.osamaaftab.dindinn.domain.model.base.PagerItem
import com.osamaaftab.dindinn.presentation.adapter.GenericPagerAdpater
import com.osamaaftab.dindinn.presentation.fragment.BannerFragment
import com.osamaaftab.dindinn.presentation.fragment.CartListFragment


class CartActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var activityCartBinding: ActivityCartBinding
    private lateinit var cartPagerAdpater: GenericPagerAdpater
    private lateinit var items: List<MenuItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        activityCartBinding = DataBindingUtil.setContentView(this, R.layout.activity_cart)
        items = intent.getParcelableArrayListExtra("selected")
        activityCartBinding.back.setOnClickListener(this)
        setFragmentAdapter()
    }

    private fun setFragmentAdapter() {
        val list: ArrayList<PagerItem> = ArrayList()
        list.add(PagerItem(CartListFragment.newInstance(items), "Cart"))
        list.add(PagerItem(BannerFragment.newInstance(""), "Orders"))
        list.add(PagerItem(BannerFragment.newInstance(""), "Information"))

        cartPagerAdpater = object : GenericPagerAdpater(supportFragmentManager) {
            override fun getViewsCount(): Int {
                return list.size
            }

            override fun getPagerItem(): ArrayList<PagerItem> {
                return list
            }
        }
        activityCartBinding.cartViewpager.adapter = cartPagerAdpater
        activityCartBinding.viewpagertab.setViewPager(activityCartBinding.cartViewpager)
    }

    override fun onClick(p0: View?) {
        finish()
    }
}