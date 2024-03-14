package com.example.servicemusicplayer


import com.example.common.Constants

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri

class DBPlayListProvider : ContentProvider() {

    private lateinit var db: SQLiteDatabase

    override fun onCreate(): Boolean {
        val dbHelper = DBHelper(context!!)
        db = dbHelper.writableDatabase
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,  // which fields will be returned after the request
        selection: String?,              // filters according to which the request will be made
        selectionArgs: Array<out String>?, // passing the selections parameters to be used
        sortOrder: String?               // by which key will the sorting be done
    ): Cursor {
        return db.query(
            Constants.PL_TABLE_NAME, projection, selection,
            selectionArgs, null, null, sortOrder
        )
    }

    override fun getType(uri: Uri): String {
        return "vnd.android.cursor.item/vnd.com.example.provider.books"
    }

    override fun insert(
        uri: Uri,
        values: ContentValues?
    ): Uri {
        val id = db.insert(Constants.PL_TABLE_NAME, null, values)
        context?.contentResolver?.notifyChange(uri, null)
        return ContentUris.withAppendedId(uri, id)
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        val count = db.update(Constants.PL_TABLE_NAME, values, selection, selectionArgs)
        context?.contentResolver?.notifyChange(uri, null)
        return count
    }

    override fun delete(
        uri: Uri,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        val count = db.delete(Constants.PL_TABLE_NAME, selection, selectionArgs)
        context?.contentResolver?.notifyChange(uri, null)
        return count
    }

}