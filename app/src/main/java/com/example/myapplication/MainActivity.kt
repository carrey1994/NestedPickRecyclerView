package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.reflect.Type

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv_index.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val mList = arrayListOf<Data>()
        val typeBeans = arrayListOf<TypeBean>()
        typeBeans.add(TypeBean("大"))
        typeBeans.add(TypeBean("小"))
        typeBeans.add(TypeBean("單"))
        typeBeans.add(TypeBean("雙"))
        typeBeans.add(TypeBean("全"))
        typeBeans.add(TypeBean("清"))

        for (i in 1..9) {
            val numbers = arrayListOf<NumberBean>()
            for (j in 1..10)
                numbers.add(NumberBean(i, j))
            mList.add(Data(i-1, numbers, typeBeans))
        }
        rv_index.adapter = IndexAdapter(mList)
    }
}
