package com.example.expensemanager

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import java.util.Date

class WithdrawMoney : AppCompatActivity() {

    lateinit var radioButtonCash : RadioButton
    lateinit var radioButtonBank : RadioButton
    lateinit var withdrawAmount : EditText
    lateinit var note : EditText
    lateinit var balanceInfo : TextView
    lateinit var balUpdateInfo : TextView
    lateinit var submit : Button
    var fileOperatorDate = FileOperationDate()
    var fileOperatorAmount = FileOperationAmount()
    var fileOperatorNote = FileOperationNote()
    var fileOperatorType = FileOperationType()
    var fileOperatorAccount = FileOperationAccount()
    var fileOperatorRemBal = FileOperationRemBal()
    var dateList = ArrayList<String>()
    var amountList = ArrayList<String>()
    var noteList = ArrayList<String>()
    var typeList = ArrayList<String>()
    var accountList = ArrayList<String>()
    var remainingList = ArrayList<String>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_withdraw_money)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        dateList = fileOperatorDate.readData(applicationContext)
        amountList = fileOperatorAmount.readData(applicationContext)
        noteList = fileOperatorNote.readData(applicationContext)
        typeList = fileOperatorType.readData(applicationContext)
        accountList = fileOperatorAccount.readData(applicationContext)
        remainingList = fileOperatorRemBal.readData(applicationContext)

        radioButtonBank = findViewById(R.id.radioButtonBankWithdraw)
        radioButtonCash = findViewById(R.id.radioButtonCashWithdraw)
        withdrawAmount = findViewById(R.id.editTextAmountWithdraw)
        note = findViewById(R.id.editTextNoteWithdraw)
        submit = findViewById(R.id.buttonSubmitWithdraw)
        balanceInfo = findViewById(R.id.textViewViewBalanceWithraw)
        balUpdateInfo = findViewById(R.id.textViewWithrawnBalance)

        var cashBalance : Int = 0
        var bankBalance : Double = 0.0

        if (accountList.size != 0 && remainingList.size !=0){

            if (accountList.last()=="Cash"){
                cashBalance = remainingList.last().toInt()
                var count = 0
                for (i in accountList.reversed()){
                    count++
                    if(i=="Bank"){
                        var accountsize :Int = accountList.size
                        var bankindex: Int = accountsize-count
                        bankBalance = remainingList.get(bankindex).toDouble()
                        break
                    }
                }
            }
            else if (accountList.last()=="Bank"){

                bankBalance = remainingList.last().toDouble()
                var count = 0
                for (i in accountList.reversed()){
                    count++
                    if(i=="Cash"){
                        var accountsize :Int = accountList.size
                        var cashindex: Int = accountsize-count
                        cashBalance = remainingList.get(cashindex).toInt()
                        break
                    }
                }

            }
            else{
                null
            }

        }


        balanceInfo.text = "Your Current Cash Balance : $cashBalance \n" +
                "Your Current Bank Balance : $bankBalance"

        submit.setOnClickListener(){
            if (radioButtonCash.isChecked){
                accountList.add("Cash")
                typeList.add("Debit")
                fileOperatorAccount.writeData(accountList,applicationContext)
                fileOperatorType.writeData(typeList,applicationContext)
                cashBalance -= withdrawAmount.text.toString().toInt()
                balanceInfo.text = "Your Updated Cash Balance : $cashBalance \n" +
                        "Your Current Bank Balance : $bankBalance"
                balUpdateInfo.text = "₹${withdrawAmount.text.toString().toInt()} is successfully withdrawn from  your Cash Balance"
                balUpdateInfo.isVisible = true
                remainingList.add(cashBalance.toString())
                fileOperatorRemBal.writeData(remainingList,applicationContext)
                amountList.add(withdrawAmount.text.toString())
                fileOperatorAmount.writeData(amountList,applicationContext)
                noteList.add(note.text.toString())
                fileOperatorNote.writeData(noteList,applicationContext)
                dateList.add(Date().toString())
                fileOperatorDate.writeData(dateList,applicationContext)
            }
            else if (radioButtonBank.isChecked){
                accountList.add("Bank")
                typeList.add("Debit")
                fileOperatorAccount.writeData(accountList,applicationContext)
                fileOperatorType.writeData(typeList,applicationContext)
                bankBalance -= withdrawAmount.text.toString().toDouble()
                balanceInfo.text = "Your Current Cash Balance : $cashBalance \n" +
                        "Your Updated Bank Balance : $bankBalance"
                balUpdateInfo.text = "₹${withdrawAmount.text.toString().toDouble()} is successfully withdrawn from your Bank Balance"
                balUpdateInfo.isVisible = true
                remainingList.add(bankBalance.toString())
                fileOperatorRemBal.writeData(remainingList,applicationContext)
                amountList.add(withdrawAmount.text.toString())
                fileOperatorAmount.writeData(amountList,applicationContext)
                noteList.add(note.text.toString())
                fileOperatorNote.writeData(noteList,applicationContext)
                dateList.add(Date().toString())
                fileOperatorDate.writeData(dateList,applicationContext)
            }
            else{
                null
            }
        }



    }
}