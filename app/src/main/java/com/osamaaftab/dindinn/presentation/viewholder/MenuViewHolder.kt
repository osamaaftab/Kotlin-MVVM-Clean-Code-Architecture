package com.osamaaftab.dindinn.presentation.viewholder

import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.osamaaftab.dindinn.presentation.adapter.GenericListAdapter
import com.osamaaftab.dindinn.presentation.viewmodel.MenuViewModel

class MenuViewHolder<T> (
    private val viewDataBinding: ViewDataBinding,private val menuViewModel: MenuViewModel) : RecyclerView.ViewHolder(viewDataBinding.root),
    GenericListAdapter.Binder<T> {

    override fun bindItem(data: T) {
        viewDataBinding.setVariable(BR.menu,data)
        viewDataBinding.setVariable(BR.viewModel,menuViewModel)
    }
}