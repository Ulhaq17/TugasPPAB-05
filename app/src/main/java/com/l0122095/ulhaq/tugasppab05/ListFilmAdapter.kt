package com.l0122095.ulhaq.tugasppab05

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListFilmAdapter(private val listFilm: ArrayList<Film>) : RecyclerView.Adapter<ListFilmAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvYear: TextView = itemView.findViewById(R.id.tv_item_year)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_film, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listFilm.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, year, img) = listFilm[position]
        holder.imgPhoto.setImageResource(img)
        holder.tvName.text = name
        holder.tvYear.text = year
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listFilm[holder.adapterPosition]) }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Film)
    }
}