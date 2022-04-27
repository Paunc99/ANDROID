package com.panacu.tfg.Room.Entities

import android.media.Image
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Players")
data class Jugadores(
    //dejamos que el sistema gestione la primary key de la tabla
    @PrimaryKey(autoGenerate = false)
    val idEquipos: Int,
    val playerNumber: Int,
    val playerName: String,
    val playerImage: String,
    val playerAge: Int
){

}
