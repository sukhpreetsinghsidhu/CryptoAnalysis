package com.example.cryptoanalysis.ui.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.cryptoanalysis.data.model.Coin
import com.example.cryptoanalysis.R

class RecyclerAdapter(var DataSourse : ArrayList<Coin>, val context : Context):  RecyclerView.Adapter<ViewHolder>() {


    private lateinit var  ItemListener : onItemClickListener;
    interface onItemClickListener{
        fun onClickListener(position : Int)
    }


    fun setItemListener(Listener: onItemClickListener){
        this.ItemListener = Listener;
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent,false)
        return ViewHolder(view, ItemListener)
    }


//    ///********update recycler view after search
//    fun updateData(dataItem: ArrayList<Coin>){
//        DataSourse = dataItem
//        notifyDataSetChanged()
//    }
//


//    @SuppressLint("Range")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemVH = DataSourse[position]
        holder.currencyname.text = itemVH.name
        holder.currencyname.setTextColor( Color.parseColor(itemVH.color))
       holder.price.text = "$ " + itemVH.price.toString()
        holder.price.setTextColor( Color.parseColor(itemVH.color))

    //to load svg file
    holder.symbol.loadUrl(itemVH.iconUrl)

    }

    override fun getItemCount(): Int {
        return DataSourse.size;
    }

    fun setItem(list: java.util.ArrayList<Coin>) {
        this.DataSourse =   list
        notifyDataSetChanged()
    }

    fun ImageView.loadUrl(url: String) {

        val imageLoader = ImageLoader.Builder(this.context)
            .componentRegistry { add(SvgDecoder(this@loadUrl.context)) }
            .build()

        val request = ImageRequest.Builder(this.context)
            .crossfade(true)
            .crossfade(500)
           // .placeholder(R.drawable.placeholder)
           // .error(R.drawable.error)
            .data(url)
            .target(this)
            .build()

        imageLoader.enqueue(request)
    }


}
class ViewHolder(view: View, ItemListener: RecyclerAdapter.onItemClickListener) : RecyclerView.ViewHolder(view) {
    var currencyname: TextView = view.findViewById(R.id.currencyname)
    var symbol: ImageView = view.findViewById(R.id.imageView)
    var price: TextView = view.findViewById(R.id.price)

    init{
        itemView.setOnClickListener {
            ItemListener.onClickListener(adapterPosition)
        }


    }

}

abstract class onRightSwipeItem :  ItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return makeMovementFlags(0, ItemTouchHelper.RIGHT)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

}