package com.example.myapplication

import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class IndexAdapter(private var data: MutableList<Data>) : RecyclerView.Adapter<IndexAdapter.IndexViewHolder>() {

	private val typeSpares = SparseArray<TypeBeans>()
	private val numberSpares = SparseArray<NumberBeans>()
	private val pickSpares = SparseArray<MutableList<Int>>()

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndexViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, null, false)
		return IndexViewHolder(view)
	}

	override fun getItemCount(): Int = data.size

	override fun onBindViewHolder(holder: IndexViewHolder, position: Int) {
		holder.rv_types.layoutManager = GridLayoutManager(holder.itemView.context, data[position].typeBeans.size, GridLayoutManager.VERTICAL, false)
		holder.rv_numbers.layoutManager = GridLayoutManager(holder.itemView.context, 5, GridLayoutManager.VERTICAL, false)

		if (position == data[position].layer) {
			if (typeSpares[position] == null) {
				typeSpares.put(position, getPureTypeBeans())
				holder.rv_types.adapter = getTypeAdapter(position, holder)
			} else {
				holder.rv_types.adapter = getTypeAdapter(position, holder)
			}
			if (numberSpares[position] == null) {
				numberSpares.put(position, data[position].numbers)
				pickSpares.put(position, mutableListOf())
			}
			holder.rv_numbers.adapter = getNumberAdapter(position, holder)
		}
	}

	fun getPureTypeBeans(): ArrayList<TypeBean> {
		val typeBeans = arrayListOf<TypeBean>()
		TypeEnum.values().forEach {
			typeBeans.add(TypeBean(it))
		}
		return typeBeans
	}

	private fun getNumberAdapter(position: Int, holder: IndexViewHolder): NumberAdapter =
		NumberAdapter(numberSpares.get(position)).apply {
			setNumberPickListener(object : NumberAdapter.NumberPickListener {
				override fun onNumberPick(numberIndex: Int) {
					val mList = pickSpares[position]
					if (!mList.contains(numberIndex))
						mList.add(numberIndex)
					else
						mList.remove(numberIndex)
					val mNumbers = numberSpares[position]
					mNumbers.forEach { it.isCheck = false }
					mList.forEach { mNumbers[it].isCheck = true }
					typeSpares.put(position, getPureTypeBeans())
					holder.rv_types.adapter = getTypeAdapter(position, holder)
					holder.rv_numbers.adapter = getNumberAdapter(position, holder)
					holder.rv_numbers.adapter?.notifyDataSetChanged()
				}
			})
		}

	private fun getTypeAdapter(position: Int, holder: IndexViewHolder): TypeAdapter =
		TypeAdapter(typeSpares.get(position)).apply {
			setOnTypeSelctListener(object : TypeAdapter.OnTypeSelect {
				override fun onType(typePosition: Int) {
					val typeBeans = getPureTypeBeans()
					typeBeans[typePosition].isCheck = true
					typeSpares.put(position, typeBeans)
					pickSpares[position].clear()
					when (typeBeans[typePosition].type.name) {
						TypeEnum.ALL.name -> {
							numberSpares[position].forEach { it.isCheck = true }
							for (i in 0..9)
								pickSpares[position].add(i)
						}
						TypeEnum.CLEAR.name	-> {
							numberSpares[position].forEach { it.isCheck = false }
							typeBeans[typePosition].isCheck = false
							typeSpares.put(position, typeBeans)
						}
						TypeEnum.BIG.name-> {
							numberSpares[position].forEach { it.isCheck = it.number > numberSpares[position].size/2 }
							for (i in 5..9)
								pickSpares[position].add(i)
						}
						TypeEnum.LITTLE.name -> {
							numberSpares[position].forEach { it.isCheck = it.number <= numberSpares[position].size/2 }
							for (i in 0..4)
								pickSpares[position].add(i)
						}
						TypeEnum.ODD.name -> {
							numberSpares[position].forEach { it.isCheck = it.number % 2 != 0 }
							for (i in 0..9 step 2)
								pickSpares[position].add(i)
						}
						TypeEnum.EVEN.name -> {
							numberSpares[position].forEach { it.isCheck = it.number % 2 == 0 }
							for (i in 1..9 step 2)
								pickSpares[position].add(i)
						}
					}
					holder.rv_numbers.adapter = getNumberAdapter(position, holder)
					holder.rv_types.adapter = getTypeAdapter(position, holder)
					holder.rv_types.adapter?.notifyDataSetChanged()
				}
			})
		}

	inner class IndexViewHolder(view: View) : RecyclerView.ViewHolder(view) {
		val rv_types = view.findViewById<RecyclerView>(R.id.rv_types)
		val rv_numbers = view.findViewById<RecyclerView>(R.id.rv_numbers)
	}
}