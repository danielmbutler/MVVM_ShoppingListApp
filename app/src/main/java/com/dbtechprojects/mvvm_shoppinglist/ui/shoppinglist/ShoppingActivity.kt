package com.dbtechprojects.mvvm_shoppinglist.ui.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dbtechprojects.mvvm_shoppinglist.R
import com.dbtechprojects.mvvm_shoppinglist.data.db.ShoppingDatabase
import com.dbtechprojects.mvvm_shoppinglist.data.db.entities.ShoppingItem
import com.dbtechprojects.mvvm_shoppinglist.data.repositories.ShoppingRepository
import com.dbtechprojects.mvvm_shoppinglist.other.ShoppingItemAdapter
import kotlinx.android.synthetic.main.activity_shopping.*

class ShoppingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val db = ShoppingDatabase(this)
        val repository = ShoppingRepository(db)
        val factory = ShoppingViewModelFactory(repository)
        val viewModel = ViewModelProviders.of(this, factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingItemAdapter(listOf(),viewModel)

        rv_shoppingitems.layoutManager = LinearLayoutManager(this)
        rv_shoppingitems.adapter = adapter

        viewModel.getAllShoppingItems().observe(this, Observer {
            // observing live Data in the Shopping DAO
            adapter.items= it
            adapter.notifyDataSetChanged()
        })


        // Dialog listerner to get Item from Dialog
        FAB.setOnClickListener {
            AddShoppingItemDialog(this,
            object : AddDialogListner{
                override fun onAddButton(item: ShoppingItem) {
                    viewModel.upsert(item)
                }

            }).show()
        }


    }
}