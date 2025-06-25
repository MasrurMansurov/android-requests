package com.example.myapp.presentation.authorization

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.myapp.MainActivity
import com.example.myapp.R
import com.example.myapp.databinding.ActivityAuthorizationBinding
import com.example.myapp.domain.usecase.AuthorizationManagerService
import com.example.myapp.presentation.product.ProductActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch

class AuthorizationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthorizationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAuthorizationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupView()
    }
    private fun setupView() = with(binding) {
        authBtn.setOnClickListener{
            if (phoneText.text.toString() != "" && passwordText.text.toString() != "") {
                authRequest(phoneText.text.toString(), passwordText.text.toString())
            }
            else {
                MaterialAlertDialogBuilder(this@AuthorizationActivity)
                    .setTitle("Warning")
                    .setMessage("Please enter the forms")
                    .show()
            }
        }
    }

    private fun authRequest(phone: String, password: String) = with(binding) {
        lifecycleScope.launch {
            loading.visibility = View.VISIBLE
            try {
                val response = AuthorizationManagerService().authorization(
                    phone,
                    password)

                if (response.isSuccessful && response.body()?.status == "success") {
                   val intent = Intent(this@AuthorizationActivity, MainActivity::class.java)
                    startActivity(intent)
                }
                else {
                    MaterialAlertDialogBuilder(this@AuthorizationActivity)
                        .setTitle("Error")
                        .setMessage("Incorrect authorization data")
                        .show()
                }
            }
            catch (e: Exception) {
                Log.e("--E Error", e.toString())
                MaterialAlertDialogBuilder(this@AuthorizationActivity)
                    .setTitle("Error")
                    .setMessage("Error while catching data")
                    .show()
            }
            finally {
                loading.visibility = View.GONE
            }
        }
    }
}