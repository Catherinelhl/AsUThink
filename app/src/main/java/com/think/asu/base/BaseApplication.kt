package com.think.asu.base

import android.app.Application


/**
+--------------------------------------+
+ @author Catherine Liu
+--------------------------------------+
+ 2020/11/2 14:29
+--------------------------------------+
+ Des:
+--------------------------------------+
 */

class BaseApplication : Application() {


    companion object {
        lateinit var instance: BaseApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}