package com.example.expensemanager

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import java.util.Date

class Passbook : AppCompatActivity() {

    lateinit var recyclerView : RecyclerView
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

    lateinit var adapter: PassbookAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_passbook)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        dateList = fileOperatorDate.readData(this)
        amountList = fileOperatorAmount.readData(this)
        noteList = fileOperatorNote.readData(this)
        typeList = fileOperatorType.readData(this)
        accountList = fileOperatorAccount.readData(this)
        remainingList = fileOperatorRemBal.readData(this)


        recyclerView = findViewById(R.id.recyclerViewPassbook)
        recyclerView.layoutManager = LinearLayoutManager(this@Passbook)


            adapter = PassbookAdapter(
                dateList,
                amountList,
                noteList,
                typeList,
                accountList,
                remainingList,
                this@Passbook
            )
            recyclerView.adapter = adapter


        }


    }