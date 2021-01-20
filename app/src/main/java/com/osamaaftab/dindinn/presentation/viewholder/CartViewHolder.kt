package com.osamaaftab.dindinn.presentation.viewholder

import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.osamaaftab.dindinn.presentation.adapter.GenericListAdapter

class CartViewHolder<T> (
    private val viewDataBinding: ViewDataBinding
) : RecyclerView.ViewHolder(viewDataBinding.root),
    GenericListAdapter.Binder<T> {

    override fun bindItem(data: T) {
        viewDataBinding.setVariable(BR.menu,data)
    }
}