package com.example.expensemanager

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class OptionAdapter(
    var optionsList: ArrayList<String>,
    var iconList: ArrayList<Int>,
    var context: Context) : RecyclerView.Adapter<OptionAdapter.OptionViewHolder> () {


    class OptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var textViewOptionName : TextView = itemView.findViewById(R.id.textViewOptionName)
        var imageView : ImageView = itemView.findViewById(R.id.imageView)
        var cardView : CardView = itemView.findViewById(R.id.cardView)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {

        val view : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_design,parent,false)
        return OptionViewHolder(view)
    }

    override fun getItemCount(): Int {

        return optionsList.size

    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {

        holder.textViewOptionName.text = optionsList.get(position)
        holder.imageView.setImageResource(iconList.get(position))
        holder.cardView.setOnClickListener(){
            if (optionsList.get(position).equals("Add Money")){
                var intent = Intent(context,AddMoney::class.java)
                context.startActivity(intent)
            }
            else if (optionsList.get(position).equals("Withdraw Money")){
                var intent = Intent(context,WithdrawMoney::class.java)
                context.startActivity(intent)
            }
            else if (optionsList.get(position).equals("Passbook")){
                var intent = Intent(context,Passbook::class.java)
                context.startActivity(intent)
            }
            else{
                null
            }
        }

    }

}