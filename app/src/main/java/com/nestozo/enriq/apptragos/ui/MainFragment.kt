package com.nestozo.enriq.apptragos.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nestozo.enriq.apptragos.R
import com.nestozo.enriq.apptragos.data.DataSource
import com.nestozo.enriq.apptragos.data.model.Drink
import com.nestozo.enriq.apptragos.databinding.FragmentMainBinding
import com.nestozo.enriq.apptragos.domain.RepoImpl
import com.nestozo.enriq.apptragos.ui.viewModel.MainViewModel
import com.nestozo.enriq.apptragos.ui.viewModel.ViewModelFactory
import com.nestozo.enriq.apptragos.vo.Resource

class MainFragment : Fragment(), MainAdapter.OnDrinkClickListener {

    private val viewModel by viewModels<MainViewModel>{ ViewModelFactory(RepoImpl(DataSource())) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    /*
    Infla el layout segun el xml
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMainBinding.bind(view)
        setUpRecyclerView(binding)
        setUpSearchView(binding)
        setUpObservers(binding)
    }

    private fun setUpObservers(binding: FragmentMainBinding){
        viewModel.fetchDrinkList.observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success-> {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerViewDrinks.adapter = MainAdapter(requireContext(),result.data,this)
                }
                is Resource.Failure<*> -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(),"Ocurrió un error al traer los datos ${result.exception}",Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setUpRecyclerView(binding: FragmentMainBinding){

        binding.apply {
            recyclerViewDrinks.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            recyclerViewDrinks.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
        }
    }

    override fun onDrinkClick(drink: Drink) {
        val bundle = Bundle()
        bundle.putParcelable("drink",drink)
        findNavController().navigate(R.id.action_mainFragment_to_detailFragment,bundle)
    }

    private fun setUpSearchView(binding: FragmentMainBinding){
        binding.apply {
            searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    viewModel.setDrink(query!!)
                    return false
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    return false;
                }

            })
        }
    }

}