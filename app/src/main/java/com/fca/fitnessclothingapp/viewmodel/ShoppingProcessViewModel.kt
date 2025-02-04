package com.fca.fitnessclothingapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fca.fitnessclothingapp.modelclass.ShoppingItem

class ShoppingProcessViewModel : ViewModel() {

    private val _selectedCategory = MutableLiveData<String>()
    val selectedCategory: LiveData<String> get() = _selectedCategory

    private val _clickedItem = MutableLiveData<ShoppingItem>()
    val clickedItem: LiveData<ShoppingItem> get() = _clickedItem

     fun onCategorySelected(categoryName: String) {
        _selectedCategory.value = categoryName
    }

    fun onShoppingItemClicked(item: ShoppingItem) {
        _clickedItem.value = item
    }
}