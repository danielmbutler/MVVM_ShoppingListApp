package com.dbtechprojects.mvvm_shoppinglist.ui.shoppinglist

import com.dbtechprojects.mvvm_shoppinglist.data.db.entities.ShoppingItem

interface AddDialogListner {

    fun onAddButton(item: ShoppingItem)
}