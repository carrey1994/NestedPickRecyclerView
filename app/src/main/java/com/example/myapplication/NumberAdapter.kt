package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NumberAdapter(private var numberBeans: NumberBeans) : RecyclerView.Adapter<NumberAdapter.NumberViewHolder>() {
	
	private var numberPickListener: NumberPickListener? = null
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.item_number, parent, false)
		return NumberViewHolder(view)
	}
	
	override fun getItemCount(): Int = numberBeans.size
	
	override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
		holder.tvNumber.text = numberBeans[position].number.toString()
		holder.tvNumber.isSelected = numberBeans[position].isCheck
		holder.tvNumber.setOnClickListener {
			numberPickListener?.onNumberPick(position)
		}
	}
	
	inner class NumberViewHolder(view: View) : RecyclerView.ViewHolder(view) {
		val tvNumber = view.findViewById<TextView>(R.id.tv_number)
	}
	
	interface NumberPickListener {
		fun onNumberPick(numberIndex: Int)
	}
	
	fun setNumberPickListener(numberPickListener: NumberPickListener) {
		this.numberPickListener = numberPickListener
	}
}