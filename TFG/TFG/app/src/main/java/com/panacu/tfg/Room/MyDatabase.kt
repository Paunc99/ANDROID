package com.panacu.tfg.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.panacu.tfg.Room.Entities.Equipos
import com.panacu.tfg.Room.Entities.Jugadores
import com.panacu.tfg.Room.Entities.Matches

@Database(entities = [Equipos::class , Jugadores::class , Matches::class], version = 2)
abstract class MyDatabase:RoomDatabase() {
    abstract fun MyDao():MyDao

    companion object{
        private var instance:MyDao?=null
        fun getInstance(context: Context):MyDao{
            return instance?: Room.databaseBuilder(context,MyDatabase::class.java,"TFG-db").build().MyDao().also { instance = it }
        }
    }
}