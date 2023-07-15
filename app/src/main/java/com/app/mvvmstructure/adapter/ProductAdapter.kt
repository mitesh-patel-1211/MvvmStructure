package com.app.mvvmstructure.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.mvvmstructure.databinding.LayoutProductBinding
import com.app.mvvmstructure.model.ProductsItem
import com.app.mvvmstructure.utils.toHtml

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    private var productList: MutableList<ProductsItem?> = mutableListOf()

    class ViewHolder(val binding: LayoutProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = productList[position]
        with(holder.binding) {
            tvPhoneName.text = model?.title.toString().toHtml("Phone Name: ")
            tvBrand.text = model?.brand.toString().toHtml("Brand: ")
            tvPrice.text = model?.price.toString().toHtml("Price: ")
        }
    }

    override fun getItemCount(): Int = productList.size

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(productList: MutableList<ProductsItem?>) {
        this.productList.clear()
        this.productList.addAll(productList)
        notifyDataSetChanged()
    }


}
