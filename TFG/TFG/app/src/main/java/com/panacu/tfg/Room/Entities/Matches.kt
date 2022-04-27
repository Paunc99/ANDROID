package com.panacu.tfg.Room.Entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.sql.Date
import kotlin.reflect.KClass

@Entity(tableName = "Encuentros")
data class Matches(
    @PrimaryKey
    val matchId: Int,
    val matchEquipoLocal: Int,
    val matchEquipoVisitante: Int,
    val matchResultado:String,
    val matchFecha:String
){

}
