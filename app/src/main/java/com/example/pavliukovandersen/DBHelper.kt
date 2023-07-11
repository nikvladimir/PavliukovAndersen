package com.example.pavliukovandersen

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


private const val dbName = Constants.DB_NAME
private const val DBVersion = 1

class DBHelper(context: Context) :
    SQLiteOpenHelper(context, dbName, null, DBVersion) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(Constants.CREATE_PL_TABLE)
        db?.execSQL(Constants.FILLING_THE_PL_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(Constants.DROP_PL_TABLE)
        onCreate(db)
    }

    fun gettext(): Cursor? {
        val db = this.writableDatabase
        return db.rawQuery(Constants.SELECT_ALL_FROM_PL, null)
    }
}
