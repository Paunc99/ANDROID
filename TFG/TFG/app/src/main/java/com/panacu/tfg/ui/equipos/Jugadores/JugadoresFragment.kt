package com.panacu.tfg.ui.equipos.Jugadores

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.panacu.tfg.R

class JugadoresFragment : Fragment() {

    companion object {
        fun newInstance() = JugadoresFragment()
    }

    private lateinit var viewModel: JugadoresViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.jugadores_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(JugadoresViewModel::class.java)
        // TODO: Use the ViewModel
    }

}