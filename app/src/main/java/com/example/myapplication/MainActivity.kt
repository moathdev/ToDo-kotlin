package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.activity_add_task.view.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val context = this;

        val listview = findViewById<ListView>(R.id.main_listview)

        listview.adapter = ListTaskAdapter(context)

        new_task.setOnClickListener {
            val intent = Intent(this, AddTask::class.java)
            startActivity(intent)
        }

        main_listview.setOnItemClickListener { parent, view, position, id ->
                Intent(this, DetailTask::class.java).also {
                    val id = parent.getItemAtPosition(position) as Int;
                    it.putExtra("EXTRA_ID", id)
                    startActivity(it)
                }

            }
    }

    private class ListTaskAdapter(context: Context) : BaseAdapter() {

        val db = DataBaseHandler(context)
        val data = db.readData()
        private val mContext: Context;

        init {
            mContext = context
        }

        override fun getCount(): Int {
            return data.size;
        }

        override fun getItem(position: Int): Any {
            return data.get(position).id
        }

        override fun getItemId(position: Int): Long {
            return 5;
        }

        @SuppressLint("ViewHolder")
        override fun getView(position: Int, convertView: View?, ViewGroup: ViewGroup?): View {


            val layoutInflater = LayoutInflater.from(mContext)

            val list_item = layoutInflater.inflate(R.layout.list_task, ViewGroup, false)

            if (data.isNotEmpty()) {

                val task_name_text = list_item.findViewById<TextView>(R.id.task_name_text)
                val task_details_text = list_item.findViewById<TextView>(R.id.task_details_text)

                task_name_text.text = data.get(position).name
                task_details_text.text = data.get(position).details
            }

            return list_item;
        }

    }
}