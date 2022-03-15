package com.falou.avaliacao_tecnica_falou.ui.dictionary.adapterWordAtributes

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.falou.avaliacao_tecnica_falou.R
import com.falou.avaliacao_tecnica_falou.model.WordAtributes
import com.falou.avaliacao_tecnica_falou.ui.dictionary.adapterWordAtributes.adapterExamples.AdapterExamples

class AdapterWordAtributes (private val listSenses: List<WordAtributes>, private val context: Context) : RecyclerView.Adapter<WordAtributesViewHolder>() {

    override fun onBindViewHolder(holder: WordAtributesViewHolder, position: Int) {
        val model = listSenses[position]
        holder.txtDefinitions.text = (position +1 ).toString() + ") ${model.definition}"

            holder.recyclerExamples.layoutManager = LinearLayoutManager(context)
            holder.recyclerExamples.adapter = AdapterExamples(model.examples)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordAtributesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return WordAtributesViewHolder(inflater.inflate(R.layout.item_dictionary, parent, false))
    }

    override fun getItemCount(): Int {
        return listSenses.size
    }
}