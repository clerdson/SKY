package com.clerdsonjuca.sky.adapter


import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.clerdsonjuca.sky.R
import com.clerdsonjuca.sky.model.All
import com.clerdsonjuca.sky.ui.MainActivity2
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row.view.*
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class MyAdapter(): RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    private var myList = emptyList<All>()
    private var  click:Int = 0

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false))
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


         holder.itemView.imageV.load("${myList[position].image  }")

        holder.itemView.card.setOnClickListener {

            if (click == 0) {

                holder.itemView.card.animate().scaleX(1.35f).scaleY(1.35f)

            click++
            }else if (click ==1){
                holder.itemView.card.animate().scaleX(1.0f).scaleY(1.0f)
                click --;

            val intent = Intent(it.context , MainActivity2::class.java)
                intent.putExtra("name",myList[position].name)
                intent.putExtra("image",myList[position].image)
                it.context.startActivity(intent)



            }

        }
       //imageView = Picasso.with(context).load(myList[position].img)

        holder.itemView.textView.text = myList[position].name

    //        holder.itemView.cardViewRow.setOnClickListener {
//
//
//            val intent = Intent(it.context , MainActivity3::class.java)
//            intent.putExtra("pay",pago)
//            intent.putExtra("time",myList[position].time)
//            intent.putExtra("plate",myList[position].plate)
//            it.context.startActivity(intent)
//        }
    }

    fun setData(newList: List<All>){
        myList = newList
        notifyDataSetChanged()
    }
}