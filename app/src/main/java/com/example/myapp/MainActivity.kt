package com.example.myapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp.databinding.ActivityMainBinding
import com.example.myapp.domain.adapter.ProductAdapter
import com.example.myapp.domain.usecase.ProductManagerService
import com.example.myapp.presentation.authorization.AuthorizationActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupView()
    }

    private fun setupView() = with(binding) {

        productRV.setHasFixedSize(true)
        productRV.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)

        sendReqBtn.setOnClickListener {
            sendProductRequest2()
        }
    }

    /*
    private fun sendProductRequest() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = ProductManagerService().getProducts("739")
                withContext(Dispatchers.Main) {
                    binding.reqBodyText.text = response.body().toString()
                    MaterialAlertDialogBuilder(this@MainActivity)
                        .setTitle("Testing")
                        .setMessage("Test Message for Test Alert")
                        .show()
                }
            }
            catch (e: Exception) {
                Log.e("--E Error", e.toString())
                withContext(Dispatchers.Main) {
                    MaterialAlertDialogBuilder(this@MainActivity)
                        .setTitle("Error")
                        .setMessage("Error while working")
                        .show()
                }
            }
        }
    }
    */

    private fun sendProductRequest2() = lifecycleScope.launch {
        binding.loading.visibility = View.VISIBLE
        try {
            val response = ProductManagerService().getProducts("739")
            binding.productRV.adapter = ProductAdapter(this@MainActivity, response.body() ?: arrayListOf())
        }
        catch (e: Exception) {
            Log.e("--E Error", e.toString())
            MaterialAlertDialogBuilder(this@MainActivity)
                .setTitle("Error")
                .setMessage("Error while catching data")
                .show()
        }
        finally {
            binding.loading.visibility = View.GONE
        }
    }
}