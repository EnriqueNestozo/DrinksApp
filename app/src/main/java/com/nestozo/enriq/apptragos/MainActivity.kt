package com.nestozo.enriq.apptragos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {

    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Este es el controlador de navegacion, es el fragment en el mainActivity, donde se inflan todos los fragmentos
        navController = findNavController(R.id.nav_host_fragment)
        //Habilita el actionBar, para que cuando yo navegue salga la flecha para regresar
        NavigationUI.setupActionBarWithNavController(this,navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}