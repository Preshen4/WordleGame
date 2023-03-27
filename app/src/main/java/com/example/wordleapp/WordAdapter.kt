package com.example.wordleapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WordAdapter(private val wordList: Array<String>) :
    RecyclerView.Adapter<WordAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
     val textList: TextView

    init {
        textList = view.findViewById(R.id.textList)
    }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = wordList[position]
        holder.textList.text = currentItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.worditem, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return wordList.size
    }

    init {

    }

}