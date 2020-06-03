package com.tms.mvvmdemo

import androidx.lifecycle.ViewModel

class MainActivityViewModel(var startingSum: Int) : ViewModel() {

    var totalSum: Int = startingSum

    fun getTotalSumExt(): Int {
        return totalSum
    }

    fun updateTotalSum(num: Int) {
        totalSum += num
    }

}