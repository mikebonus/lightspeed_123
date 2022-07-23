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
import com.lightspeed.lightspeedproject.viewmodel.FirstViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_second.recycler_View

@AndroidEntryPoint
class FirstActivity : AppCompatActivity() {

    companion object {
        var onlineStatus = "online"
    }

    // view-model
    private val viewModel: FirstViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lightspeedAdapter = LightspeedAdapter()

        binding.apply {
            recyclerView.apply {
                adapter = lightspeedAdapter
                layoutManager = LinearLayoutManager(this@FirstActivity)
            }

            if (Constants.isOnline(applicationContext)) {
                onlineStatus = "online"
                viewModel.lightspeed.observe(this@FirstActivity) { lightspeed ->
                    lightspeedAdapter.submitList(lightspeed)
                }

            } else {
                onlineStatus = "offline"
                viewModel.lightspeed.observe(this@FirstActivity) { lightspeed ->
                    lightspeedAdapter.submitList(lightspeed)

                    Toast.makeText(
                        applicationContext,
                        "Network is online... ",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        recycler_View.adapter = null
    }
}

