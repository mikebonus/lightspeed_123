package com.lightspeed.presentation.ui

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.lightspeed.presentation.adapter.LightspeedAdapter
import com.lightspeed.lightspeedproject.databinding.ActivitySecondBinding
import com.lightspeed.util.Constants
import com.lightspeed.presentation.viewmodel.SecondViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_second.*

@AndroidEntryPoint
class SecondActivity : AppCompatActivity() {

    companion object {
        var onlineStatus = "online"
    }

    // view-model
    private lateinit var binding: ActivitySecondBinding
    private lateinit var lightspeedAdapter: LightspeedAdapter
    private val viewModel: SecondViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        lightspeedAdapter = LightspeedAdapter()
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

