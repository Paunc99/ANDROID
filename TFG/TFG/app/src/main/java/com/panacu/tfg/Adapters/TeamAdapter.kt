package com.panacu.tfg.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.panacu.tfg.Adapters.TeamAdapter.TeamViewHolder
import com.panacu.tfg.ImageController
import com.panacu.tfg.R
import com.panacu.tfg.Room.Entities.Equipos

class TeamAdapter(val context: Context,val equipos: List<Equipos>) : RecyclerView.Adapter<TeamViewHolder>(){


        class TeamViewHolder(val context: Context,view: View):RecyclerView.ViewHolder(view){
            val tvNombreEquipo = view.findViewById<TextView>(R.id.tvNameEquipo)
            val tvCiudadEquipo = view.findViewById<TextView>(R.id.tvCiudadTeam)
            val ivLogoEquipo = view.findViewById<ImageView>(R.id.imageView)

            fun bind(equipos: Equipos){
                val imageUri = ImageController.getImageUri(context, equipos.teamId as Long)
                tvNombreEquipo.text = equipos.teamName
                tvCiudadEquipo.text = equipos.teamCity
                ivLogoEquipo.setImageURI(imageUri)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TeamViewHolder(context,layoutInflater.inflate(R.layout.item_equipos,parent,false))
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val item = equipos[position]
        holder.bind(item)
    }

    override fun getItemCount()= equipos.size
}