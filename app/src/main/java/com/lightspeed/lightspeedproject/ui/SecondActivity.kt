package com.lightspeed.lightspeedproject.ui

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.lightspeed.lightspeedproject.adapter.LightspeedAdapter
import com.lightspeed.lightspeedproject.databinding.ActivityFirstBinding
import com.lightspeed.lightspeedproject.util.Constants
import com.lightspeed.lightspeedproject.viewmodel.SecondViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_second.*

@AndroidEntryPoint
class SecondActivity : AppCompatActivity() {

    companion object {
        var onlineStatus = "online"
    }

    // view-model
    private val viewModel: SecondViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lightspeedAdapter = LightspeedAdapter()

        binding.apply {
            recyclerView.apply {
                adapter = lightspeedAdapter
                layoutManager = LinearLayoutManager(this@SecondActivity)
            }

            if (Constants.isOnline(applicationContext)) {
                onlineStatus = "online"
                viewModel.lightspeed.observe(this@SecondActivity) { employees ->
                    lightspeedAdapter.submitList(employees)
                }

            } else {
                onlineStatus = "offline"
                viewModel.lightspeed.observe(this@SecondActivity) { employees ->
                    lightspeedAdapter.submitList(employees)

                    Toast.makeText(
                        applicationContext,
                        "Network is online... ",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        recycler_View.adapter = null
    }
}

