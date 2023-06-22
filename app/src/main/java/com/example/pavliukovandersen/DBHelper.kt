package com.example.pavliukovandersen

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


private const val tableName = Constants.PL_TABLE_NAME

class DBHelper(context: Context) : SQLiteOpenHelper(
    context, tableName, null, 1
) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(Constants.CREATE_PL_TABLE)
        db?.execSQL(Constants.FILL_THE_PL_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(Constants.DROP_PL_TABLE)
    }

    fun gettext(): Cursor? {
        val db = this.writableDatabase
        return db.rawQuery(Constants.SELECT_ALL_FROM_PL, null)
    }
}
