package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_task.*
import kotlinx.android.synthetic.main.activity_detail_task.*
import kotlinx.android.synthetic.main.activity_detail_task.task_name

class DetailTask : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_task)

        val actionBar = supportActionBar;

        val home_page = Intent(this, MainActivity::class.java)

        actionBar!!.title = "Detail Task";

        actionBar.setDisplayHomeAsUpEnabled(true);

        val id = intent.getIntExtra("EXTRA_ID", 0);

        val db = DataBaseHandler(this)
        val data = db.getData(id)

        task_name.text = data.get(0).name;

        task_detail.text = data.get(0).details;


        delete.setOnClickListener {
            db.deleteData(id)
            home_page.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(home_page)
        }

        back.setOnClickListener {
            home_page.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(home_page)
        }
    }
}
