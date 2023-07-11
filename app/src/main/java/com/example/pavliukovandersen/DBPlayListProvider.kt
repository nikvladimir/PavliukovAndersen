package com.example.pavliukovandersen

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import com.example.pavliukovandersen.Constants.Companion.PL_TABLE_NAME

class DBPlayListProvider : ContentProvider() {

    private lateinit var db: SQLiteDatabase

    override fun onCreate(): Boolean {
        val dbHelper = DBHelper(context!!)
        db = dbHelper.writableDatabase
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,  // какие поля вернутся после запроса
        selection: String?,              // фильтры согласно которым будет запрос
        selectionArgs: Array<out String>?, // перечада параметров ыудусешщты кот д.б. использованы
        sortOrder: String?               // по какому ключу будет сортировка
    ): Cursor {
        return db.query(
            PL_TABLE_NAME, projection, selection,
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
        val id = db.insert(PL_TABLE_NAME, null, values)
        context?.contentResolver?.notifyChange(uri, null)
        return ContentUris.withAppendedId(uri, id)
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        val count = db.update(PL_TABLE_NAME, values, selection, selectionArgs)
        context?.contentResolver?.notifyChange(uri, null)
        return count
    }

    override fun delete(
        uri: Uri,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        val count = db.delete(PL_TABLE_NAME, selection, selectionArgs)
        context?.contentResolver?.notifyChange(uri, null)
        return count
    }

}