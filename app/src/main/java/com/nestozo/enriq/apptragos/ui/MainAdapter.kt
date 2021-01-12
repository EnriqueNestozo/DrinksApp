package com.nestozo.enriq.apptragos.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nestozo.enriq.apptragos.R
import com.nestozo.enriq.apptragos.base.BaseViewHolder
import com.nestozo.enriq.apptragos.data.model.Drink
import com.nestozo.enriq.apptragos.databinding.DrinkItemBinding

class MainAdapter(private val context: Context, private val drinkList: List<Drink>, private val itemClickListener: OnDrinkClickListener) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnDrinkClickListener{
        fun onDrinkClick(drink: Drink)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        //Retorna una vista inflada
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.drink_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return drinkList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(drinkList[position], position)
        }
    }

    inner class MainViewHolder(itemView: View) : BaseViewHolder<Drink>(itemView) {
        private val binding = DrinkItemBinding.bind(itemView)

        override fun bind(item: Drink, position: Int) {
            Glide.with(context).load(item.imagen).centerCrop().into(binding.imgDrink)
            binding.textTitulo.text = item.nombre
            binding.textDescripcion.text = item.descripcion
            itemView.setOnClickListener {
                itemClickListener.onDrinkClick(item)
            }
        }

    }

}