package com.testapp.klima.mvvm.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.testapp.klima.R
import com.testapp.klima.model.KlimaLocation
import com.testapp.klima.mvvm.NetworkScreenState
import com.testapp.klima.mvvm.forecast.ForecastFragment
import com.testapp.klima.utils.toBitmap
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.home_content.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(findViewById(R.id.toolbar))

        fab_menu.visibility = View.INVISIBLE

        viewModel.screenState.observe(::getLifecycle, ::updateUI)

        viewModel.loadLocation()

        initFABMenu()
    }

    private fun updateUI(networkScreenState: NetworkScreenState<KlimaLocation>) {
        when (networkScreenState) {
            is NetworkScreenState.Loading -> {
                loading_view.visibility = View.VISIBLE
                error_group.visibility = View.GONE
            }
            is NetworkScreenState.Error -> {
                loading_view.visibility = View.GONE
                error_group.visibility = View.VISIBLE
                error_button.setOnClickListener {
                    viewModel.loadLocation()
                }
            }
            is NetworkScreenState.Success -> {
                loading_view.visibility = View.GONE
                error_group.visibility = View.GONE
                val fragmentManager: FragmentManager = supportFragmentManager
                val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                val fragment = ForecastFragment.newInstance(
                    networkScreenState.data.lat,
                    networkScreenState.data.lon
                )
                fragmentTransaction.replace(R.id.fragmentContainer, fragment).commit()
            }
        }
    }

    private fun initFABMenu() {
        // TODO make this dinamic
        val info = mapOf(
            "Paris" to Pair(48.8032, 2.3511),
            "Tokyo" to Pair(35.6894, 139.692),
            "Londres" to Pair(51.5072, -0.1275),
            "Moscu" to Pair(55.7508, 37.6172)
        )

        val fabOptions = mutableListOf(
            Pair(fab_menu_first, fab_menu_first_text),
            Pair(fab_menu_second, fab_menu_second_text),
            Pair(fab_menu_third, fab_menu_third_text),
            Pair(fab_menu_fourth, fab_menu_fourth_text)
        )

        info.forEach { (key, value) ->
            if (fabOptions.isNotEmpty()) {
                val first = fabOptions.first()
                configureFab(first.first, first.second, key, value.first, value.second)
                fabOptions.removeFirst()
            }
        }
    }

    private fun configureFab(button: FloatingActionButton, text: TextView, name: String, lat: Double, long: Double) {
        text.visibility = View.VISIBLE
        button.visibility = View.VISIBLE
        text.text = name
        val textSize = resources.getDimension(R.dimen.fab_menu_text_size)
        button.setImageBitmap(name.take(1).toBitmap(textSize))
        button.setOnClickListener {
            val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            val fragment = ForecastFragment.newInstance(lat, long)
            fragmentTransaction.replace(R.id.fragmentContainer, fragment).commit()
            supportActionBar?.title = name
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}