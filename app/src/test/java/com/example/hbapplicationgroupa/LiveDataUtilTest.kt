package com.example.hbapplicationgroupa

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException


fun <T> LiveData<T>.getOrAwaitValueTest(
    time: Long = 10,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () ->Unit = {}
) : T {
    var data : T? = null
    val latch = CountDownLatch(1)
    val observer = object  : Observer<T> {
        override fun onChanged(t: T) {
            data = t
            latch.countDown()
            this@getOrAwaitValueTest.removeObserver(this)
        }

    }
    this.observeForever(observer)

    try {
        afterObserve.invoke()

//        if (!latch.await(time, timeUnit)){
//            throw TimeoutException("LiveData was never set")
//        }
    }finally {
        this.removeObserver(observer)
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}