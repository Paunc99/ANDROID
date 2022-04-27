package com.panacu.tfg.Room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.panacu.tfg.Room.Entities.Equipos
import com.panacu.tfg.Room.Entities.Jugadores
import com.panacu.tfg.Room.Entities.Matches

@Dao
interface MyDao {

    @Query("SELECT * FROM Teams")
    fun getAllTeams(): MutableList<Equipos>
    @Query("SELECT * FROM Players")
    suspend fun getAllPlayers(): MutableList<Jugadores>
    @Query("SELECT * FROM Encuentros")
    suspend fun getAllGames(): MutableList<Matches>

    //añadimos un Equipo
    @Insert
    fun addEquipo(equipos: Equipos):Long

    //select para coger un registro de equipo por su id
    @Query("SELECT * FROM Teams WHERE teamId LIKE :id")
    suspend fun getEquipoId(id: Int): Equipos

    //actualizacion de los registros
    @Update
    suspend fun updateEquipo(equipos: Equipos):Int

    //añadimos un Jugador
    @Insert
    suspend fun addJugador(jugador: Jugadores):Long



    //actualizacion de los registros
    @Update
    suspend fun updatePlayer(jugador: Jugadores):Int


    //borrado de los registros
    @Delete
    suspend fun borrarProducto(equipos: Equipos):Int
}