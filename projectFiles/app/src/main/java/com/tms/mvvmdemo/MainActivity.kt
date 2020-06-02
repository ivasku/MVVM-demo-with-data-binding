package com.tms.mvvmdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.tms.mvvmdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "TMS"
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main) // We do not need this setConentView anymore, we are doing this with dataBinding now
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // initialise ViewModel and register it to this activity
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        //use binding to set the initial value of the totalSum and to set it again onConfigurationChange from the ViewModel
        binding.tvSumResult.text = viewModel.getTotalSumExt().toString()

        // access button and textView from layout via binding object
        binding.bAdd.setOnClickListener {
            Log.d(TAG, "Add button clicked")
            //Use function from ViewModel to update the value of the total sum
            viewModel.updateTotalSum(binding.etNumField.text.toString().toInt())
            // using binding to set the textView with the data from the ViewModel to update it in the UI
            binding.tvSumResult.text = viewModel.getTotalSumExt().toString()
        }

        // declared a data block and a variable insinde the R.layout.activity_main
        // now we need to asign value to that variable and then in the layout you are using it with eg @{person.name}
        binding.person = createPerson("Pero" , "Driver")




    }

    private fun createPerson(name:String, pos:String) : Person {
        return Person(name, pos)
    }


}
