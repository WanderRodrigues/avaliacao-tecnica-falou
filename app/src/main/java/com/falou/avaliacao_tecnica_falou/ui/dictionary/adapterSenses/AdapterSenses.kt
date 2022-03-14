package com.falou.avaliacao_tecnica_falou.ui.dictionary.adapterSenses

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.falou.avaliacao_tecnica_falou.R
import com.falou.avaliacao_tecnica_falou.data.model.Senses
import com.falou.avaliacao_tecnica_falou.ui.dictionary.adapterSenses.adapterExamples.AdapterExamples

class AdapterSenses (private val listSenses: List<Senses>, private val context: Context) : RecyclerView.Adapter<SensesViewHolder>() {

    override fun onBindViewHolder(holder: SensesViewHolder, position: Int) {
        val model = listSenses[position]
        if (model.definitions!=null && model.examples !=null) {
            model.definitions.forEach {
                holder.txtDefinitions.text = (position +1).toString() + ") $it"
            }

            holder.recyclerExamples.layoutManager = LinearLayoutManager(context)
            holder.recyclerExamples.adapter = AdapterExamples(model.examples)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SensesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SensesViewHolder(inflater.inflate(R.layout.item_dictionary, parent, false))
    }

    override fun getItemCount(): Int {
        return listSenses.size
    }
}