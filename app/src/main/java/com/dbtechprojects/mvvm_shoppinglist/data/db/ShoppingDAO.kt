package com.dbtechprojects.mvvm_shoppinglist.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dbtechprojects.mvvm_shoppinglist.data.db.entities.ShoppingItem

@Dao
interface ShoppingDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ShoppingItem)

    @Delete
    suspend fun delete(item: ShoppingItem)

    @Query("SELECT * FROM shopping_items")
    fun getAllShoppingItems(): LiveData<List<ShoppingItem>>
}