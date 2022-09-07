package com.dmitry.notes.screens.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dmitry.notes.R
import com.dmitry.notes.model.AppNote

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainHolder>() {

    private var mListNotes = emptyList<AppNote>()

    class MainHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameNote: TextView = view.findViewById(R.id.item_note_name)
        val textNote: TextView = view.findViewById(R.id.item_note_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return MainHolder(view)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.nameNote.text = mListNotes[position].text
        holder.textNote.text = mListNotes[position].text
    }

    override fun getItemCount(): Int = mListNotes.size

    override fun onViewAttachedToWindow(holder: MainHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener{
            MainFragment.click(mListNotes[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: MainHolder) {
        holder.itemView.setOnClickListener(null)
        super.onViewDetachedFromWindow(holder)
    }

    fun setList(list: List<AppNote>) {
        mListNotes = list
        notifyDataSetChanged()
    }
}