package com.panacu.tfg.ui.equipos

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.panacu.tfg.Adapters.TeamAdapter
import com.panacu.tfg.ImageController
import com.panacu.tfg.R
import com.panacu.tfg.Room.Entities.Equipos
import com.panacu.tfg.databinding.FragmentEquiposBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class EquiposdFragment : Fragment() {

    private var _binding : FragmentEquiposBinding? = null
    lateinit var recyclerView: RecyclerView
    var Equipos:MutableList<Equipos> = mutableListOf()
    private lateinit var equiposViewModel:EquiposViewModel
    lateinit var adapter :TeamAdapter

    private var imageUri: Uri? = null
    private val SELECT_ACTIVITY = 50
    var idEquipo: Long? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var fab:FloatingActionButton
    private lateinit var IvLogo :ImageView
    private lateinit var edNombreEquipos: EditText
    private lateinit var edCiudadEquipos: EditText
    private lateinit var btnAñadirEquipos: Button
    private lateinit var btnCancelar: Button
    private lateinit var btnSeleccionarImagen: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEquiposBinding.inflate(inflater, container, false)
        val root: View = binding.root
        fab = _binding!!.fabAddEquipo!!
        //variable para coger el viewmodel
        equiposViewModel = ViewModelProvider(this)[EquiposViewModel::class.java]
        //cogemos todos los elementos de la bbdd?
        equiposViewModel.getAllTeams()
        val builder = AlertDialog.Builder(context)
        val inflater = requireActivity().layoutInflater
        builder.setView(inflater.inflate(R.layout.dialogo_equipo,null))
        var dialog : AlertDialog = builder.create()
        IvLogo = dialog.findViewById(R.id.imageView)
        edNombreEquipos = dialog.findViewById(R.id.edNombreEquipo)
        edCiudadEquipos = dialog.findViewById(R.id.edCiudadEquipo)
        btnSeleccionarImagen = dialog.findViewById(R.id.btnLogo)
        btnCancelar = dialog.findViewById(R.id.btnCancelAddTeam)
        btnAñadirEquipos = dialog.findViewById(R.id.btnAddTeam)

        fab.setOnClickListener(View.OnClickListener {
            dialogoAddEquipo(dialog)
        })

        equiposViewModel.equiposListLD.observe(viewLifecycleOwner){
            Equipos.clear()
            Equipos.addAll(it)
            recyclerView.adapter?.notifyDataSetChanged()
        }


        //Observadores del viewModel



        setUpRecyclerView()
        return root
    }


    fun setUpRecyclerView(){
        adapter = TeamAdapter (requireContext(),Equipos)
        recyclerView = binding.rvEquipos!!
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when {
            requestCode == SELECT_ACTIVITY && resultCode == Activity.RESULT_OK -> {
                imageUri = data!!.data

                IvLogo.setImageURI(imageUri)
            }
        }
    }
    fun dialogoAddEquipo(dialog: Dialog){
        //DIALOGO PARA INGRESAR EQUIPOS
        dialog.show()

        btnAñadirEquipos.setOnClickListener(View.OnClickListener {

            val equipo = Equipos(
                teamName = edNombreEquipos.text.toString(),
                teamCity = edCiudadEquipos.text.toString(),
                teamLogo = R.drawable.iconoequipo
            )
            if(idEquipo!=null){
                CoroutineScope(Dispatchers.IO).launch {
                    equipo.teamId=idEquipo as Int
                    equiposViewModel.equipoDao.updateEquipo(equipo)

                    imageUri?.let {
                        val intent = Intent()
                        intent.data = it
                        intent.extras?.let { it1 -> setFragmentResult("EQUIPO", it1) }
                        ImageController.saveImage(requireContext(),idEquipo!!,it)
                    }

                }
            }else{
                CoroutineScope(Dispatchers.IO).launch {
                    val id = equiposViewModel.addEquipo(equipo)
                    imageUri?.let {
                        ImageController.saveImage(requireContext(),id,it)
                    }
                }
            }

        })
        btnCancelar.setOnClickListener(View.OnClickListener {
            dialog.cancel()
        })
        btnSeleccionarImagen.setOnClickListener(View.OnClickListener {
            ImageController.selectPhotoFromGallery(requireActivity(), SELECT_ACTIVITY)
        })
    }
}