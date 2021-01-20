package com.osamaaftab.dindinn.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.osamaaftab.dindinn.R
import com.osamaaftab.dindinn.databinding.ActivityMainBinding
import com.osamaaftab.dindinn.databinding.FragmentMenuBinding
import com.osamaaftab.dindinn.domain.model.MenuItem
import com.osamaaftab.dindinn.presentation.adapter.GenericListAdapter
import com.osamaaftab.dindinn.presentation.customview.PagerSnapHelper
import com.osamaaftab.dindinn.presentation.customview.SnapHelper
import com.osamaaftab.dindinn.presentation.viewholder.MenuViewHolder
import com.osamaaftab.dindinn.presentation.viewmodel.MenuViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class MenuFragment : Fragment() {

    lateinit var fragmentMenuBinding: FragmentMenuBinding
    lateinit var snapHelper: SnapHelper
    private val menuViewModel: MenuViewModel by sharedViewModel()
    private var menuAdapter: GenericListAdapter<MenuItem>? = null

    companion object {
        @JvmStatic
        fun newInstance(type: String) = MenuFragment().apply {
            arguments = Bundle().apply {
                putString("type", type)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMenuBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_menu, container, false)
        initObserver()
        initAdapter()
        menuViewModel.fetchMenu(arguments?.getString("type"))
        return fragmentMenuBinding.root
    }

    private fun initObserver() {

        menuViewModel.getOnShow().observe(this, Observer {
            if (it == true) {
                fragmentMenuBinding.indeterminateBar.visibility = View.VISIBLE
            } else fragmentMenuBinding.indeterminateBar.visibility = View.GONE
        })
        menuViewModel.getCallBack().observe(this, Observer {
            if (it == true) {
                snapHelper.destroyCallbacks()
            } else snapHelper.setupCallbacks()

        })

        menuViewModel.getMenuList().observe(
            this,
            Observer {
                menuAdapter?.itemList = it
            })

        menuViewModel.getOnError().observe(this, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun initAdapter() {
        menuAdapter = object : GenericListAdapter<MenuItem>() {

            override fun getViewHolder(viewDataBinding: ViewDataBinding): RecyclerView.ViewHolder {
                return MenuViewHolder<MenuItem>(viewDataBinding, menuViewModel)
            }

            override fun getLayoutId(): Int {
                return R.layout.menu_item
            }
        }
        fragmentMenuBinding.menuRecyclerView.adapter = menuAdapter
        snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(fragmentMenuBinding.menuRecyclerView)
    }
}