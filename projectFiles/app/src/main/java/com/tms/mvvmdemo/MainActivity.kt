package com.tms.mvvmdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.bind
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.tms.mvvmdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "TMS"
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var viewModelFactory: MainActivityViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main) // We do not need this setConentView anymore, we are doing this with dataBinding now
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // initialise ViewModel and register it to this activity
        //viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModelFactory = MainActivityViewModelFactory(100)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainActivityViewModel::class.java) // since we have factory now we need to pass it as a second argument here so we init viewModel like this


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


        //ObserveLiveData
        viewModel.getTotalSunLiveData().observe(this, Observer {
            //this is where we get notified with new data
            binding.tvSumResult2.text = it.toString()
        })

        binding.bAdd2.setOnClickListener {
            viewModel.updateTotalSumLiveData(binding.etNumField2.text.toString().toInt())
            // no need to set again the textView with the new data as we did before because we are observing the changes in LiveData and when it changes the code
            // from line 53 - 56 will be called.
        }

        // we can also set the viewModel to the var to use in the layout onClick
        binding.viewModel = viewModel

    }

    private fun createPerson(name:String, pos:String) : Person {
        return Person(name, pos)
    }


}
