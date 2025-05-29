package com.example.viewpager2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.viewpager2.databinding.PageLayoutBinding

class ViewPagerAdapter : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    private var itemList = mutableListOf<String>()

    inner class ViewPagerViewHolder(private val binding: PageLayoutBinding) : ViewHolder(binding.root){
        fun bind(item: String){
            binding.pageText.text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        return ViewPagerViewHolder(
            PageLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    fun submitData(data: List<String>){
        itemList.clear()
        itemList.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.bind(itemList[position])
    }
}