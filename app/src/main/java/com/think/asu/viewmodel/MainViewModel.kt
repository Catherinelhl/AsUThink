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


    /**
     * 生产按顺序的数据
     */
    fun getAscModeData() {
        //Step 1:从 1、2；1、3；1、4；1、5；1、n...依次类推
        //Step 2:define two variable to stand for first param,and another is second param
        var firstParam = 1
        var secondParam = 8
        //两位
        for (i in firstParam..secondParam) {
            for (j in firstParam..secondParam) {
                result.value = "$i  $j"
            }
        }


    }

    /**
     * 生产倒序数据
     */
    fun getDescModeData() {

    }

    /**
     *生产系统智能模式/自定义模式下的数据
     */
    fun getRandomModeData(
        startRandom: Int,
        endRandom: Int,
        indexStartRandom: Int,
        indexEndRandom: Int
    ) {
        //Step 1:random a number to stand for the array length
        var arrayLength = (startRandom..endRandom).random()
        //Step 2:根据这个随机数遍历，又去获取每一个index下表生成1..n的数，作为事件的位数，然后遍历这个位数，生成1-n之间的随机数，作为动作
        var indexRandom: Int
        for (i in 1..arrayLength) {
            //声明每一步对应的随机动作数组
            var indexSingleSB = StringBuffer()
            indexRandom = (indexStartRandom..indexEndRandom).random()
            for (j in 1..indexRandom) {
                indexSingleSB.append("${(indexStartRandom..indexEndRandom).random()} ")
            }
            result.value = indexSingleSB.toString()
        }
    }

}