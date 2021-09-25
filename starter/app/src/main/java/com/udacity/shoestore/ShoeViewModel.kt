package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {
    var currentShoe = MutableLiveData<Shoe>()

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    init {
        createInitialShoeList()
        currentShoe.value = getDefaultShoe()
    }

    fun addShoe() {
        _shoeList.value?.add(currentShoe?.value ?: getDefaultShoe())
        currentShoe.value = getDefaultShoe()
    }

    private fun getDefaultShoe(): Shoe {
        return Shoe("", 0.0, "", "")
    }

    private fun createInitialShoeList() {
        _shoeList.value = mutableListOf()
        _shoeList.value?.add(
            Shoe(
                "Sneakers",
                8.5,
                "Sketchers",
                "These are really cool, they light up!"
            )
        )
        _shoeList.value?.add(
            Shoe(
                "Slippers",
                9.4,
                "Nikes",
                "These shoes are great for casual walks."
            )
        )
        _shoeList.value?.add(Shoe("Spikes", 5.0, "Adidas", "These are a must have for runners."))
        _shoeList.value?.add(Shoe("Boots", 8.1, "Reebok", "These are great for tough weather."))
        _shoeList.value?.add(
            Shoe(
                "Air",
                7.5,
                "Jordan",
                "These are excellent for basketball players."
            )
        )
        _shoeList.value?.add(
            Shoe(
                "Flip Flops",
                11.0,
                "Yeezy",
                "This might be expensive, but they're comfortable."
            )
        )
        _shoeList.value?.add(
            Shoe(
                "Sandals",
                7.0,
                "North Face",
                "These are great for walks on the beach!"
            )
        )
    }
}