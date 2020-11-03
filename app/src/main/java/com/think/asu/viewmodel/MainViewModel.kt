package com.think.asu.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData


/**
+--------------------------------------+
+ @author Catherine Liu
+--------------------------------------+
+ 2020/11/2 17:15
+--------------------------------------+
+ Des:
+--------------------------------------+
 */

class MainViewModel(application: Application) : AndroidViewModel(application) {

    var result: MediatorLiveData<String> = MediatorLiveData()


    fun getRandomData(
        startRandom: Int,
        endRandom: Int,
        indexStartRandom: Int,
        indexEndRandom: Int
    ) {
        //Step 1:random a number to stand for the array length
        var arrayLength = (startRandom..endRandom).random()
//        Log.e("tts getRandomData：", "随机出$arrayLength 组数据")
        //声明一个变量来得到最后的结果
        //Step 2:根据这个随机数遍历，又去获取每一个index下表生成1..n的数，作为事件的位数，然后遍历这个位数，生成1-n之间的随机数，作为动作
        var indexRandom: Int
        for (i in 1..arrayLength) {
            //声明每一步对应的随机数组
            var indexSingleSB = StringBuffer()
            indexRandom = (indexStartRandom..indexEndRandom).random()
//            println("第 $i 位的随机事件位数是 $indexRandom")
            for (j in 1..indexRandom) {
                indexSingleSB.append("${(indexStartRandom..indexEndRandom).random()} ")
            }
            result.value = indexSingleSB.toString()

        }
    }
}