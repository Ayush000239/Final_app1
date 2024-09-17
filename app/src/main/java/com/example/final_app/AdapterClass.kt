package com.example.final_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.final_app.model.DataClass

class AdapterClass(private val dataList: List<DataClass>) :
    RecyclerView.Adapter<AdapterClass.ViewHolderClass>() {

    var onItemClick: ((DataClass) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolderClass(view)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val dataItem = dataList[position]

        // Set summary data (excluding description)
        holder.nameTextView.text = dataItem.name
        holder.cultureTextView.text = dataItem.culture
        holder.domainTextView.text = dataItem.domain
        holder.symbolTextView.text = dataItem.symbol
        holder.parentageTextView.text = dataItem.parentage

        // Handle item clicks
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(dataItem)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val cultureTextView: TextView = itemView.findViewById(R.id.cultureTextView)
        val domainTextView: TextView = itemView.findViewById(R.id.domainTextView)
        val symbolTextView: TextView = itemView.findViewById(R.id.symbolTextView)
        val parentageTextView: TextView = itemView.findViewById(R.id.parentageTextView)
    }
}
