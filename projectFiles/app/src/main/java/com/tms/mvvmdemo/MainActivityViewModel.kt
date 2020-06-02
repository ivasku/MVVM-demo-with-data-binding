package com.tms.mvvmdemo

import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    var totalSum: Int = 0

    fun getTotalSumExt(): Int {
        return totalSum
    }

    fun updateTotalSum(num: Int) {
        totalSum += num
    }

}