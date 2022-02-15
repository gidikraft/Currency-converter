package com.example.currencyconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconverter.databinding.ActivityMainBinding
import com.example.currencyconverter.model.Rates
import com.example.currencyconverter.repository.CurrencyRepository
import com.example.currencyconverter.viewmodel.CurrencyViewModel
import com.example.currencyconverter.viewmodel.CurrencyViewModelFactoryProvider

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CurrencyViewModel
    private lateinit var firstRatePicker: String
    private lateinit var secondRatePicker: String

    private lateinit var dropDownList: Rates

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firstDropDown = binding.startDropdown
        val secondDropdown = binding.targetDropdown

        //initialise viewModel using custom ViewModel Provider
        val currencyRepository = CurrencyRepository()
        val viewModelProviderFactory = CurrencyViewModelFactoryProvider(currencyRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[CurrencyViewModel::class.java]

        //intialise view to be updated with liveData
        viewModel.currencyUpdates.observe(this, { response ->
            if (response.isSuccessful){
                binding.newCurrency.text = response.body()?.date

                dropDownList = response.body()!!.rates
                val dropdownMenu = arrayOf(dropDownList)

                firstDropDown.adapter = ArrayAdapter(this,
                    android.R.layout.simple_spinner_item,
                    dropdownMenu)

                //override dropdown listener to choose from dropdown
                firstDropDown.onItemSelectedListener = object : AdapterView.OnItemClickListener,
                    AdapterView.OnItemSelectedListener {
                    override fun onItemClick(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {}

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        firstRatePicker = parent?.getItemAtPosition(position).toString()
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                }

                secondDropdown.adapter = ArrayAdapter(this,
                    android.R.layout.simple_spinner_item,
                    dropdownMenu)

                //override dropdown listener to choose from dropdown
                secondDropdown.onItemSelectedListener = object: AdapterView.OnItemClickListener,
                    AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {}

                    override fun onNothingSelected(parent: AdapterView<*>?) {}

                    override fun onItemClick(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        secondRatePicker = parent?.getItemAtPosition(position).toString()
                    }
                }
            }
        })
    }
}