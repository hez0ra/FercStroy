package com.example.fercstroy

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(val context: Context, val factory: SQLiteDatabase.CursorFactory?) : SQLiteOpenHelper(context, "app", factory, 1) {

    private val defaultAvatar = ImageHelper.compressImage(ImageHelper.getDefaultAvatar(context))

    companion object{
        // Таблица пользователей
        private const val TABLE_USERS = "users"
        private const val USER_ID = "id"
        private const val USER_FIO =  "fio"
        private const val USER_PHONE = "phone"
        private const val USER_EMAIL = "email"
        private const val USER_PASS = "pass"
        private const val USER_IMAGE = "image"

        // Таблица админов
        private const val TABLE_ADMINS = "admins"
        private const val ADMINS_ID = "id"
        private const val ADMINS_USER_ID = "user_id"
        private const val ADMINS_ATTACH_CREATE = "attach_create"
    }


    override fun onCreate(db: SQLiteDatabase?) {
        var query: String = """
            CREATE TABLE $TABLE_USERS (
            $USER_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $USER_FIO TEXT, 
            $USER_PHONE TEXT,
            $USER_EMAIL TEXT,
            $USER_PASS TEXT,
            $USER_IMAGE BLOB
            )
        """.trimMargin()
        db!!.execSQL(query)

        query= """
            CREATE TABLE $TABLE_ADMINS (
            $ADMINS_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $ADMINS_USER_ID INTEGER,
            $ADMINS_ATTACH_CREATE BOOLEAN,
            FOREIGN KEY ($ADMINS_USER_ID) REFERENCES $TABLE_USERS($USER_ID) ON DELETE CASCADE)
            """.trimIndent()
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_ADMINS")
        onCreate(db)
    }

    fun getUser(email: String, pass: String){
        val db = this.readableDatabase
        val result =
            db.rawQuery("SELECT * FROM $TABLE_USERS WHERE $USER_EMAIL = '$email' OR $USER_PHONE = '$email' AND $USER_PASS = '$pass'", null)
        if (result.moveToFirst()) {
            // Проверяем, существуют ли столбцы в курсоре
            val idIndex = result.getColumnIndex(USER_ID)
            val fioIndex = result.getColumnIndex(USER_FIO)
            val emailIndex = result.getColumnIndex(USER_EMAIL)
            val phoneIndex = result.getColumnIndex(USER_PHONE)
            val passIndex = result.getColumnIndex(USER_PASS)
            val imageIndex = result.getColumnIndex(USER_IMAGE)
            if (idIndex != -1 && fioIndex != -1 && phoneIndex != -1 && emailIndex != -1 && passIndex != -1 && imageIndex != -1) {
                // Если все столбцы найдены, создаем и возвращаем объект User
                val id = result.getInt(idIndex)
                val fio = result.getString(fioIndex)
                val phone = result.getString(phoneIndex)
                val userEmail = result.getString(emailIndex)
                val userPass = result.getString(passIndex)
                val image = result.getBlob(imageIndex)
                ActiveUser.setUser(User(fio, phone, userEmail, userPass, id, image))
                ActiveUser.setUserId(id)
                ActiveUser.setIsAdmin(adminCheck(id))
                if(adminCheck(id)) ActiveUser.setAttachToCreate(attachToCreateCheck(id))
            }
        }
    }

    fun addUser(user: User) {
        val values = ContentValues()
        values.put(USER_FIO, user.fio)
        values.put(USER_PHONE, user.phone)
        values.put(USER_EMAIL, user.email)
        values.put(USER_PASS, user.pass)
        values.put(USER_IMAGE, defaultAvatar)

        val db = this.writableDatabase
        db.insert(TABLE_USERS, null, values)
        db.close()
    }

    fun adminCheck(userID: Int): Boolean{
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_ADMINS WHERE $ADMINS_USER_ID = '$userID'", null)
        return cursor.moveToFirst()
    }

    fun attachToCreateCheck(userID: Int): Boolean{
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_ADMINS WHERE $ADMINS_USER_ID = '$userID'", null)
        return if(cursor.moveToFirst()){
            cursor.getInt(cursor.getColumnIndexOrThrow(ADMINS_ATTACH_CREATE)) != 0
        } else false
    }


}