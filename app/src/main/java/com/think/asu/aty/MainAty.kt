package com.think.asu.aty

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.think.asu.R
import com.think.asu.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.aty_main.*


/**
+--------------------------------------+
+ @author Catherine Liu
+--------------------------------------+
+ 2020/11/2 14:27
+--------------------------------------+
+ Des:首页分为【随机模式】、【自定义模式】
+--------------------------------------+
 */

class MainAty : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.aty_main)
//        mainViewModel =  ViewModelProvider(this,).get(MainViewModel::class.java)
        initView()
        initListener()
    }

    private fun initView() {
    }


    private fun initListener() {
        tvAIMode.setOnClickListener {
            getRandomData()
        }
    }

    private fun getRandomData() {
        //Step 1:random a number to stand for the array length
        var startRandom = 10
        var endRandom = 20
        var arrayLength = (startRandom..endRandom).random()
//        println("随机出$arrayLength 组数据")
        //声明一个变量来得到最后的结果
        //Step 2:根据这个随机数遍历，又去获取每一个index下表生成1-n的数，作为事件的位数，然后遍历这个位数，生成1-n之间的随机数，作为动作
        var indexStartRandom = 1
        var indexEndRandom = 6
        var indexRandom: Int
        //声明最后的随机数据结果
        var indexArrayRandom: MutableList<MutableList<Int>> = arrayListOf()
        for (i in 1..arrayLength) {
            //声明每一步对应的随机数组
            var indexSingleArrayRandom: MutableList<Int> = arrayListOf()
            indexRandom = (indexStartRandom..indexEndRandom).random()
//            println("第 $i 位的随机事件位数是 $indexRandom")
            for (j in 1..indexRandom) {
                var randomSingle = (indexStartRandom..indexEndRandom).random()
//                println("第 $i 位的随机动作是 $randomSingle")
                indexSingleArrayRandom.add(randomSingle)
            }
            indexArrayRandom.add(indexSingleArrayRandom)
        }
        println("最后生成的数据为：$indexArrayRandom")

    }


}