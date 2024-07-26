package com.example.expensemanager

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class PassbookAdapter(

    var dateList: ArrayList<String>,
    var amountList: ArrayList<String>,
    var noteList: ArrayList<String>,
    var typeList: ArrayList<String>,
    var accountList: ArrayList<String>,
    var remainingList: ArrayList<String>,
    var context: Context) : RecyclerView.Adapter<PassbookAdapter.OptionViewHolder>() {

    var fileOperatorDate = FileOperationDate()
    var fileOperatorAmount = FileOperationAmount()
    var fileOperatorNote = FileOperationNote()
    var fileOperatorType = FileOperationType()
    var fileOperatorAccount = FileOperationAccount()
    var fileOperatorRemBal = FileOperationRemBal()


    class OptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textViewDate : TextView = itemView.findViewById(R.id.textViewDatePassbook)
        var textViewAmount : TextView = itemView.findViewById(R.id.textViewAmountPassbook)
        var textViewNote : TextView = itemView.findViewById(R.id.textViewNotePassbook)
        var textViewType : TextView = itemView.findViewById(R.id.textViewTypePassbook)
        var textViewAccount : TextView = itemView.findViewById(R.id.textViewAccountPassbook)
        var textViewRemaining : TextView = itemView.findViewById(R.id.textViewRemainingBalPassbook)
        var linearLayout : LinearLayout = itemView.findViewById(R.id.LinearLayoutPassbookDesign)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {

        val view : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.passbook_design,parent,false)
        return OptionViewHolder(view)

    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        holder.textViewDate.text = dateList.get(position)
        holder.textViewAmount.text = amountList.get(position)
        holder.textViewNote.text = noteList.get(position)
        holder.textViewType.text = typeList.get(position)
        holder.textViewAccount.text = accountList.get(position)
        holder.textViewRemaining.text = remainingList.get(position)
        holder.linearLayout.setOnClickListener(){
            var alert = AlertDialog.Builder(context)
            alert.setTitle("Delete")
            alert.setMessage("Do you want to delete this entry?")
            alert.setCancelable(false)
            alert.setNegativeButton("No",DialogInterface.OnClickListener{dialogInterface, i ->

                dialogInterface.cancel()

            })
            alert.setPositiveButton("Yes",DialogInterface.OnClickListener{dialogInterface, i ->

                dateList.removeAt(position)
                amountList.removeAt(position)
                noteList.removeAt(position)
                typeList.removeAt(position)
                accountList.removeAt(position)
                remainingList.removeAt(position)
                notifyDataSetChanged()
                fileOperatorDate.writeData(dateList,context.applicationContext)
                fileOperatorAmount.writeData(amountList,context.applicationContext)
                fileOperatorNote.writeData(noteList,context.applicationContext)
                fileOperatorType.writeData(typeList,context.applicationContext)
                fileOperatorAccount.writeData(accountList,context.applicationContext)
                fileOperatorRemBal.writeData(remainingList,context.applicationContext)

            })
            alert.create().show()
        }
    }

    override fun getItemCount():Int {

        return dateList.size

    }

}