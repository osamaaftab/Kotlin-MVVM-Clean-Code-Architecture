package com.osamaaftab.dindinn.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.osamaaftab.dindinn.R
import com.osamaaftab.dindinn.databinding.FragmentCartListBinding
import com.osamaaftab.dindinn.domain.model.MenuItem
import com.osamaaftab.dindinn.presentation.adapter.GenericListAdapter
import com.osamaaftab.dindinn.presentation.viewholder.CartViewHolder
import kotlin.math.round

class CartListFragment : Fragment() {

    lateinit var fragmentCartListBinding: FragmentCartListBinding
    private var menuAdapter: GenericListAdapter<MenuItem>? = null

    companion object {
        private const val LIST = "LIST"

        @JvmStatic
        fun newInstance(items: List<MenuItem>) = CartListFragment().apply {
            arguments = Bundle().apply {
                putParcelableArrayList(LIST, ArrayList(items))
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentCartListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_cart_list, container, false)
        val list = arguments?.getParcelableArrayList<MenuItem>(LIST)
        if (list != null) {
            initAdapter(list.toList())
            fragmentCartListBinding.total.text =
                round(list.map { it.price }.sum()).toString() + " Usd"
        }
        return fragmentCartListBinding.root
    }

    private fun initAdapter(list: List<MenuItem>) {
        menuAdapter = object : GenericListAdapter<MenuItem>() {

            override fun getViewHolder(viewDataBinding: ViewDataBinding): RecyclerView.ViewHolder {
                return CartViewHolder<MenuItem>(viewDataBinding)
            }

            override fun getLayoutId(): Int {
                return R.layout.cart_item
            }
        }
        fragmentCartListBinding.cartRecyclerView.adapter = menuAdapter
        menuAdapter?.itemList = list
    }

}