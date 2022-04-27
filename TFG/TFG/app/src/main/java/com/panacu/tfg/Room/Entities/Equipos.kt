package com.panacu.tfg.Room.Entities

import android.widget.ImageView
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Teams")
data class Equipos(
    @PrimaryKey(autoGenerate = true)
    var teamId : Int = 0,
    var teamName: String = "",
    var teamCity: String = "",
    var teamLogo: Int = 0
){

}
