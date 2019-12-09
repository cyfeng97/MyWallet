package com.example.mywallet

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(InOutLog::class), version = 1)
abstract class inOutLogDatebase : RoomDatabase(){
    //Ensure only one instance of the database is created

    abstract fun inOutLogDao() : inOutLogDao

    companion object{
        @Volatile
        private var INSTANCE: inOutLogDatebase? = null

        fun getDatabase(context: Context): inOutLogDatebase{
            val tempDB = INSTANCE
            if(tempDB != null){
                return tempDB
            }


            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    inOutLogDatebase::class.java,
                    "inoutlog_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}