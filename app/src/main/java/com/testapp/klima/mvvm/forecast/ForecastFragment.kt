package com.testapp.klima.mvvm.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.testapp.klima.R
import com.testapp.klima.model.ForecastData
import com.testapp.klima.model.ForecastResponse
import com.testapp.klima.mvvm.NetworkScreenState
import kotlinx.android.synthetic.main.fragment_forecast.*
import kotlinx.android.synthetic.main.home_content.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

/**
 * A simple [Fragment] subclass
 */
class ForecastFragment : Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<ForecastAdapter.ViewHolder>? = null

    private val lat: Double by lazy {
        arguments?.getDouble(LATITUDE) ?: 0.0
    }
    private val lon: Double by lazy {
        arguments?.getDouble(LONGITUDE) ?: 0.0
    }

    private val viewModel: ForecastViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forecast, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        viewModel.screenState.observe(::getLifecycle, ::updateUI)
        viewModel.loadForecast(lat, lon)
    }

    private fun updateUI(networkScreenState: NetworkScreenState<ForecastResponse>) {
        when (networkScreenState) {
            is NetworkScreenState.Loading -> {
                activity?.loading_view?.visibility = View.VISIBLE
                activity?.error_group?.visibility = View.GONE
            }
            is NetworkScreenState.Error -> {
                activity?.loading_view?.visibility = View.GONE
                activity?.error_group?.visibility = View.VISIBLE
                activity?.error_button?.setOnClickListener {
                    viewModel.loadForecast(lat, lon)
                }
            }
            is NetworkScreenState.Success -> {
                activity?.loading_view?.visibility = View.GONE
                activity?.error_group?.visibility = View.GONE
                val forecastDataList = mutableListOf<ForecastData>()
                // Add current weather info
                forecastDataList.add(
                    ForecastData(
                        "Today",
                        networkScreenState.data.current?.temp,
                        0.0,
                        0.0,
                        networkScreenState.data.current?.pressure,
                        networkScreenState.data.current?.humidity,
                        networkScreenState.data.current?.weather?.get(0)?.description!!,
                        networkScreenState.data.current.weather[0]?.icon!!,
                        networkScreenState.data.current.weather[0]?.main!!
                    )
                )

                val calendar = Calendar.getInstance()
                var currentDay = calendar[Calendar.DAY_OF_WEEK]
                for (daily in networkScreenState.data.daily!!) {
                    if (currentDay == 7) {
                        currentDay = 1
                    } else {
                        currentDay += 1
                    }
                    forecastDataList.add(
                        ForecastData(
                            getDayByInt(currentDay),
                            0.0, daily?.temp?.min, daily?.temp?.max,
                            daily?.pressure, daily?.humidity,
                            daily?.weather?.get(0)?.description!!,
                            daily?.weather[0]?.icon!!, daily?.weather[0]?.main!!
                        )
                    )
                }


                recycler_view.apply {
                    // set a LinearLayoutManager to handle Android
                    // RecyclerView behavior
                    layoutManager = LinearLayoutManager(activity)
                    // set the custom adapter to the RecyclerView
                    adapter =
                        ForecastAdapter(
                            forecastDataList
                        )
                }
            }
        }
    }

    private fun getDayByInt(day: Int): String {
        when (day) {
            Calendar.SUNDAY -> {
                return "Sunday"
            }
            Calendar.MONDAY -> {
                return "Monday"
            }
            Calendar.TUESDAY -> {
                return "Tuesday"
            }
            Calendar.WEDNESDAY -> {
                return "Wednesday"
            }
            Calendar.THURSDAY -> {
                return "Thursday"
            }
            Calendar.FRIDAY -> {
                return "Friday"
            }
            Calendar.SATURDAY -> {
                return "Saturday"
            }
        }
        // This never gonna happened
        return ""
    }

    companion object {

        private const val LATITUDE = "lat"
        private const val LONGITUDE = "lon"

        @JvmStatic
        fun newInstance(lat: Double, lon: Double) = ForecastFragment()
            .apply {
                arguments = Bundle().apply {
                    putDouble(LATITUDE, lat)
                    putDouble(LONGITUDE, lon)
                }
            }
    }
}