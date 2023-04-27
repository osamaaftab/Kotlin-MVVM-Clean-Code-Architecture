package com.osamaaftab.dindinn.presentation.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.osamaaftab.dindinn.R
import com.osamaaftab.dindinn.databinding.ActivityCartBinding
import com.osamaaftab.dindinn.domain.model.MenuItem
import com.osamaaftab.dindinn.domain.model.base.PagerItem
import com.osamaaftab.dindinn.presentation.adapter.GenericPagerAdapter
import com.osamaaftab.dindinn.presentation.ui.activity.MainActivity.Companion.SELECTED_LIST
import com.osamaaftab.dindinn.presentation.ui.fragment.BannerFragment
import com.osamaaftab.dindinn.presentation.ui.fragment.CartListFragment


class CartActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var activityCartBinding: ActivityCartBinding
    private lateinit var cartPagerAdapter: GenericPagerAdapter
    private lateinit var items: List<MenuItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        activityCartBinding = DataBindingUtil.setContentView(this, R.layout.activity_cart)
        items = intent.getParcelableArrayListExtra(SELECTED_LIST)
        activityCartBinding.back.setOnClickListener(this)
        setFragmentAdapter()
    }

    private fun setFragmentAdapter() {
        val list: ArrayList<PagerItem> = ArrayList()
        list.add(PagerItem(CartListFragment.newInstance(items), "Cart"))
        list.add(PagerItem(BannerFragment.newInstance(""), "Orders"))
        list.add(PagerItem(BannerFragment.newInstance(""), "Information"))

        cartPagerAdapter = object : GenericPagerAdapter(supportFragmentManager) {
            override fun getViewsCount(): Int {
                return list.size
            }

            override fun getPagerItem(): ArrayList<PagerItem> {
                return list
            }
        }
        activityCartBinding.cartViewpager.adapter = cartPagerAdapter
        activityCartBinding.viewpagertab.setViewPager(activityCartBinding.cartViewpager)
    }

    override fun onClick(p0: View?) {
        finish()
    }
}