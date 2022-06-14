package com.example.businesscard.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.businesscard.data.BusinessCard
import com.example.businesscard.databinding.ItemBusinessCardBinding

class BusinessCardAdapter :
    ListAdapter<BusinessCard, BusinessCardAdapter.ViewHolder>(DiffCallback()) {

    var listenerShare: (View) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBusinessCardBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemBusinessCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BusinessCard) {
            binding.textViewNome.text = item.nome
            binding.textViewTelefone.text = item.telefone
            binding.textViewEmail.text = item.email
            binding.textViewEmpresa.text = item.empresa
            binding.cdContentMaterialCard.setCardBackgroundColor(Color.parseColor(item.fundoPersonalizado))
            binding.cdContentMaterialCard.setOnClickListener {
                listenerShare(it)
            }
        }

    }

    class DiffCallback : DiffUtil.ItemCallback<BusinessCard>() {
        override fun areItemsTheSame(oldItem: BusinessCard, newItem: BusinessCard) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: BusinessCard, newItem: BusinessCard) =
            oldItem.id == newItem.id

    }


}