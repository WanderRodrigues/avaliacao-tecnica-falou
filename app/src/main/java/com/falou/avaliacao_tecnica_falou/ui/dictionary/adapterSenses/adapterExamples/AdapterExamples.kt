package com.falou.avaliacao_tecnica_falou.ui.dictionary.adapterSenses.adapterExamples

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.falou.avaliacao_tecnica_falou.R
import com.falou.avaliacao_tecnica_falou.data.model.Examples
import com.falou.avaliacao_tecnica_falou.data.model.Senses

class AdapterExamples (private val listExamples: List<Examples?>?) : RecyclerView.Adapter<ExamplesViewHolder>() {

    override fun onBindViewHolder(holder: ExamplesViewHolder, position: Int) {
        if (listExamples !=null){
            val model = listExamples[position]
            holder.txtExamples.text = "• "  + model?.text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamplesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ExamplesViewHolder(inflater.inflate(R.layout.item_examples, parent, false))
    }

    override fun getItemCount(): Int {

        return listExamples?.size ?: 0
    }
}