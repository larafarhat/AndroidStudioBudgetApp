package com.example.budgetapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView


class RecordItemFragment : RecyclerView.Adapter<RecordItemFragment.RecordItemViewHolder>() {




    var data = listOf<Record>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun getItemCount(): Int {
        return data.size
    }


    class RecordItemViewHolder(val rootView: CardView) : RecyclerView.ViewHolder(rootView) {
        val recordAmount = rootView.findViewById<TextView>(R.id.record_amount)
        val recordDate = rootView.findViewById<TextView>(R.id.record_date)
        val recordType = rootView.findViewById<TextView>(R.id.record_type)
        val recordNote = rootView.findViewById<TextView>(R.id.record_note)
        companion object {
            fun inflateFrom(parent: ViewGroup): RecordItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.fragment_record_item, parent, false) as CardView
                return RecordItemViewHolder(view)
            }
        }

        fun bind(item: Record) {
            recordAmount.text = item.recordAmount
          recordDate.text= item.recordDate
            recordType.text = item.recordType
            recordNote.text = item.recordNote

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordItemViewHolder {
        return RecordItemViewHolder.inflateFrom(parent)
    }


    override fun onBindViewHolder(holder: RecordItemViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }


}