package com.dbtechprojects.mvvm_shoppinglist.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dbtechprojects.mvvm_shoppinglist.data.db.entities.ShoppingItem


@Database(
    entities = [ShoppingItem::class],
    version = 1 // everytime you update columns need to update this
)
abstract class ShoppingDatabase: RoomDatabase() {

    abstract fun getShoppingDao(): ShoppingDAO



    companion object{ // final in java ,  these elements can only be created once
        @Volatile
        private var instance: ShoppingDatabase? = null
        private val LOCK = Any()

        // executed when class is invoked i.e ShoppingDatabase()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){

            // no other processes can now run whilst we null check the DB
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
            ShoppingDatabase::class.java, "ShoppingDB.db").build()

    }
}