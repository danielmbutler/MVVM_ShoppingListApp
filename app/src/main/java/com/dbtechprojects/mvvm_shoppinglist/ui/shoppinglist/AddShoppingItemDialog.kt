package com.dbtechprojects.mvvm_shoppinglist.ui.shoppinglist

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.dbtechprojects.mvvm_shoppinglist.R
import com.dbtechprojects.mvvm_shoppinglist.data.db.entities.ShoppingItem
import kotlinx.android.synthetic.main.dialog_add_shopping_item.*

class AddShoppingItemDialog(context: Context, var addDialogListner: AddDialogListner): AppCompatDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_shopping_item)

        tvAdd.setOnClickListener {
            val name = etName.text.toString()
            val amount = etAmount.text.toString()

            if(name.isEmpty() || amount.isEmpty()){
                Toast.makeText(context, "Please enter All Information", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                val item = ShoppingItem(name, amount.toInt())
                addDialogListner.onAddButton(item)
                dismiss()
            }
        }

        tvCancel.setOnClickListener {
            cancel()
        }
    }
}