package com.osamaaftab.dindinn.presentation.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.appbar.AppBarLayout
import com.osamaaftab.dindinn.R
import com.osamaaftab.dindinn.databinding.ActivityMainBinding
import com.osamaaftab.dindinn.domain.model.MenuItem
import com.osamaaftab.dindinn.domain.model.base.PagerItem
import com.osamaaftab.dindinn.presentation.adapter.GenericPagerAdapter
import com.osamaaftab.dindinn.presentation.ui.fragment.BannerFragment
import com.osamaaftab.dindinn.presentation.ui.fragment.MenuFragment
import com.osamaaftab.dindinn.presentation.viewmodel.BannerViewModel
import com.osamaaftab.dindinn.presentation.viewmodel.MenuViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.math.abs

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var activityMainBinding: ActivityMainBinding
    private val bannerViewModel: BannerViewModel by viewModel()
    private val menuViewModel: MenuViewModel by viewModel()
    private var bannerList: ArrayList<PagerItem> = ArrayList()
    private var selectedList: List<MenuItem> = ArrayList()
    private lateinit var menuPagerAdapter: GenericPagerAdapter
    private lateinit var bannerPagerAdapter: GenericPagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initObserver()
        activityMainBinding.appbarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {
                activityMainBinding.viewpagertab.visibility = View.INVISIBLE
                menuViewModel.onDestoryCallBack(false)//Collapsed
                activityMainBinding.counterFab.visibility = View.VISIBLE

            } else {
                activityMainBinding.viewpagertab.visibility = View.VISIBLE
                activityMainBinding.counterFab.visibility = View.GONE
                menuViewModel.onDestoryCallBack(true) //Expanded
            }
        })
        setFragmentAdapter()
        initBannerAdapter()
        bannerViewModel.fetchBanner()
        activityMainBinding.counterFab.setOnClickListener(this)
    }

    private fun initObserver() {

        bannerViewModel.getOnShow().observe(this, Observer {
//            if (it == true) {
//                fragmentMenuBinding.indeterminateBar.visibility = View.VISIBLE
//            } else fragmentMenuBinding.indeterminateBar.visibility = View.GONE
        })

        menuViewModel.getOnNotifyCounter().observe(this, Observer {
            activityMainBinding.counterFab.increase()
        })

        menuViewModel.getSelectedList().observe(this, Observer {
            selectedList = it
        })


        bannerViewModel.getMenuList().observe(
            this,
            Observer {
                bannerList.clear()
                for (element in it.iterator()) {
                    bannerList.add(PagerItem(BannerFragment.newInstance(element.image_url)))
                }
                bannerPagerAdapter.notifyDataSetChanged()
            })

        bannerViewModel.getOnError().observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun initBannerAdapter() {
        bannerPagerAdapter =
            object : GenericPagerAdapter(this.supportFragmentManager) {
                override fun getViewsCount(): Int {
                    return bannerList.size
                }

                override fun getPagerItem(): ArrayList<PagerItem> {
                    return bannerList
                }
            }
        activityMainBinding.bannerViewpager.adapter = bannerPagerAdapter
        activityMainBinding.wormDotsIndicator.setViewPager(activityMainBinding.bannerViewpager)
    }

    private fun setFragmentAdapter() {
        val list: ArrayList<PagerItem> = ArrayList()
        list.add(PagerItem(MenuFragment.newInstance("dairy"), "dairy"))
        list.add(PagerItem(MenuFragment.newInstance("fruit"), "fruit"))
        list.add(PagerItem(MenuFragment.newInstance("vegetable"), "vegetable"))

        menuPagerAdapter = object : GenericPagerAdapter(supportFragmentManager) {
            override fun getViewsCount(): Int {
                return list.size
            }

            override fun getPagerItem(): ArrayList<PagerItem> {
                return list
            }
        }
        activityMainBinding.menuViewpager.adapter = menuPagerAdapter
        activityMainBinding.viewpagertab.setViewPager(activityMainBinding.menuViewpager)
    }

    override fun onClick(p0: View?) {
        val intent = Intent(this, CartActivity::class.java)
        intent.putParcelableArrayListExtra(SELECTED_LIST, ArrayList(selectedList))
        startActivity(intent)
    }

    companion object{
        const val SELECTED_LIST = "SELECTED_LIST"
    }
}