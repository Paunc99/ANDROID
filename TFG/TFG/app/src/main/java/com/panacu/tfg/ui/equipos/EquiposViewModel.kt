package com.panacu.tfg.ui.equipos

import android.app.Activity
import android.app.AlertDialog
import android.app.Application
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.panacu.tfg.ImageController
import com.panacu.tfg.R
import com.panacu.tfg.Room.Entities.Equipos
import com.panacu.tfg.Room.MyDao
import com.panacu.tfg.Room.MyDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EquiposViewModel(application: Application):AndroidViewModel(application) {
    //variables locales
    val context = application
    //instanciamos la BBDD
    var equipoDao:MyDao = MyDatabase.getInstance(context)



    //Mutables para controlar los distintos cambios en la app
    val equiposListLD: MutableLiveData<MutableList<Equipos>> = MutableLiveData()
    val updateEquiposLD:MutableLiveData<Equipos?> = MutableLiveData()
    val deleteEquiposLD:MutableLiveData<Int> = MutableLiveData()
    val insertEquiposLD:MutableLiveData<Equipos> = MutableLiveData()


    fun getAllTeams() {
        CoroutineScope(Dispatchers.IO).launch {
            equiposListLD.postValue(equipoDao.getAllTeams())
        }
    }
    /**
     * funcion para añadir un producto a la BBDD
     */
    fun addEquipo(equipo: Equipos):Long{
        var id:Long = 0
        CoroutineScope(Dispatchers.IO).launch {
             id = equipoDao.addEquipo(equipo)

        }
        return id
    }


}