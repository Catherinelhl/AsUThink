package com.think.asu.tools

/**
+--------------------------------------+
+ @author Catherine Liu
+--------------------------------------+
+ 2020/11/2 15:34
+--------------------------------------+
+ Des:点击工具类 (https://www.jianshu.com/p/f05e561ed13c)
+--------------------------------------+
 */


//@JvmOverloads
//fun View.avoidShakeClick(windowDuration: Long = 1000,
//                                      unit: TimeUnit = TimeUnit.MILLISECONDS,
//                                      listener: (view: View) -> Unit): Disposable {
//    return Observable.create(ObservableOnSubscribe<View> { emitter ->
//        setOnClickListener {
//            if (!emitter.isDisposed) {
//                emitter.onNext(it)
//            }
//        }
//    }).throttleFirst(windowDuration, unit)
//        .subscribe { listener(it) }
//}