package com.amebaownd.pikohan_nwiatori.todocardlist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.amebaownd.pikohan_nwiatori.todocardlist.Data.Event

class CardListAdapter(private val context: Context,
                      private val events:ArrayList<Event>): RecyclerView.Adapter<CardListAdapter.CardListViewHolder>(){
    private val inflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CardListViewHolder {
          val view = inflater.inflate(R.layout.card_layout,parent,false)
          return CardListViewHolder(view)
         }

    override fun getItemCount()=events.size

    override fun onBindViewHolder(viewHolder: CardListViewHolder, position: Int) {
            viewHolder.title.text = events[position].title
        viewHolder.date.text = events[position].date.toString()
        viewHolder.memo.text = events[position].memo
    }

    class CardListViewHolder(view:View):RecyclerView.ViewHolder(view){
        val title = view.findViewById<TextView>(R.id.title)!!
        val date = view.findViewById<TextView>(R.id.date)!!
        val memo =view.findViewById<TextView>(R.id.memo)!!
    }
}