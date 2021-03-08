package com.dbtechprojects.mvvm_shoppinglist.ui.shoppinglist

import androidx.lifecycle.ViewModel
import com.dbtechprojects.mvvm_shoppinglist.data.db.entities.ShoppingItem
import com.dbtechprojects.mvvm_shoppinglist.data.repositories.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel(
    private val repository: ShoppingRepository
): ViewModel() {

    // ROOM Provides Main thread safety so we okay using Dispatchers.Main
    fun upsert(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.upsert(item)
    }

    fun delete(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(item)
    }

    fun getAllShoppingItems() = repository.getAllShoppingItems()


}