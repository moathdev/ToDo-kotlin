package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_task.*
import android.content.Intent

class AddTask : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        val actionBar = supportActionBar;

        actionBar!!.title = "Add new Task";

        actionBar.setDisplayHomeAsUpEnabled(true);

        val context = this;
        val home_page = Intent(this, MainActivity::class.java)

        cancelbtn.setOnClickListener {
            home_page.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(home_page)
        }

        addbtn.setOnClickListener {
            if (task_name.text.toString().isNotEmpty() && task_details.text.toString().isNotEmpty()) {
               var task = Task(task_name.text.toString(), task_details.text.toString())
               var db = DataBaseHandler(context)
                db.insertData(task)

                home_page.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(home_page)
            } else {
                Toast.makeText(context, "Please Fill All Data's", Toast.LENGTH_SHORT).show()
            }
        }
    }
}