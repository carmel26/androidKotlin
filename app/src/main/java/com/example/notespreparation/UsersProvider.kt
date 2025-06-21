package com.example.notespreparation

import android.content.*
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri

class UsersProvider : ContentProvider() {

    companion object {
        const val PROVIDER_NAME = "com.example.notespreparation.UserProvider"
        const val URL = "content://$PROVIDER_NAME/users"
        val CONTENT_URI: Uri = Uri.parse(URL)

        const val id = "id"
        const val name = "name"
        const val uriCode = 1
        val uriMatcher: UriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(PROVIDER_NAME, "users", uriCode)
            addURI(PROVIDER_NAME, "users/*", uriCode)
        }
        private val values: HashMap<String, String>? = null

        const val DATABASE_NAME = "EmpDB"
        const val TABLE_NAME = "Employees"
        const val DATABASE_VERSION = 1
        const val CREATE_DB_TABLE = "CREATE TABLE $TABLE_NAME (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL);"
    }

    private lateinit var db: SQLiteDatabase

    override fun onCreate(): Boolean {
        val context = context ?: return false
        val dbHelper = DatabaseHelper(context)
        db = dbHelper.writableDatabase
        return db != null
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?, selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        val qb = SQLiteQueryBuilder()
        qb.tables = TABLE_NAME
        when (uriMatcher.match(uri)) {
            uriCode -> qb.projectionMap = values
            else -> throw IllegalArgumentException("Unknown URI $uri")
        }
        val finalSortOrder = if (sortOrder.isNullOrEmpty()) id else sortOrder
        val c = qb.query(db, projection, selection, selectionArgs, null, null, finalSortOrder)
        c.setNotificationUri(context!!.contentResolver, uri)
        return c
    }

    override fun getType(uri: Uri): String {
        return when (uriMatcher.match(uri)) {
            uriCode -> "vnd.android.cursor.dir/users"
            else -> throw IllegalArgumentException("Unsupported URI: $uri")
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri {
        val rowID = db.insert(TABLE_NAME, "", values)
        if (rowID > 0) {
            val resultUri = ContentUris.withAppendedId(CONTENT_URI, rowID)
            context!!.contentResolver.notifyChange(resultUri, null)
            return resultUri
        }
        throw android.database.SQLException("Failed to add a record into $uri")
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        val count = when (uriMatcher.match(uri)) {
            uriCode -> db.update(TABLE_NAME, values, selection, selectionArgs)
            else -> throw IllegalArgumentException("Unknown URI $uri")
        }
        context!!.contentResolver.notifyChange(uri, null)
        return count
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        val count = when (uriMatcher.match(uri)) {
            uriCode -> db.delete(TABLE_NAME, selection, selectionArgs)
            else -> throw IllegalArgumentException("Unknown URI $uri")
        }
        context!!.contentResolver.notifyChange(uri, null)
        return count
    }

    private class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
        override fun onCreate(db: SQLiteDatabase) {
            db.execSQL(CREATE_DB_TABLE)
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
            onCreate(db)
        }
    }
}