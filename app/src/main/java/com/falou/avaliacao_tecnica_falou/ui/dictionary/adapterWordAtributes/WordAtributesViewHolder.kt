package com.falou.avaliacao_tecnica_falou.ui.dictionary.adapterWordAtributes

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.falou.avaliacao_tecnica_falou.R


class WordAtributesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var txtDefinitions : TextView = itemView.findViewById(R.id.txtDefinitions)
    var recyclerExamples : RecyclerView = itemView.findViewById(R.id.recyclerExamples)
}