package com.nestozo.enriq.apptragos.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nestozo.enriq.apptragos.AppDataBase
import com.nestozo.enriq.apptragos.R
import com.nestozo.enriq.apptragos.data.DataSourceImpl
import com.nestozo.enriq.apptragos.data.model.Drink
import com.nestozo.enriq.apptragos.data.model.DrinkEntity
import com.nestozo.enriq.apptragos.databinding.FragmentFavoritesBinding
import com.nestozo.enriq.apptragos.domain.RepoImpl
import com.nestozo.enriq.apptragos.ui.viewModel.MainViewModel
import com.nestozo.enriq.apptragos.ui.viewModel.ViewModelFactory
import com.nestozo.enriq.apptragos.vo.Resource

class FavoritesFragment : Fragment(), MainAdapter.OnDrinkClickListener {

    private val viewModel by activityViewModels<MainViewModel> {
        ViewModelFactory(
            RepoImpl(
                DataSourceImpl(
                    AppDataBase.getDatabase(requireActivity().applicationContext)
                )
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentFavoritesBinding.bind(view)
        setUpRecyclerView(binding)
        setUpObservers(binding)
    }

    private fun setUpObservers(binding: FragmentFavoritesBinding) {
        viewModel.getFavoritesDrinks().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    val lista = result.data.map {
                        Drink(it.id,it.imagen, it.nombre, it.descripcion, it.hasAlcohol)
                    }
                    Log.i("listado", lista.toString())
                    binding.apply {
                        recyclerViewFavoritesDrinks.adapter =
                            MainAdapter(requireContext(), lista, this@FavoritesFragment)
                    }
                }
                is Resource.Failure -> {

                }
            }
        })
    }

    private fun setUpRecyclerView(binding: FragmentFavoritesBinding) {
        binding.apply {
            recyclerViewFavoritesDrinks.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewFavoritesDrinks.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    override fun onDrinkClick(drink: Drink, position: Int) {
        viewModel.deleteDrink(drink)
        val adaptador = view?.findViewById<RecyclerView>(R.id.recyclerView_favorites_drinks)?.adapter
        adaptador?.notifyItemRemoved(position)
        adaptador?.notifyItemRangeRemoved(position,adaptador?.itemCount!!)
    }
}