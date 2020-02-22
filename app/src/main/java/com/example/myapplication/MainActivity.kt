package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.reflect.Type

class MainActivity : AppCompatActivity() {
    val mList = arrayListOf<Data>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv_index.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        initRecyclerView()

        btn_data.setOnClickListener {
            Log.e("TheData====", "${Gson().toJson(mList)}===")
        }

        btn_reset.setOnClickListener {
            initRecyclerView()
        }
    }

    private fun initRecyclerView() {
        mList.clear()
        val typeBeans = arrayListOf<TypeBean>()
        TypeEnum.values().forEach {
            typeBeans.add(TypeBean(it))
        }
        for (i in 1..9) {
            val numbers = arrayListOf<NumberBean>()
            for (j in 1..10)
                numbers.add(NumberBean(i, j))
            mList.add(Data(i - 1, numbers, typeBeans))
        }
        rv_index.adapter = IndexAdapter(mList)
    }

}
