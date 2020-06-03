package com.tms.mvvmdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel(var startingSum: Int) : ViewModel() {

    private var totalSum: Int = startingSum
    private var totalSumLive = MutableLiveData<Int>()

    init {
        //liveData
        totalSumLive.value = startingSum
    }

    fun getTotalSumExt(): Int {
        return totalSum
    }

    fun updateTotalSum(num: Int) {
        totalSum += num
    }

    //same example as with standard viewModel only with liveData
    //we return liveData, NOT multableLiveData in order to make sure that the user cannot change the value
    fun getTotalSunLiveData() : LiveData<Int> {
        return totalSumLive
    }

    fun updateTotalSumLiveData(num: Int) {
        totalSumLive.value = (totalSumLive.value)?.plus(num)
    }

}