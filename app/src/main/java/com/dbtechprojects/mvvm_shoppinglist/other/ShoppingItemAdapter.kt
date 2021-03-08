package com.dbtechprojects.mvvm_shoppinglist.other

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dbtechprojects.mvvm_shoppinglist.R
import com.dbtechprojects.mvvm_shoppinglist.data.db.entities.ShoppingItem
import com.dbtechprojects.mvvm_shoppinglist.ui.shoppinglist.ShoppingViewModel
import kotlinx.android.synthetic.main.shopping_item.view.*

class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
): RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    inner class ShoppingViewHolder(itemview: View): RecyclerView.ViewHolder(itemview)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent,false)
        return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currentShoppingItem = items[position]

        holder.itemView.tvName.text = currentShoppingItem.name
        holder.itemView.tvAmount.text = "${currentShoppingItem.amount}"

        holder.itemView.ivDelete.setOnClickListener {
            viewModel.delete(currentShoppingItem)
        }

        holder.itemView.ivPlus.setOnClickListener {
            currentShoppingItem.amount++
            viewModel.upsert(currentShoppingItem)
        }

        holder.itemView.ivMinus.setOnClickListener {
            if(currentShoppingItem.amount > 0){
                currentShoppingItem.amount--
                Log.d("currentamount", currentShoppingItem.amount.toString())
                viewModel.upsert(currentShoppingItem)
            }
        }
    }

    override fun getItemCount(): Int {
       return items.size
    }
}