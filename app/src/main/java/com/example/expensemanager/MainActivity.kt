package com.example.expensemanager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    lateinit var AddBal : Button
    lateinit var Withdraw : Button
    lateinit var Passbook : Button
    lateinit var recyclerView : RecyclerView
    lateinit var cardView : CardView

    var optionsList = ArrayList<String>()
    var iconList = ArrayList<Int>()

    lateinit var adapter: OptionAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

        optionsList.add("Add Money")
        optionsList.add("Withdraw Money")
        optionsList.add("Passbook")

        iconList.add(R.drawable.addm)
        iconList.add(R.drawable.with)
        iconList.add(R.drawable.pass)

        adapter = OptionAdapter(optionsList,iconList,this@MainActivity)

        recyclerView.adapter = adapter


    }
}