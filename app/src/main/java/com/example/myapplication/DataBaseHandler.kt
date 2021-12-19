package com.example.myapplication


import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

const val DATABASE_NAME = "app_task"
const val TABLE_NAME = "task"
const val COL_NAME = "name"
const val COL_DETAILS = "details"
const val COL_ID = "id"

class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME + " VARCHAR(256)," +
                COL_DETAILS + " TEXT)"

        db?.execSQL(createTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Todo
    }

    fun insertData(task: Task) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_NAME, task.name)
        cv.put(COL_DETAILS, task.details)
        val result = db.insert(TABLE_NAME, null, cv)
        if (result == -1.toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Task Successfully Created", Toast.LENGTH_SHORT).show()
        }
    }

    fun getData(id: Int?): MutableList<Task> {
        var list: MutableList<Task> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from $TABLE_NAME where id = $id";

        val result = db.rawQuery(query, null)
        if(result.moveToFirst()) {
            var task = Task();
            task.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
            task.name = result.getString(result.getColumnIndex(COL_NAME))
            task.details = result.getString(result.getColumnIndex(COL_DETAILS))
            list.add(task)
        } else {
            var task = Task();
            task.id = 1
            task.name = "test";
            task.details = "test";
            list.add(task)
        }

        result.close()
        db.close()
        return list
    }
    fun deleteData(id: Int?){
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "$COL_ID=$id", null) > 0;

        Toast.makeText(context, "Task Successfully Deleted", Toast.LENGTH_SHORT).show()

        db.close()
    }

    fun readData(): MutableList<Task> {
        var list: MutableList<Task> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from $TABLE_NAME";
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                var task = Task();
                task.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                task.name = result.getString(result.getColumnIndex(COL_NAME))
                task.details = result.getString(result.getColumnIndex(COL_DETAILS))
                list.add(task)
            } while (result.moveToNext())
        }

        result.close()
        db.close()
        return list
    }


}