package com.example.myapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TypeAdapter(val typeBeans: TypeBeans) : RecyclerView.Adapter<TypeAdapter.TypeViewHolder>() {
	
	private lateinit var typeSelect: OnTypeSelect
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.item_type, parent, false)
		return TypeViewHolder(view)
	}
	
	override fun getItemCount(): Int = typeBeans.size
	
	override fun onBindViewHolder(holder: TypeViewHolder, position: Int) {
		holder.tvType.text = typeBeans[position].type
		holder.tvType.isSelected = typeBeans[position].isCheck
		holder.tvType.setOnClickListener {
			typeSelect.onType(position)
		}
	}
	
	inner class TypeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
		val tvType = view.findViewById<TextView>(R.id.tv_type)
	}
	
	interface OnTypeSelect {
		fun onType(typePosition: Int)
	}
	
	fun setOnTypeSelctListener(onTypeSelect: OnTypeSelect) {
		this.typeSelect = onTypeSelect
	}
}