package com.nestozo.enriq.apptragos.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.nestozo.enriq.apptragos.AppDataBase
import com.nestozo.enriq.apptragos.R
import com.nestozo.enriq.apptragos.data.DataSourceImpl
import com.nestozo.enriq.apptragos.data.model.Drink
import com.nestozo.enriq.apptragos.data.model.DrinkEntity
import com.nestozo.enriq.apptragos.databinding.FragmentDetailBinding
import com.nestozo.enriq.apptragos.domain.RepoImpl
import com.nestozo.enriq.apptragos.ui.viewModel.MainViewModel
import com.nestozo.enriq.apptragos.ui.viewModel.ViewModelFactory

class DetailFragment : Fragment() {

    private val viewModel by activityViewModels<MainViewModel>{ ViewModelFactory(RepoImpl(DataSourceImpl(
        AppDataBase.getDatabase(requireActivity().applicationContext)))) }

    private lateinit var drink: Drink

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            drink = it.getParcelable("drink")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)
        binding.apply {
            Glide.with(requireContext()).load(drink.imagen).centerCrop().into(drinkImage)
            drinkTitle.text = drink.nombre
            drinkDescription.text = drink.descripcion
            hasAlcohol.text = drink.hasAlcohol
            btnSave.setOnClickListener {
                viewModel.saveDrink(DrinkEntity(drink.drinkId,drink.imagen,drink.nombre,drink.descripcion,drink.hasAlcohol))
                Toast.makeText(requireContext(),"Se guardó el trago en favoritos", Toast.LENGTH_LONG).show()
            }
        }
    }

}