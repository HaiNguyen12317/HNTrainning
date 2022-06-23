package com.vnpay.hainv4.adapter

import android.view.LayoutInflater.from
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import com.vnpay.hainv4.databinding.ItemParentBinding
import com.vnpay.hainv4.model.Item


class ParentItemAdapter() :
    RecyclerView.Adapter<ParentItemAdapter.ParentViewHolder>() {
    private var binding: ItemParentBinding? = null
    private val differUtils = object : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer(this, differUtils)
    private val viewPool = RecycledViewPool()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ParentViewHolder {
        binding = ItemParentBinding.inflate(from(parent.context), parent, false)
        return ParentViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
        val layoutManager = GridLayoutManager(
            holder.childRecycleView.context,
            3
        )
        layoutManager.initialPrefetchItemCount = differ.currentList[position].title.length

        val childItemAdapter = ChildItemAdapter()
        holder.childRecycleView.layoutManager = layoutManager
        holder.childRecycleView.adapter = childItemAdapter
        holder.childRecycleView.setRecycledViewPool(viewPool)

    }


    override fun getItemCount(): Int = differ.currentList.size

    class ParentViewHolder(private val binding: ItemParentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var childRecycleView: RecyclerView = RecyclerView(binding.childRecyclerview.context)
        fun bind(type: Item) {
            binding.parentItemTitle.text = type.title
            childRecycleView = binding.childRecyclerview

        }

    }
}